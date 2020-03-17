//package com.cjb.mall.elastic.job.config;
//
//import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
//import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ElasticJobRegistryCenterConfig {
//
//    private static final int ZK_PORT=2181;
//
//    private static final String ZK_CONNECTION_STRING="localhost:"+ZK_PORT;
//
//    private static final String JOB_NAMESPACE="job1";
//
//    //zk的配置
//    @Bean(initMethod = "init")
//    private static CoordinatorRegistryCenter setUpRegistryCenter(){
//        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration(ZK_CONNECTION_STRING,JOB_NAMESPACE);
//        zookeeperConfiguration.setSessionTimeoutMilliseconds(60000);
//
//        CoordinatorRegistryCenter coordinatorRegistryCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
//        return coordinatorRegistryCenter;
//    }
//}
