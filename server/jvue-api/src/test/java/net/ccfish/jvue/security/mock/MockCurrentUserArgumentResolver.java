package net.ccfish.jvue.security.mock;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Primary;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.ModelAndViewContainer;

import net.ccfish.jvue.security.CurrentUserArgumentResolver;
import net.ccfish.jvue.security.JwtUserDetails;

@Component
@Primary
public class MockCurrentUserArgumentResolver extends CurrentUserArgumentResolver {

	public static JwtUserDetails jwtUser;
	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
//		Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
//		JwtUserDetails jwtUser = new JwtUserDetails(1L, "mock", "mock",
//				1, "mock user", "",
//				authorities, new ArrayList<>());
		return jwtUser;
	}
}