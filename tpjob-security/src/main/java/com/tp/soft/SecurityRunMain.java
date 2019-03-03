package com.tp.soft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringCloudApplication
@EnableEurekaClient
@MapperScan("com.tp.soft.*.dao")
public class SecurityRunMain {

    public static void main(String[] args) {
        SpringApplication.run(SecurityRunMain.class, args);
    }
}
