package com.assey.zandi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");

        // Add the following line to serve static files from src/main/webapp
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/", "classpath:/resources/", "classpath:/META-INF/resources/", "file:src/main/webapp/");
    }
}
