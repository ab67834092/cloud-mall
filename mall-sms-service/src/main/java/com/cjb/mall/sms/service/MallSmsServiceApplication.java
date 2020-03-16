package com.cjb.mall.sms.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallSmsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallSmsServiceApplication.class, args);
    }

}
