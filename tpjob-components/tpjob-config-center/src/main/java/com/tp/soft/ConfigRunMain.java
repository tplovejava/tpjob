package com.tp.soft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigRunMain {
    public static void main(String[] args) {
        SpringApplication.run(ConfigRunMain.class, args);
    }
}
