package com.cjb.mall.rocketmq.producer.controller;

import com.cjb.mall.rocketmq.producer.template.MqTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jiabao.chen
 * @date 2020/3/28 12:15
 */
@RestController
public class TestRocketMqController {

    @Autowired
    MqTemplate mqTemplate;

//    @RequestMapping("common")
//    public void sendTcpCommonMsg(){
//        String hello_world_mq = mqTemplate.send("cjb-common-msg-test", "common-msg-test", "hello world mq");
//        System.out.println(hello_world_mq);
//    }

    @RequestMapping("common/{num}")
    public void sendHttpCommonMsg(@PathVariable int num){
        for(int i=0;i<num;i++){
            String hello_world_mq = mqTemplate.sendHttp("common-msg-test", "hello world mq");
            System.out.println(hello_world_mq);
        }
    }
}
