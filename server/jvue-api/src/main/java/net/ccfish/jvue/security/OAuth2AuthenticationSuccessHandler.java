package net.ccfish.jvue.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import net.ccfish.jvue.service.JvueOAuth2UserService;

public class OAuth2AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JvueOAuth2UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.onAuthenticationSuccess(request, response, authentication);
		// TODO 处理jvue用户绑定
//		logger.info("URI {}", request.getRequestURI());
//		request.getParameterMap().forEach((key, value) -> {
//			logger.info("param {} = {} ", key, value);
//		});
		
		String registrationId = null;
		String username = null;
		
		if (authentication instanceof OAuth2AuthenticationToken) {
			OAuth2AuthenticationToken oAuth2Authentication = (OAuth2AuthenticationToken)authentication;
			registrationId = oAuth2Authentication.getAuthorizedClientRegistrationId();
		} else {
			// registration取不到
			logger.warn("取不到 ClientRegistrationId");
			return;
		}
			
		
		if (authentication.getPrincipal() != null) {
			if (authentication.getPrincipal() instanceof OAuth2User) {
				OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
				username = oauth2User.getName();
				
				// 这里可以根据不同的[registrationId]从[oauth2User.getAttributes()]里获取不同的用户数据
				// oauth2User.getAttributes()
			}
		}
		
		// save and update the principal
		logger.info("session {}", request.getSession());
		JwtUserDetails userDetails = userService.updateUser(registrationId, username);
		request.getSession(true).setAttribute("USER_INFO", userDetails);
	}

}
