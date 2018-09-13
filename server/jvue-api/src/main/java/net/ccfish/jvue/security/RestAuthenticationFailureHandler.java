/*
 * Copyright © 2018 ccfish.net, sweeper. All Rights Reserved.
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ccfish.common.web.BaseModel;
/**
 * 处理登录异常
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.1
 */
public class RestAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	private final Logger logger = LoggerFactory.getLogger(getClass()); 	
	@Autowired
	ObjectMapper objectMapper;
	
	public RestAuthenticationFailureHandler() {
		super("/login?error");
	}
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// do nth.
		// TODO 处理登录失败N次后，账号锁定等

        // 根据不同的Accept返回不同类型值
    	String accept = response.getHeader("accept");
        if (Objects.equals(MediaType.APPLICATION_JSON_UTF8_VALUE, accept)
        		|| Objects.equals(MediaType.APPLICATION_JSON_VALUE, accept)) {
			logger.info("login faild ");
			String result = objectMapper.writeValueAsString(BaseModel.error("1", exception.getMessage()));
			response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
			response.getWriter().write(result);
        } else {
        	super.onAuthenticationFailure(request, response, exception);
        }
	}

}
