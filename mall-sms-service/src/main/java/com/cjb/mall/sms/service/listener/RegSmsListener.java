package com.cjb.mall.sms.service.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Map;

@Component
public class RegSmsListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "${mq.direct.sms.reg.vcode.queue}"),
            exchange = @Exchange(name = "${mq.direct.sms.exchange}", type = ExchangeTypes.DIRECT),
            key = "${mq.direct.sms.reg.vcode.routingKey}"
    ))
    public void listenRegVerifyCode(Map<String, Object> msg) {
        if (msg == null) {
            return;
        }
        String phone = (String) msg.get("phone");
        if (StringUtils.isEmpty(phone)) {
            return;
        }
        //这里是发送短信代码
        System.out.println("已为手机"+phone+",发送验证码："+msg.get("code"));
    }
}
