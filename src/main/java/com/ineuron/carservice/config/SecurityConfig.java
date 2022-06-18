package com.ineuron.carservice.config;

import com.ineuron.carservice.constant.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

//    @Autowired
//    private AuthProviderConfig customAuthProvider;

    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.xAuthToken();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthProviderConfig authProvider() {
        AuthProviderConfig authenticationProvider = new AuthProviderConfig();
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    response.getWriter().write("{\"message\":\"Unauthorized User\"}");
                }).and()
                .formLogin()
                .permitAll()
                .usernameParameter(CommonConstant.USERNAME)
                .passwordParameter(CommonConstant.PASSWORD)
                .loginProcessingUrl("/login")
                .successHandler(getAuthenticationSuccessHandler())
                .failureHandler(getAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(getLogoutSuccessHandler())
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/forgot-password",
                        "/user/reset-password",
                        "/user/create-password").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .authenticationProvider(authProvider());
        return http.build();
    }


    private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            request.getSession().setMaxInactiveInterval(Integer.MAX_VALUE);
            response.setStatus(HttpStatus.OK.value());
        };
    }

    private AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return (request, response, exception) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("{\"message\":\"" + exception.getMessage() + "\"}");
        };
    }

    private LogoutSuccessHandler getLogoutSuccessHandler() {
        return (request, response, authentication) -> response.setStatus(HttpStatus.NO_CONTENT.value());
    }


}
