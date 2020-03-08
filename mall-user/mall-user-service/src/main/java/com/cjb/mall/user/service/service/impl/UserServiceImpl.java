package com.cjb.mall.user.service.service.impl;

import com.cjb.mall.common.exception.BizException;
import com.cjb.mall.common.redis.key.UserCacheKey;
import com.cjb.mall.common.redis.template.CacheTemplate;
import com.cjb.mall.common.utils.NumberUtils;
import com.cjb.mall.user.service.mapper.UserMapper;
import com.cjb.mall.user.service.po.User;
import com.cjb.mall.user.service.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cjb on 2019/1/23.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    CacheTemplate cacheTemplate;

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${mq.direct.sms.exchange}")
    private String exchange;

    @Value("${mq.direct.sms.reg.vcode.routingKey}")
    private String routingKey;

    @Override
    public boolean checkPhoneCanReg(String phone) {
        User user = userMapper.getUserByParam(new User(phone));
        if(user==null){
            return true;
        }
        return false;
    }

    /**
     * 发送注册手机验证码
     * 1、每个手机号一天只能发送10次
     * 2、每个手机号1分钟只能发送1次
     * @param phone
     */
    @Override
    public void sendRegPhoneVCode(String phone) {
        String limit = cacheTemplate.get(UserCacheKey.SEND_REG_VCODE_ONE_DAY_LIMIT + phone);
        if(Integer.parseInt(limit)>=10){
            throw new BizException("每个手机号一天只能发送10次");
        }
        String str = cacheTemplate.get(UserCacheKey.SEND_REG_VCODE_ONE_MINUTE_LIMIT + phone);
        if(StringUtils.isEmpty(str)){
            throw new BizException("每个手机号1分钟只能发送1次验证码");
        }
        String vcode = NumberUtils.generateCode(6);
        //5分钟内有效
        cacheTemplate.set(UserCacheKey.REG_VCODE+phone,vcode,5*60);
        cacheTemplate.set(UserCacheKey.SEND_REG_VCODE_ONE_MINUTE_LIMIT + phone,vcode,60);
        cacheTemplate.incr(UserCacheKey.SEND_REG_VCODE_ONE_DAY_LIMIT + phone,1);

        //通过消息队列发送
        //向mq中发送消息
        Map<String,String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", vcode);
        amqpTemplate.convertAndSend(exchange, routingKey, map);
    }
}
