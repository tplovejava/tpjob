package com.tp.soft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegisterRunMain {
    public static void main(String[] args) {
        SpringApplication.run(RegisterRunMain.class, args);
    }
}
