/*
 * Copyright © 2013-2019 BaoLaiTong, Co., Ltd. All Rights Reserved.
 */

package net.ccfish.jvue.security;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ccfish.common.web.BaseModel;

/**
 * 处理登录
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@Component
public class RestLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler implements LogoutSuccessHandler {

//    @Autowired
//    private AuthService userService;
    
    private final Logger logger = LoggerFactory.getLogger(RestLogoutSuccessHandler.class);
    
//    private final JwtTokenUtil jwtTokenUtil;
	
    @Autowired
	private ObjectMapper objectMapper;

//    @Autowired
//    public RestLogoutSuccessHandler(JwtTokenUtil jwtTokenUtil) {
//        this.jwtTokenUtil = jwtTokenUtil;
//    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.web.authentication.logout.LogoutSuccessHandler#onLogoutSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        // 根据不同的Accept返回不同类型值
    	String accept = response.getHeader("accept");
    	if (MediaType.APPLICATION_JSON_UTF8_VALUE.equalsIgnoreCase(accept)
         		|| MediaType.APPLICATION_JSON_VALUE.equalsIgnoreCase(accept)) {
	        String result = objectMapper.writeValueAsString(BaseModel.ok(""));
	        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(result);
        } else {
        	// 画面跳转等处理 
        	super.handle(request, response, authentication);
        }
		
        //jwtTokenUtil.expireToken(token);
//        if (authorization != null) {
//            userService.logout(authorization);
//        }
    }

}
