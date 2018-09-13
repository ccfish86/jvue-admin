/*
 * Copyright © 2013-2017 BLT, Co., Ltd. All Rights Reserved.
 */
package net.ccfish.jvue.security;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 自定401返回值
 *
 * @author hackyo
 * Created on 2017/12/9 20:10.
 */
@Component
public class EntryPointUnauthorizedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setStatus(401);
    }

}