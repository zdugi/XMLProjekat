package com.xmlproject.project_poverenik.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class RestConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //update: for some reason DELETE is only blocked, so I just added manually
        registry.addMapping("/**").allowedOrigins("https://localhost:8081")
                .allowedMethods("PUT", "POST", "DELETE", "GET");
    }




}