package net.ccfish.jvue.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import net.ccfish.common.acl.CurrentUser;

@Component
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
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
		if (authentication.getPrincipal() instanceof JwtUserDetails) {
			JwtUserDetails jwtUser = (JwtUserDetails) authentication.getPrincipal();
			return jwtUser;
		} else {
			return null;
		}
	}
}
