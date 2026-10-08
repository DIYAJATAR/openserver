package com.diya.openserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OpenserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenserverApplication.class, args);
    }
}