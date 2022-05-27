package com.study.springsecurity.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import com.study.springsecurity.components.OtpAuthenticationProvider;
import com.study.springsecurity.components.UsernamePasswordAuthenticationProvider;
import com.study.springsecurity.filters.InitialAuthenticationFilter;
import com.study.springsecurity.filters.JwtAuthenticationFilter;
import com.study.springsecurity.handlers.CustomAuthenticationFailureHandler;
import com.study.springsecurity.handlers.CustomAuthenticationSuccessHandler;

@Configuration
@EnableAsync
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationSuccessHandler     authenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler     authenticationFailureHandler;

    @Autowired
    private InitialAuthenticationFilter            initialAuthenticationFilter;

    @Autowired
    private JwtAuthenticationFilter                jwtAuthenticationFilter;

    @Autowired
    private OtpAuthenticationProvider              otpAuthenticationProvider;

    @Autowired
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(otpAuthenticationProvider)
            .authenticationProvider(usernamePasswordAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        http.formLogin().defaultSuccessUrl("/main", true);
        //        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class).authorizeRequests()
        //            .anyRequest().permitAll();
        http.csrf().disable();
        http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
            .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests().antMatchers("/user/**").permitAll().anyRequest().permitAll();
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
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

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
