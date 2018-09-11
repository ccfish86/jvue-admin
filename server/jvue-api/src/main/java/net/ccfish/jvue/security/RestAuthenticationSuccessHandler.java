/*
 * Copyright © 2018 ccfish.net, sweeper. All Rights Reserved.
 */
package net.ccfish.jvue.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ccfish.common.web.BaseModel;
import net.ccfish.jvue.service.model.JvueUserInfo;

/**
 * 处理登录异常
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.1
 */
public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass()); 
	
	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Object principal = authentication.getPrincipal();
		Object authenticationDetails = authentication.getDetails();
		logger.info("login success with {}, {}", principal, authenticationDetails);
		
		// TODO 异步写登录日志等
		if (principal != null) {
			if (principal instanceof JwtUserDetails) {

				JwtUserDetails userDetail = (JwtUserDetails)principal;

				JvueUserInfo userInfo = new JvueUserInfo();
				userInfo.setUsername(userDetail.getUsername());
				userInfo.setEmail(userDetail.getEmail());
				userInfo.setNickname(userDetail.getNickname());
				String result = objectMapper.writeValueAsString(BaseModel.ok(userInfo));
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
				response.getWriter().write(result);
			}
		}
	}

}
