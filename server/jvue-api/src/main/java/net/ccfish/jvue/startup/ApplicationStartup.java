package net.ccfish.jvue.startup;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.ccfish.common.JvueDataStatus;
import net.ccfish.common.acl.AclResc;
import net.ccfish.jvue.autogen.dao.JvueUserMapper;
import net.ccfish.jvue.autogen.model.JvueUser;
import net.ccfish.jvue.service.JvueApiService;
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
    private JvueUserMapper userRepository;

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
            
            // 没有超级管理员
            JvueUser record = new JvueUser();
            
            long userCount =userRepository.selectCount(record);
            if (userCount > 0) {
                // 用户已经存在
                return;
            }
            
            // 追加管理员
            JvueUser user = new JvueUser();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setStatus(JvueDataStatus.ENABLE_TRUE);
            user.setNickname("Supper admin");
            user.setSuperUser(JvueDataStatus.SUPER_USER_TRUE);
            
            userRepository.insertSelective(user);
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

            Set<Integer> apiIds = new HashSet<>(); 
            
            Map<RequestMappingInfo, HandlerMethod> map =
                    requestMappingHandlerMapping.getHandlerMethods();
            for (RequestMappingInfo info : map.keySet()) {
                HandlerMethod handlerMethod = map.get(info);
                AclResc classAclResc = handlerMethod.getBeanType().getAnnotation(AclResc.class);
                Api classApi = handlerMethod.getBeanType().getAnnotation(Api.class);

                if (classAclResc != null) {

                    Collection<AclResource> resources = resourcesMap.get(classAclResc.id());

                    Class<?> aclResourceClass = handlerMethod.getBeanType();
                    RequestMapping classMapping =
                            aclResourceClass.getAnnotation(RequestMapping.class);

                    if (resources == null || resources.isEmpty()) {

                        // 追加class
                        AclResource classResc = new AclResource();
                        classResc.setId(classAclResc.id());
                        classResc.setType(AclResource.Type.CLASS);
                        classResc.setName(classApi.value());

                        resourcesMap.put(classAclResc.id(), classResc);
                    }

                    if (classMapping != null) {

                        Method method = handlerMethod.getMethod();
                        AclResc methodAclResc = method.getAnnotation(AclResc.class);
                        ApiOperation methodOperation = method.getAnnotation(ApiOperation.class);

                        if (methodAclResc != null) {

                            if (methodOperation == null) {
                                logger.warn("@ApiOperation is undefined. {}#{}", aclResourceClass.getName(), method.getName());
                                continue;
                            }
                            
                            String apiName = classApi.value();
                            String operatioName = methodOperation.value();
                            
                            if (StringUtils.isEmpty(apiName)) {
                                // 读tags值
                                apiName = StringUtils.join(classApi.tags());
                            }
                            if (StringUtils.isEmpty(operatioName)) {
                                // 读tags值
                                operatioName = StringUtils.join(methodOperation.tags());
                            }

                            // 有一部分字段，与Swagger2重复，仅需保留Swagger2定义的一部分，便于开发与维护
                            int id = classAclResc.id() + methodAclResc.id();
                            String name = apiName + operatioName;
                            
                            if (StringUtils.isEmpty(apiName)) {
                                logger.warn("Class name is undefined：{}#{}", aclResourceClass.getName(), method.getName());
                            }
                            if (StringUtils.isEmpty(operatioName)) {
                                logger.warn("Method name is undefined：{}#{}", aclResourceClass.getName(), method.getName());
                            }
                            
                            // 判断ID是否重复
                            if (!apiIds.add(id)) {
                                logger.error("API ID is duplicate defined：{} {}#{}", id, aclResourceClass.getName(), method.getName());
                                continue;
                            }
                            
                            logger.debug("load method resource: id={}, name={}", id, name);

                            AclResource methodResc = new AclResource();
                            methodResc.setId(id);
                            methodResc.setType(AclResource.Type.METHOD);
                            methodResc.setName(name);

                            // URL和请求方法
                            PatternsRequestCondition pattern = info.getPatternsCondition();
                            RequestMethodsRequestCondition methods = info.getMethodsCondition();

                            // logger.debug("pattern: {}, {}", pattern, methods);

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
