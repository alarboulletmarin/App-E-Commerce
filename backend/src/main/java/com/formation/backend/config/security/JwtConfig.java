package com.formation.backend.config.security;

import com.formation.backend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * JwtConfig class is used to configure the JwtTokenProvider bean
 * JwtTokenProvider is used to generate and validate JWT tokens
 * */
@Configuration
public class JwtConfig {

    private final CustomUserDetailsService customUserDetailsService;
    // Read the values from application.yml file
    @Value("${spring.security.jwt.secret-key}")
    private String secretKey;
    // Read the values from application.yml file
    @Value("${spring.security.jwt.token-validity}")
    private long tokenValidity;

    /**
     * JwtConfig constructor
     *
     * @param customUserDetailsService CustomUserDetailsService
     */
    public JwtConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }


    /**
     * JwtTokenProvider bean is used to generate and validate JWT tokens
     *
     * @return JwtTokenProvider bean
     */
    @Bean
    public JwtTokenProvider jwtTokenProvider() {
        return new JwtTokenProvider(secretKey, tokenValidity, customUserDetailsService);
    }
}
