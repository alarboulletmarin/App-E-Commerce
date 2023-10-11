package com.formation.backend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/*
 * WebSecurityConfig class is used to configure the Spring Security
 * */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * WebSecurityConfig constructor
     */
    private final JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenFilter = new JwtTokenFilter(jwtTokenProvider);
    }

    /**
     * This method is used to configure the Spring Security
     *
     * @param http HttpSecurity object
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors().and()
                .authorizeRequests(requests -> requests
                        .antMatchers("/api/auth/signin", "/api/auth/register", "/api/auth/logout", "/v3/api-docs",
                                "/configuration/ui",
                                "/swagger-resources/**",
                                "/configuration/security",
                                "/swagger-ui/**",
                                "/webjars/**").permitAll()
                        .antMatchers("/api/users/**").hasRole("ADMIN")
                        .antMatchers("/api/users/**").hasRole("USER")
                        .antMatchers("/api/products/**", "/api/categories/**").permitAll()
                        .anyRequest().authenticated())
                .csrf(csrf -> csrf.disable());
    }

    /**
     * This method is used to configure the AuthenticationManager bean
     *
     * @return AuthenticationManager bean
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
