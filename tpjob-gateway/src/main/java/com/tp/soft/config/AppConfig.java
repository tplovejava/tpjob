package com.tp.soft.config;

import com.tp.soft.filter.JwtCheckGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public JwtCheckGatewayFilterFactory jwtCheckGatewayFilterFactory(){
        return new JwtCheckGatewayFilterFactory();
    }
}