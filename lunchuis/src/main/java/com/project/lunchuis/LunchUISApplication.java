package com.project.lunchuis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.lunchuis")
public class LunchUISApplication {
    public static void main(String[] args) {
        SpringApplication.run(LunchUISApplication.class, args);
    }
}

