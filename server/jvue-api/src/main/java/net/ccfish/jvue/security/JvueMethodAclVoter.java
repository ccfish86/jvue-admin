/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.security;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import net.ccfish.common.JvueDataStatus;
import net.ccfish.jvue.service.JvueRoleService;
import net.ccfish.jvue.service.acl.AclResc;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
@Component
public class JvueMethodAclVoter implements AccessDecisionVoter<MethodInvocation> {

    private final Logger logger = LoggerFactory.getLogger(JvueMethodAclVoter.class);

    @Autowired
    private JvueRoleService jvueRoleService;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, MethodInvocation invocation,
            Collection<ConfigAttribute> attributes) {
        JwtUserDetails jwtUser = null;
        // 1.判断是否为超级管理员，是的话直接放行
        if (authentication.getPrincipal() instanceof JwtUserDetails) {
            jwtUser = (JwtUserDetails) authentication.getPrincipal();
            if (jwtUser.getSuperUser() == JvueDataStatus.SUPER_USER_TRUE) {
                // 放行
                return ACCESS_GRANTED;
            }
        } else {
            return ACCESS_ABSTAIN;
        }

        // 1.判断URL对应的权限定义
        // >> arg1 FilterInvocation: URL: /module?page=0&pageSize=10
        // >> 如果不需要登录的话，直接放行
        Method method = invocation.getMethod();
        AclResc classResc = method.getDeclaringClass().getAnnotation(AclResc.class);
        AclResc methodAclResc = method.getAnnotation(AclResc.class);
        Integer apiCode = 0;
        if (classResc != null) {
            apiCode += classResc.id();
        }
        if (methodAclResc != null) {
            apiCode += methodAclResc.id();
        }
        
        if(apiCode > 0) {
            List<Integer> roles = jvueRoleService.getRolesByApi(apiCode);
            if (!roles.isEmpty()) {
                // 2.判断是否为超级管理员，是的话直接放行
                Collection<Integer> intersection =
                        CollectionUtils.intersection(roles, jwtUser.getRoles());
                if (intersection.isEmpty()) {
                    // 没有匹配到角色
                    return ACCESS_DENIED;
                } else {
                    logger.debug("匹配到角色 {}", intersection);
                    return ACCESS_GRANTED;
                }
            } else {
                return ACCESS_ABSTAIN;
            }
        } else {
            return ACCESS_ABSTAIN;
        }
    }

}
