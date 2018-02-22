/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import net.ccfish.jvue.security.JwtTokenUtil;
import net.ccfish.jvue.security.JwtUserDetails;
import net.ccfish.jvue.service.AuthService;
import net.ccfish.jvue.vm.UserInfo;

/**
 * 用户登录处理
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserInfo login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserInfo userInfo = new UserInfo();
        String token = jwtTokenUtil.generateToken(userDetails);
        userInfo.setToken(token);
        if (userDetails instanceof JwtUserDetails) {
            JwtUserDetails jwtUser= (JwtUserDetails) userDetails;
            userInfo.setEmail(jwtUser.getEmail());
            userInfo.setNickname(jwtUser.getNickname());
            userInfo.setUsername(username);
        }
        return userInfo;
    }

    @Override
    public String refreshToken(String oldToken) {
        String token = oldToken.substring("Bearer ".length());
        if (!jwtTokenUtil.isTokenExpired(token)) {
            return jwtTokenUtil.refreshToken(token);
        }
        return "error";
    }

}
