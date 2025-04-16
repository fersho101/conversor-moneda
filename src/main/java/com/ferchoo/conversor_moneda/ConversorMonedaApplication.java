package com.ferchoo.conversor_moneda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableCaching
@SpringBootApplication
public class ConversorMonedaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConversorMonedaApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins(
                                "https://67ff44d354d743c72a41f629--warm-pasca-014f6f.netlify.app",
                                "http://localhost:5500",
                                "https://warm-pasca-014f6f.netlify.app/")
                        .allowedMethods("GET", "POST", "OPTIONS")
                        .allowedHeaders("*");

            }
        };
    }

}
