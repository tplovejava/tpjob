package com.tp.soft.config;

import com.tp.soft.config.RabbitmqServerConfiguration.Marker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: taop
 * @Date: 2019/6/9 下午12:58
 * @Version 1.0
 */
@Configuration
@ConditionalOnBean({Marker.class})
@EnableConfigurationProperties(RabbitmqConfigurationProperties.class)
public class RabbitmqConfig {

    private Logger logger = LoggerFactory.getLogger(RabbitmqConfig.class);

    @Autowired
    private RabbitmqConfigurationProperties rabbitmqConfigurationProperties;

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(rabbitmqConfigurationProperties.getHost());
        connectionFactory.setPort(rabbitmqConfigurationProperties.getPort());
        connectionFactory.setUsername(rabbitmqConfigurationProperties.getUsername());
        connectionFactory.setPassword(rabbitmqConfigurationProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitmqConfigurationProperties.getVirtualHost());
        connectionFactory.setChannelCacheSize(rabbitmqConfigurationProperties.getChannelCacheSize());
        connectionFactory.setPublisherConfirms(true);
        connectionFactory.setPublisherReturns(true);
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin(){
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
        rabbitTemplate.setMandatory(true);
        /*rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            }
        });*/

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                logger.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",exchange,routingKey,replyCode,replyText,message);
            }
        });
        return rabbitTemplate;
    }

    //配置消费者监听的容器
    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(rabbitmqConfigurationProperties.getConcurrentConsumers());
        factory.setMaxConcurrentConsumers(rabbitmqConfigurationProperties.getMaxConcurrentConsumers());
        factory.setPrefetchCount(rabbitmqConfigurationProperties.getPrefetchCount());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);//设置确认模式手工确认

        return factory;
    }

}
