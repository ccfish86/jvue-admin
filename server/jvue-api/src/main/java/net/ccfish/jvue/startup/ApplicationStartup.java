package net.ccfish.jvue.startup;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ILock;
import com.hazelcast.core.MultiMap;

import net.ccfish.common.JvueDataStatus;
import net.ccfish.jvue.model.User;
import net.ccfish.jvue.repository.UserRepository;
import net.ccfish.jvue.service.JvueApiService;
import net.ccfish.jvue.service.acl.AclResc;
import net.ccfish.jvue.vm.AclResource;

/**
 * ACL接口加载 <br>
 * <p>
 * 参考了<a href="https://www.cnblogs.com/sweetchildomine/p/5998659.html">高因咖啡</a>的处理
 * <p>
 * 这里采用URL后匹配的模式,
 * 后续可以通过加载DB中的role/api-code数据，绑定给`Spring security`相关模块，效果更好一些
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
@Component
public class ApplicationStartup implements CommandLineRunner {

    private final Logger logger = LoggerFactory.getLogger(ApplicationStartup.class);

    @Autowired
    private HazelcastInstance hazelcastInstance;
    @Autowired
    private UserRepository userRepository;

    // @Resource
    // private AclResourceService aclResourceService;
    // @Resource
    // private AclAuthService aclAuthService;
    //
    @Autowired
    private JvueApiService jvueApiService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    // @Resource
    // private MySecurityMetadataSource securityMetadataSource;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void run(String... strings) throws Exception {
        /**
         * 初始化资源,保存到数据库
         */
        initModule();

        // /**
        // * Spring Security 需要的资源-权限
        // */
        // securityMetadataSource.doLoadResourceDefine();
        
        /**
         * 初始化用户
         */
        initAdmin();
        
    }

    private void initAdmin() {

        ILock ilock = hazelcastInstance.getLock("init-admin");
        try {
            if (ilock.isLocked()) {
                // 已有节点在执行
                return;
            }
            
            ilock.lock();
            long userCount =userRepository.count();
            if (userCount > 0) {
                // 用户已经存在
                return;
            }
            
            // 追加管理员
            User user = new User();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setStatus(JvueDataStatus.ENABLE_TRUE);
            user.setNickname("jvue super admin user");
            user.setSuperUser(JvueDataStatus.SUPER_USER_TRUE);
            
            userRepository.save(user);
        } finally {
            ilock.unlock();
        }
    }

    /**
     * 读取所有Controller包括以内的方法
     */
    private void initModule() {

        ILock ilock = hazelcastInstance.getLock("init-acl-resource");
        try {
            if (ilock.isLocked()) {
                // 已有节点在执行
                return;
            }

            ilock.lock();
            /**
             * 模块 - 方法map
             */
            MultiMap<Integer, AclResource> resourcesMap =
                    hazelcastInstance.getMultiMap("acl-resource");

            if (resourcesMap.size() > 0) {
                // 已加载过
                return;
            }

            Map<RequestMappingInfo, HandlerMethod> map =
                    requestMappingHandlerMapping.getHandlerMethods();
            for (RequestMappingInfo info : map.keySet()) {
                HandlerMethod handlerMethod = map.get(info);
                AclResc classAclResc = handlerMethod.getBeanType().getAnnotation(AclResc.class);

                if (classAclResc != null) {

                    logger.debug("load class resource: id={}, code={}, name={}", classAclResc.id(),
                            classAclResc.code(), classAclResc.name());
                    Collection<AclResource> resources = resourcesMap.get(classAclResc.id());

                    Class<?> aclResourceClass = handlerMethod.getBeanType();
                    RequestMapping classMapping =
                            aclResourceClass.getAnnotation(RequestMapping.class);

                    if (resources == null || resources.isEmpty()) {

                        // 追加class
                        AclResource classResc = new AclResource();
                        classResc.setId(classAclResc.id());
                        classResc.setType(AclResource.Type.CLASS);
                        classResc.setCode(classAclResc.code());
                        classResc.setName(classAclResc.name());
                        
                        resourcesMap.put(classAclResc.id(), classResc);
                    }

                    if (classMapping != null) {

                        Method method = handlerMethod.getMethod();
                        AclResc methodAclResc = method.getAnnotation(AclResc.class);
                        
                        if (methodAclResc != null) {

                            logger.debug("load resource: id={}, code={}, name={}",
                                    methodAclResc.id(), methodAclResc.code(), methodAclResc.name());
                            
                            // TODO 需要后续处理的事项，有一部分字段，与Swagger2重复，仅需保留Swagger2定义的一部分，便于开发与维护
                            
                            AclResource methodResc = new AclResource();
                            methodResc.setId(classAclResc.id() + methodAclResc.id());
                            methodResc.setType(AclResource.Type.METHOD);
                            methodResc.setCode(classAclResc.code() + methodAclResc.code());
                            methodResc.setName(classAclResc.name() + methodAclResc.name());
                            
                            // URL和请求方法
                            PatternsRequestCondition pattern = info.getPatternsCondition();
                            RequestMethodsRequestCondition methods = info.getMethodsCondition();
                            
                            methodResc.setPattern(pattern.getPatterns());
                            // methodResc.setPath(classMapping.path());
                            methodResc.setMethod(toString(methods.getMethods()));

                            resourcesMap.put(classAclResc.id(), methodResc);
                        }
                    }

                }
            }
        } finally {
            ilock.unlock();
        }
        // addModule(resourcesMap);
    }

    /**
     * @param methods
     * @return
     * @since  1.0
     */
    private String[] toString(Set<RequestMethod> methods) {
        if (methods == null || methods.size() == 0) {
            return null;
        }
        
        String[] strMethods = methods.stream().map(m -> m.name()).toArray(String[]::new);
        return strMethods;
    }
}
