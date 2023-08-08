package com.example.ibmvacationrentalapi.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Mapeia todas as URLs
                .allowedOrigins("*") // Permite todas as origens
                .allowedMethods("*") // Permite todos os métodos (GET, POST, etc.)
                .allowedHeaders("*"); // Permite todos os headers
    }
}
