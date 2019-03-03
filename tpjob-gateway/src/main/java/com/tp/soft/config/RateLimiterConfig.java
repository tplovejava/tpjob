package com.tp.soft.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

/**
 * @Author: taop
 * @Date: 2019/3/3 上午11:44
 * @Version 1.0
 */

@Configuration
public class RateLimiterConfig {
    @Bean(value = "remoteAddrKeyResolver")
    public KeyResolver remoteAddrKeyResolver() {
        return exchange ->
                Mono.just(
                        exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
