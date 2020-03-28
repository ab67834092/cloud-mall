package com.cjb.mall.rocketmq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallRocketmqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallRocketmqConsumerApplication.class, args);
    }

}
