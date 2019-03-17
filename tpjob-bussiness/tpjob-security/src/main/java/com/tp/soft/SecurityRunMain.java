package com.tp.soft;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@MapperScan("com.tp.soft.*.dao")
@EnableDistributedTransaction
public class SecurityRunMain {

    public static void main(String[] args) {
        SpringApplication.run(SecurityRunMain.class, args);
    }
}
