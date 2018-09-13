package net.ccfish.jvue.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import net.ccfish.jvue.security.EntryPointUnauthorizedHandler;
import net.ccfish.jvue.security.OAuth2AuthenticationSuccessHandler;
import net.ccfish.jvue.security.RestAccessDeniedHandler;
import net.ccfish.jvue.security.RestAuthenticationFailureHandler;
import net.ccfish.jvue.security.RestAuthenticationSuccessHandler;
import net.ccfish.jvue.security.RestLogoutSuccessHandler;

/**
 * 安全模块配置
 * 
 * @author 袁贵
 * @version 1.0
 * @since  1.0
 */
@Configuration
@EnableWebSecurity
//@Order(SecurityProperties.BASIC_AUTH_ORDER)
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
//    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final RestLogoutSuccessHandler logoutSuccessHandler;
    
    @Autowired
    private DataSource dataSource;
//    @Autowired
//    private RoleAccessDecisionManager accessDecisionManager;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService,
            EntryPointUnauthorizedHandler entryPointUnauthorizedHandler,
            RestAccessDeniedHandler restAccessDeniedHandler,
            RestLogoutSuccessHandler logoutSuccessHandler) {
        this.userDetailsService = userDetailsService;
//        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
        this.entryPointUnauthorizedHandler = entryPointUnauthorizedHandler;
        this.restAccessDeniedHandler = restAccessDeniedHandler;
        this.logoutSuccessHandler = logoutSuccessHandler;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        
        httpSecurity
            .csrf().disable()	
//                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//            .and()			
				.formLogin()
//				.loginPage("/login")
				.successHandler(authenticationSuccessHandler())
				.failureHandler(authenticationFailureHandler())
				.permitAll()
            .and()
            	.oauth2Login()
            	.successHandler(oauth2SuccessHandler())
			.and().
				rememberMe()/* weex下cookie有问题 .alwaysRemember(true)*/
				//.alwaysRemember(true)
				.tokenValiditySeconds(840000)
				.tokenRepository(tokenRepository())
			.and()
				.userDetailsService(userDetailsService)
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/pub/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/*.html").permitAll()
                .antMatchers("/**/*.html").permitAll()
                .antMatchers("/swagger-resources").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/","/admin/").permitAll()
                .antMatchers("/admin/**","/**/favicon.ico", "/webjars/**").permitAll()
                .antMatchers("/**").authenticated()
            .and()
            	.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
            .and()
                .headers().cacheControl();
        
//        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
//        httpSecurity.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(restAccessDeniedHandler);

    }
    
    @Bean(name="myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		jdbcTokenRepositoryImpl.setDataSource(dataSource);
		// jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		return jdbcTokenRepositoryImpl;
	}
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
		return new RestAuthenticationSuccessHandler();
	}
	
	@Bean
	public RestAuthenticationFailureHandler authenticationFailureHandler() {
		return new RestAuthenticationFailureHandler();
	}
	
	@Bean
	public AuthenticationSuccessHandler oauth2SuccessHandler() {
		return new OAuth2AuthenticationSuccessHandler();
	}
}
