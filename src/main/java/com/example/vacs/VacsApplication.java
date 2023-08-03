package com.example.vacs;


import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class VacsApplication {


    public static void main(String[] args) {
        SpringApplication.run(VacsApplication.class, args);
    }

}
