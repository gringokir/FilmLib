package com.filmlib;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class FilmLibApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmLibApplication.class, args);
    }

}
