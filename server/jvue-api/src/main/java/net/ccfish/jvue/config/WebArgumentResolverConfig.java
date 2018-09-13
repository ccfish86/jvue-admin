package net.ccfish.jvue.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import net.ccfish.jvue.security.CurrentUserArgumentResolver;

@Configuration
public class WebArgumentResolverConfig implements WebMvcConfigurer {

	@Autowired
	private CurrentUserArgumentResolver userArgumentResolver;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
		resolvers.add(userArgumentResolver);
	}

	
}
