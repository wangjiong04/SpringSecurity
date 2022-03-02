package com.study.SpringSecurity.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;

import com.study.SpringSecurity.handler.CustomAuthenticationFailureHandler;
import com.study.SpringSecurity.handler.CustomAuthenticationSuccessHandler;

@Configuration
@EnableAsync
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider             authenticationProvider;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler);
        http.authorizeRequests().antMatchers("/error**").permitAll().anyRequest().authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder
            .setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
    }
}
