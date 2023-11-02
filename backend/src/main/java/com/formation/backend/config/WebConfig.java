package com.formation.backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String[] angularRoutes = {
            "/",
            "/products/**",
            "/categories/**",
            "/login",
            "/register",
    };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

        registry.addResourceHandler("/img/**")
                .addResourceLocations("classpath:/public/img/")
                .setCacheControl(CacheControl.maxAge(7, TimeUnit.DAYS));
    }

    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
        registry.addRedirectViewController("/api", "/swagger-ui/");

        Arrays.stream(angularRoutes).forEach(route -> registry.addViewController(route).setViewName("forward:/index.html"));
    }

}