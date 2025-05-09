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
                                "http://localhost:5500",
                                "https://dainty-cocada-4aadbb.netlify.app/",
                                "https://681a17a2683089282906e1c4--dainty-cocada-4aadbb.netlify.app/")
                        .allowedMethods("GET", "POST", "OPTIONS")
                        .allowedHeaders("*", "Content-Type");

            }
        };
    }

}
