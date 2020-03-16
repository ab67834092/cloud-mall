package com.cjb.mall.elastic.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MallElasticJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallElasticJobApplication.class, args);
    }

}
