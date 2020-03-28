package com.cjb.mall.rocketmq.consumer.config;

import com.aliyun.mq.http.MQClient;
import com.aliyun.mq.http.MQConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiabao.chen
 * @date 2020/3/28 13:00
 */
@Configuration
public class MqConfig {

    @Bean
    public MQConsumer consumer(){
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
        // 您在控制台创建的 Group ID
        final String groupId = "GID_http_test";
        // Topic所属实例ID，默认实例为空
        final String instanceId = "MQ_INST_1835564177508720_BcQeFOD4";

        final MQConsumer consumer;
        if (instanceId != null && instanceId != "") {
            consumer = mqClient.getConsumer(instanceId, topic, groupId, null);
        } else {
            consumer = mqClient.getConsumer(topic, groupId);
        }
        return consumer;
    }
}
