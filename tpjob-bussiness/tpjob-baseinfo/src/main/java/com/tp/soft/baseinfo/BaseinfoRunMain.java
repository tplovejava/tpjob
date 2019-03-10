package com.tp.soft.baseinfo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.codingapi.txlcn.tc.config.EnableDistributedTransaction;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: taop
 * @Date: 2019/3/10 下午4:14
 * @Version 1.0
 */
@SpringCloudApplication
@EnableFeignClients
@MapperScan("com.tp.soft.*.dao")
@EnableDistributedTransaction
@EnableAutoConfiguration(exclude = {DruidDataSourceAutoConfigure.class})
public class BaseinfoRunMain {
    public static void main(String[] args) {
        SpringApplication.run(BaseinfoRunMain.class, args);
    }
}
