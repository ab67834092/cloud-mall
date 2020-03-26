package com.cjb.mall.caffeine.config;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class CaffeineConfig {

    //缓存最大数量
    private static final int CACHE_MAX_SIZE=1000;

    //最后一次写入后多长时间失效
    private static final int CACHE_EXPIRE_TIME_MINUTES = 1;

    @Bean(name = "productCache")
    public Cache ProductCache(){
        return Caffeine.newBuilder()
                .maximumSize(CACHE_MAX_SIZE)
                .expireAfterWrite(CACHE_EXPIRE_TIME_MINUTES, TimeUnit.MINUTES)
                .build();
    }

    @Bean(name = "userCache")
    public Cache UserCache(){
        return Caffeine.newBuilder()
                .maximumSize(CACHE_MAX_SIZE)
                .expireAfterWrite(CACHE_EXPIRE_TIME_MINUTES, TimeUnit.MINUTES)
                .build();
    }

}
