package net.ccfish.jvue.config;

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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import net.ccfish.jvue.security.EntryPointUnauthorizedHandler;
import net.ccfish.jvue.security.JwtAuthenticationTokenFilter;
import net.ccfish.jvue.security.RestAccessDeniedHandler;
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
    private final JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    private final EntryPointUnauthorizedHandler entryPointUnauthorizedHandler;
    private final RestAccessDeniedHandler restAccessDeniedHandler;
    private final RestLogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService,
            JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter,
            EntryPointUnauthorizedHandler entryPointUnauthorizedHandler,
            RestAccessDeniedHandler restAccessDeniedHandler,
            RestLogoutSuccessHandler logoutSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationTokenFilter = jwtAuthenticationTokenFilter;
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
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
//            .disable()
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
                .anyRequest().authenticated()
//            .and().rememberMe()
            .and().logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
            .and()
                .headers().cacheControl();
        
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(restAccessDeniedHandler);

    }
    
    @Bean(name="myAuthenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
