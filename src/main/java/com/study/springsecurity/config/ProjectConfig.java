package com.study.springsecurity.config;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.study.springsecurity.handlers.CustomAuthenticationFailureHandler;
import com.study.springsecurity.handlers.CustomAuthenticationSuccessHandler;

@Configuration
@EnableAsync
public class ProjectConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthenticationProvider             authenticationProvider;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        http.formLogin().defaultSuccessUrl("/main", true);
        //        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class).authorizeRequests()
        //            .anyRequest().permitAll();
        http.csrf().disable();
        http.authorizeRequests().anyRequest().permitAll();
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

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
