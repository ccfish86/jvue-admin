package net.ccfish.jvue.startup;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

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
import org.springframework.web.method.HandlerMethod;
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
                AclResc moduleAclResc = map.get(info).getBeanType().getAnnotation(AclResc.class);

                if (moduleAclResc != null) {

                    logger.debug("load module resource: id={}, code={}, name={}", moduleAclResc.id(),
                            moduleAclResc.code(), moduleAclResc.name());
                    Collection<AclResource> resources = resourcesMap.get(moduleAclResc.id());

                    Class<?> aclResourceClass = map.get(info).getBeanType();
                    RequestMapping moduleMapping =
                            aclResourceClass.getAnnotation(RequestMapping.class);

                    if (resources == null || resources.isEmpty()) {

                        // 追加module
                        AclResource moduleResc = new AclResource();
                        moduleResc.setId(moduleAclResc.id());
                        moduleResc.setType(AclResource.Type.MODULE);
                        moduleResc.setCode(moduleAclResc.code());
                        moduleResc.setName(moduleAclResc.name());

                        resourcesMap.put(moduleAclResc.id(), moduleResc);
                    }

                    if (moduleMapping != null) {

                        Method method = map.get(info).getMethod();
                        AclResc methodAclResc = method.getAnnotation(AclResc.class);
                        if (methodAclResc != null) {

                            logger.debug("load resource: id={}, code={}, name={}",
                                    methodAclResc.id(), methodAclResc.code(), methodAclResc.name());
                            AclResource methodResc = new AclResource();
                            methodResc.setId(moduleAclResc.id() + methodAclResc.id());
                            methodResc.setType(AclResource.Type.METHOD);
                            methodResc.setCode(moduleAclResc.code() + methodAclResc.code());
                            methodResc.setName(moduleAclResc.name() + methodAclResc.name());

                            resourcesMap.put(moduleAclResc.id(), methodResc);
                        }
                    }

                }
            }
        } finally {
            ilock.unlock();
        }
        // addModule(resourcesMap);
    }
}
