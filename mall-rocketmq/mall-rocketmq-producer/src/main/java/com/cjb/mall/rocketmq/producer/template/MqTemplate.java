package com.cjb.mall.rocketmq.producer.template;

/**
 * mq模板
 * @author jiabao.chen
 * @date 2020/3/28 10:18
 */
public interface MqTemplate {

    public String send(String topic,String tagName,Object msg);

    public String sendHttp(String tagName,Object msg);
}
