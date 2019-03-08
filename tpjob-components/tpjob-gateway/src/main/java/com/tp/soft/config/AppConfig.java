package com.tp.soft.config;


import com.tp.soft.filter.AuthTokenFilter;
import com.tp.soft.filter.UserTokenGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public AuthTokenFilter tokenFilter(){
        return new AuthTokenFilter();
    }

    @Bean
    public UserTokenGatewayFilterFactory userTokenGatewayFilterFactory(){
        return new UserTokenGatewayFilterFactory();
    }
}