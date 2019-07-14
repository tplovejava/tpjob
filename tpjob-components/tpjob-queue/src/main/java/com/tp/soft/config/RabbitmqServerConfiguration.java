package com.tp.soft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: taop
 * @Date: 2019/6/9 上午11:49
 * @Version 1.0
 */
@Configuration
public class RabbitmqServerConfiguration {
    public RabbitmqServerConfiguration() {
    }

    @Bean
    public RabbitmqServerConfiguration.Marker enableConfigServerMarker() {
        return new RabbitmqServerConfiguration.Marker();
    }

    class Marker {
        Marker() {
        }
    }

}
