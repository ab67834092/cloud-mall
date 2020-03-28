package com.cjb.mall.rocketmq.producer.config;

import com.aliyun.mq.http.MQClient;
import com.aliyun.mq.http.MQProducer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * mq配置类
 * @author jiabao.chen
 * @date 2020/3/28 9:57
 */
@Configuration
public class MqConfig {

//    /**
//     * TCP使用
//     * @return
//     */
//    @Bean
//    public Producer producer(){
//        Properties producerProperties = new Properties();
//        producerProperties.setProperty(PropertyKeyConst.GROUP_ID, "GID_http_test");
//        producerProperties.setProperty(PropertyKeyConst.AccessKey, "LTAI4FshCfBSKoKLFsigxGLw");
//        producerProperties.setProperty(PropertyKeyConst.SecretKey,"u6LZLeVzR2tYKPXLahHisldtpR1qMF");
//        producerProperties.setProperty(PropertyKeyConst.NAMESRV_ADDR, "http://MQ_INST_1835564177508720_BcQeFOD4.cn-zhangjiakou.mq-internal.aliyuncs.com:8080");
//        // 普通消息的生产者实例
//        Producer producer = ONSFactory.createProducer(producerProperties);
//        producer.start();
//        return producer;
//    }

    @Bean
    public MQProducer producer(){
        MQClient mqClient = new MQClient(
                // 设置HTTP接入域名（此处以公共云生产环境为例）
                "http://1835564177508720.mqrest.cn-zhangjiakou.aliyuncs.com",
                // AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
                "LTAI4FshCfBSKoKLFsigxGLw",
                // SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
                "u6LZLeVzR2tYKPXLahHisldtpR1qMF"
        );
        // 所属的 Topic
        final String topic = "cjb-common-msg-test";
        // Topic所属实例ID，默认实例为空
        final String instanceId = "MQ_INST_1835564177508720_BcQeFOD4";
        // 获取Topic的生产者
        MQProducer producer;
        if (!StringUtils.isEmpty(instanceId)) {
            producer = mqClient.getProducer(instanceId, topic);
        } else {
            producer = mqClient.getProducer(topic);
        }
        return producer;
    }
}
