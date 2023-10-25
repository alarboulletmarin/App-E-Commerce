package com.formation.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    // @Autowired
    // public void configureSwaggerUi(SwaggerUiConfigProperties swaggerUi) {
    //     swaggerUi.setUrl("/api-docs");
    // }


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("My API").version("1.0").description("My Custom API"));
    }
}
