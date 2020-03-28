package com.cjb.mall.rocketmq.producer.template;

import com.aliyun.mq.http.MQProducer;
import com.aliyun.mq.http.model.TopicMessage;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.shade.com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mq模板实现类
 * @author jiabao.chen
 * @date 2020/3/28 10:19
 */
@Component
public class MqTemplateImple implements MqTemplate {

//    @Autowired
//    private Producer producer;

    @Autowired
    private MQProducer httpProducer;

    @Override
    public String send(String topic,String tagName,Object msg) {
//        Message message = new Message( topic,tagName, JSON.toJSONString(msg).getBytes());
//        SendResult sendResult = producer.send(message);
//        return sendResult.getMessageId();
        return null;
    }

    @Override
    public String sendHttp(String tagName, Object msg) {
        TopicMessage pubMsg = new TopicMessage(JSON.toJSONString(msg).getBytes(),tagName);
        // 同步发送消息，只要不抛异常就是成功
        TopicMessage pubResultMsg = httpProducer.publishMessage(pubMsg);
        return pubResultMsg.getMessageId();
    }
}
