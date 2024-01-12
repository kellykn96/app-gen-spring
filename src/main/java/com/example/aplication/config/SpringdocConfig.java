package com.example.aplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.models.OpenAPI;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class SpringdocConfig implements WebMvcConfigurer {

    @Bean
    public OpenAPI customOpenAPI() throws IOException {
        Yaml yaml = new Yaml();
        ClassPathResource resource = new ClassPathResource("swagger.yaml");
        try (InputStream inputStream = resource.getInputStream()) {
            String yamlContent = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            return yaml.loadAs(yamlContent, OpenAPI.class);
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/META-INF/resources/");
    }
}
