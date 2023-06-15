package com.z7.collabowriteapi.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private TokenValidatorFilter tokenValidatorFilter;

    @Autowired
    private CustomBasicEntryPoint customBasicEntryPoint;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((configurer -> configurer.disable()))
                .exceptionHandling(configurer -> configurer.authenticationEntryPoint(customBasicEntryPoint))
                .authorizeHttpRequests((requests) -> requests.anyRequest().authenticated())
                .addFilterBefore(tokenValidatorFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((configurer) -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

}
