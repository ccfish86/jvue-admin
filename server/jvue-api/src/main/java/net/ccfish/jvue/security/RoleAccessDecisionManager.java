/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.MultiMap;

import net.ccfish.common.JvueDataStatus;
import net.ccfish.jvue.vm.AclResource;

/**
 * 根据用户权限，处理API访问控制
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@Component
public class RoleAccessDecisionManager implements AccessDecisionManager {

    private final Logger logger = LoggerFactory.getLogger(RoleAccessDecisionManager.class);
    
    @Autowired
    private HazelcastInstance hazelcastInstance;
    
    /* (non-Javadoc)
     * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
     */
    @Override
    public void decide(Authentication authentication, Object arg1, Collection<ConfigAttribute> arg2)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // TODO Auto-generated method stub
        logger.debug("arg0 {}, arg1 {}, arg2 {}", authentication, arg1, arg2);
        // 逻辑
        
        // 1.判断URL对应的权限定义
        MultiMap<Integer, AclResource> resourcesMap =
                hazelcastInstance.getMultiMap("acl-resource");
        // >> arg1 FilterInvocation: URL: /module?page=0&pageSize=10
        // >> 如果不需要登录的话，直接放行
        

        // 2.判断是否为超级管理员，是的话直接放行
        if (authentication.getPrincipal() instanceof JwtUserDetails) {
            JwtUserDetails jwtUser = (JwtUserDetails) authentication.getPrincipal();
            if (jwtUser.getSuperUser() == JvueDataStatus.SUPER_USER_TRUE) {
                // 放行
                return ;
            }
        }
        
        // 3.获取用户的角色，通过比对角色授予的API接口ID和AclResource里定义的ID，有匹配则放行
        
        // 4.上述以外，禁止调用
        // TODO throw new AccessDeniedException("no role");
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
