package com.tp.soft.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: taop
 * @Date: 2019/6/9 下午12:58
 * @Version 1.0
 */
@ConfigurationProperties(prefix = "mq.rabbitmq")
public class RabbitmqConfigurationProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
    private String virtualHost;
    private Integer channelCacheSize;

    private Integer concurrentConsumers;
    private Integer maxConcurrentConsumers;
    private Integer prefetchCount;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVirtualHost() {
        return virtualHost;
    }

    public void setVirtualHost(String virtualHost) {
        this.virtualHost = virtualHost;
    }

    public Integer getConcurrentConsumers() {
        return concurrentConsumers;
    }

    public void setConcurrentConsumers(Integer concurrentConsumers) {
        this.concurrentConsumers = concurrentConsumers;
    }

    public Integer getMaxConcurrentConsumers() {
        return maxConcurrentConsumers;
    }

    public void setMaxConcurrentConsumers(Integer maxConcurrentConsumers) {
        this.maxConcurrentConsumers = maxConcurrentConsumers;
    }

    public Integer getPrefetchCount() {
        return prefetchCount;
    }

    public void setPrefetchCount(Integer prefetchCount) {
        this.prefetchCount = prefetchCount;
    }

    public Integer getChannelCacheSize() {
        return channelCacheSize;
    }

    public void setChannelCacheSize(Integer channelCacheSize) {
        this.channelCacheSize = channelCacheSize;
    }
}
