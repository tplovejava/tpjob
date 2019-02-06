package com.tp.soft.config;

import com.tp.soft.filter.httpFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching//开启注解
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter(){
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new httpFilter());
        registration.addUrlPatterns("/*");
        registration.setName("HttpFilter");
        registration.setOrder(1);
        return registration;
    }
}
