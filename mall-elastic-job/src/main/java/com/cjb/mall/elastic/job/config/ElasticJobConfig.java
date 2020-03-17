//package com.cjb.mall.elastic.job.config;
//
//import com.cjb.mall.elastic.job.task.Job1;
//import com.dangdang.ddframe.job.api.simple.SimpleJob;
//import com.dangdang.ddframe.job.config.JobCoreConfiguration;
//import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
//import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
//import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
//import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
//import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class ElasticJobConfig {
//
//    @Autowired
//    SimpleJob job1;
//
//    @Autowired
//    CoordinatorRegistryCenter registryCenter;
//
//    private LiteJobConfiguration createJobConfiguration(){
//        // 创建作业配置
//        JobCoreConfiguration coreConfig = JobCoreConfiguration.newBuilder("job1", "0/3 * * * * ?", 3).build();
//        SimpleJobConfiguration dataflowJobConfig = new SimpleJobConfiguration(coreConfig, Job1.class.getCanonicalName());
//        LiteJobConfiguration result = LiteJobConfiguration.newBuilder(dataflowJobConfig).overwrite(true).build();
//        return result;
//    }
//
//    @Bean(initMethod = "init")
//    public SpringJobScheduler initSimpleElasticJob(){
//        SpringJobScheduler springJobScheduler = new SpringJobScheduler(job1, registryCenter, createJobConfiguration());
//        return springJobScheduler;
//
//    }
//}
