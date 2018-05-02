/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import net.ccfish.jvue.security.JvueMethodAclVoter;

/**
 * 
 * @author 袁贵
 * @version 1.0
 * @since 1.0
 */
@Configuration
@AutoConfigureAfter(WebSecurityConfig.class)
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class JvueGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {
    private final Logger logger = LoggerFactory.getLogger(JvueGlobalMethodSecurityConfiguration.class);
    
    @Autowired
    private JvueMethodAclVoter jvueMethodAclVoter;

    @Override
    public AccessDecisionManager accessDecisionManager() {

        List<AccessDecisionVoter<? extends Object>> decisionVoters =
                new ArrayList<AccessDecisionVoter<? extends Object>>();

        decisionVoters.add(jvueMethodAclVoter);// 启用自定义投票器
        decisionVoters.add(new RoleVoter());
        decisionVoters.add(new AuthenticatedVoter());

        return new AffirmativeBased(decisionVoters);
    }
}
