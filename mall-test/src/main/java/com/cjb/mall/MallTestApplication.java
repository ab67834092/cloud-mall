package com.cjb.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallTestApplication.class, args);
    }

}
