package com.tp.soft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tp.soft.*.dao")
public class SpringApplicationMain {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplicationMain.class, args);
    }
}
