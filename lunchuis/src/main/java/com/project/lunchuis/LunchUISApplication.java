package com.project.lunchuis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScan
public class LunchUISApplication {
    public static void main(String[] args) {
        SpringApplication.run(LunchUISApplication.class, args);
    }
}

