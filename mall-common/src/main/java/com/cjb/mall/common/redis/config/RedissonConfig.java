package com.cjb.mall.common.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by chenjiabao on 2020/1/1.
 */
@Configuration
public class RedissonConfig {

    private int standalone = 1;

    @Bean
    public Config config(){
        //单机
        Config config = new Config();
        if(standalone==1){
            SingleServerConfig singleSerververConfig = config.useSingleServer();
            singleSerververConfig.setAddress("redis://localhost:6379");
            singleSerververConfig.setPassword("123456");
        }else{
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
//            clusterServersConfig.set
        }
        return config;
    }

    @Bean
    public RedissonClient redissonClient(){
        RedissonClient redisson = Redisson.create(config());
        return redisson;
    }
}
