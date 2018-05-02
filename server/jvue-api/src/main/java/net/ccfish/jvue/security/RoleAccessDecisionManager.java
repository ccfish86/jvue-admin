/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.security;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.util.UrlPathHelper;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

import net.ccfish.jvue.config.JvueGlobalMethodSecurityConfiguration;
import net.ccfish.jvue.service.JvueRoleService;
import net.ccfish.jvue.vm.AclResource;

/**
 * 根据用户权限，处理API访问控制
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 * @see JvueGlobalMethodSecurityConfiguration
 * @see JvueMethodAclVoter
 * @deprecated path匹配时，带参数的时候无法匹配成功，故改为MethodInvocation方式处理
 */
// @Component
public class RoleAccessDecisionManager implements AccessDecisionManager {

    private final Logger logger = LoggerFactory.getLogger(RoleAccessDecisionManager.class);

    private final UrlPathHelper pathHelper = new UrlPathHelper();
    private final PathMatcher pathMatcher = new AntPathMatcher();
    
    @Autowired
    private HazelcastInstance hazelcastInstance;
    @Autowired
    private JvueRoleService jvueRoleService;
    
    /* (non-Javadoc)
     * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
     */
    @Override
    public void decide(Authentication authentication, Object arg1, Collection<ConfigAttribute> arg2)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // TODO Auto-generated method stub
        logger.debug("arg0 {}, arg1 {}, arg2 {}", authentication, arg1, arg2);
        // 逻辑
        if (arg1 instanceof FilterInvocation) {
            // HTTP filter object
            FilterInvocation filterInvocation = (FilterInvocation) arg1;
            
//            // 1.判断是否为超级管理员，是的话直接放行
//            if (authentication.getPrincipal() instanceof JwtUserDetails) {
//                JwtUserDetails jwtUser = (JwtUserDetails) authentication.getPrincipal();
//                if (jwtUser.getSuperUser() == JvueDataStatus.SUPER_USER_TRUE) {
//                    // 放行
//                    logger.debug("SUPER_USER_TRUE");
//                    return ;
//                }
//            }
            
            // 1.判断URL对应的权限定义
            MultiMap<Integer, AclResource> resourcesMap =
                    hazelcastInstance.getMultiMap("acl-resource");
            // >> arg1 FilterInvocation: URL: /module?page=0&pageSize=10
            // >> 如果不需要登录的话，直接放行
            String requestUrl = filterInvocation.getRequestUrl();
            String requestMethod = filterInvocation.getRequest().getMethod();
            
            Integer apiCode = null;
            logger.debug("访问接口:{} {}", requestUrl, requestMethod);
            
            for (AclResource ar: resourcesMap.values()) {
                if (ar.getType() == AclResource.Type.METHOD) {
                    //pathHelper.
                    boolean isUrl = false;
                    boolean isMethod = false;
                    
                    logger.trace("判断接口:{} {} {}, {}", ar.getCode(), ar.getName(), ar.getPath(), ar.getPattern());

                    for (String path : ar.getPattern()) {
                        isUrl = pathMatcher.match(path, requestUrl);
                        if (isUrl) {
                            break;
                        }
                    }
                    if (isUrl) {
                        if (ar.getMethod() != null) {
                            for (String method: ar.getMethod()) {
                                if (Objects.equals(method, requestMethod)){
                                    isMethod = true;
                                    break;
                                }
                            }
                        } else {
                            isMethod = true;
                        }
                    }
                    
                    if (isUrl && isMethod) {
                        // 已匹配
                        apiCode = ar.getId();
                        logger.debug("已匹配接口:{} > {} {}", requestUrl, ar.getCode(), ar.getName());
                        break;
                    }
                }
            }
            
            if (apiCode != null ) {
                // 取对应的角色权限
                List<Integer> roles = jvueRoleService.getRolesByApi(apiCode);
                
                if (!roles.isEmpty()) {
                    // 2.判断是否为超级管理员，是的话直接放行
                    if (authentication.getPrincipal() instanceof JwtUserDetails) {
                        JwtUserDetails jwtUser = (JwtUserDetails) authentication.getPrincipal();
                        Collection<Integer> intersection =
                                CollectionUtils.intersection(roles, jwtUser.getRoles());
                        if (intersection.isEmpty()) {
                            // 没有匹配到角色
                            throw new AccessDeniedException("no role");
                        }

                    }
                }
            }
            
            // 处理 apiCode与角色匹配
            
            // 3.获取用户的角色，通过比对角色授予的API接口ID和AclResource里定义的ID，有匹配则放行
            
            // 4.上述以外，禁止调用
            // TODO throw new AccessDeniedException("no role");
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.security.access.AccessDecisionManager#supports(org.springframework.security.access.ConfigAttribute)
     */
    @Override
    public boolean supports(ConfigAttribute arg0) {
        // TODO Auto-generated method stub
        return false;
    }

    /* (non-Javadoc)
     * @see org.springframework.security.access.AccessDecisionManager#supports(java.lang.Class)
     */
    @Override
    public boolean supports(Class<?> arg0) {
        // TODO Auto-generated method stub
        return false;
    }

}
