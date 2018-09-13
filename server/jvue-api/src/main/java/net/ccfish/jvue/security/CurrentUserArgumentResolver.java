package net.ccfish.jvue.security;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import net.ccfish.common.acl.CurrentUser;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 检查解析器是否支持解析该参数
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果该参数注解有@CurrentUser
		// 如果该参数的类型为User
		if (parameter.getParameterAnnotation(CurrentUser.class) != null
				&& parameter.getParameterType() == JwtUserDetails.class) {
			// 支持解析该参数
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return null;
		}
		
		logger.debug("authentication {}", authentication);
		logger.debug("authentication {}", authentication.getPrincipal().getClass());
		if (authentication.getPrincipal() instanceof JwtUserDetails) {
			JwtUserDetails jwtUser = (JwtUserDetails) authentication.getPrincipal();
			return jwtUser;
		} else if (authentication instanceof OAuth2AuthenticationToken) {
			logger.debug("authentication OAuth2AuthenticationToken {}", authentication.getPrincipal());
			HttpServletRequest request =  
					webRequest.getNativeRequest(HttpServletRequest.class);
			return request.getSession(true).getAttribute("USER_INFO");
		} else {
			return null;
		}
	}
}
