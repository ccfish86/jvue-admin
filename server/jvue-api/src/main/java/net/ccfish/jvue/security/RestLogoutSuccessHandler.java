/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 处理登录
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@Component
public class RestLogoutSuccessHandler implements LogoutSuccessHandler {

//    @Autowired
//    private AuthService userService;
    
    private final Logger logger = LoggerFactory.getLogger(RestLogoutSuccessHandler.class);
    
    private final JwtTokenUtil jwtTokenUtil;
    
    @Autowired
    public RestLogoutSuccessHandler(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
            Authentication arg2) throws IOException, ServletException {
        logger.debug(" Authentication {}", arg2);
        //jwtTokenUtil.expireToken(token);
//        if (authorization != null) {
//            userService.logout(authorization);
//        }
    }

}
