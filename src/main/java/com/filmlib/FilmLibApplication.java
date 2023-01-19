package com.filmlib;

import com.filmlib.util.Secret;
import com.filmlib.util.SecurityUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Secret.class)
public class FilmLibApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmLibApplication.class, args);
    }

}
