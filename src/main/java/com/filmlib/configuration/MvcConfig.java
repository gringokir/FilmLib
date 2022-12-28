package com.filmlib.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    public void addViewControllers(ViewControllerRegistry registry) {
    }

   /* @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/view/react/build/static/");
        registry.addResourceHandler("/*.js")
                .addResourceLocations("/WEB-INF/view/react/build/");
        registry.addResourceHandler("/*.json")
                .addResourceLocations("/WEB-INF/view/react/build/");
        registry.addResourceHandler("/*.ico")
                .addResourceLocations("/WEB-INF/view/react/build/");
        registry.addResourceHandler("/index.html")
                .addResourceLocations("/WEB-INF/view/react/build/index.html");
    }*/

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*")
                        .allowedHeaders("*");
            }
        };
    }
}
