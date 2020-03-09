package com.cjb.mall.user.service.service.impl;

import com.cjb.mall.common.exception.BizException;
import com.cjb.mall.common.redis.key.UserCacheKey;
import com.cjb.mall.common.redis.template.CacheTemplate;
import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.user.UserInfo;
import com.cjb.mall.common.utils.*;
import com.cjb.mall.user.service.config.JwtConfig;
import com.cjb.mall.user.service.mapper.UserMapper;
import com.cjb.mall.user.service.po.User;
import com.cjb.mall.user.service.service.UserService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
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

    @Autowired
    private JwtConfig jwtConfig;

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
        User userInDb = userMapper.getUserByParam(new User(phone));
        if(userInDb!=null){
            throw new BizException("该手机号已经被注册！");
        }

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
        if(!cacheTemplate.hasKey(UserCacheKey.SEND_REG_VCODE_ONE_DAY_LIMIT + phone)){
            cacheTemplate.set(UserCacheKey.SEND_REG_VCODE_ONE_DAY_LIMIT + phone,1, DateUtils.getRemainSecondsOneDay(new Date()));
        }else{
            cacheTemplate.incr(UserCacheKey.SEND_REG_VCODE_ONE_DAY_LIMIT + phone,1);
        }

        //通过消息队列发送
        //向mq中发送消息
        Map<String,String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", vcode);
        amqpTemplate.convertAndSend(exchange, routingKey, map);
    }

    @Override
    public void register(String phone, String vcode,String pwd) {
        User userInDb = userMapper.getUserByParam(new User(phone));
        if(userInDb!=null){
            throw new BizException("该手机号已经被注册！");
        }
        //对比验证码
        String serverVCode = cacheTemplate.get(UserCacheKey.REG_VCODE+phone);
        if(!serverVCode.equals(vcode)){
            throw new BizException("验证码不正确！");
        }
        User user = new User();
        BeanUtils.initBean(user);
        //生成盐
        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);
        //生成密码
        String md5Pwd = CodecUtils.md5Hex(pwd, user.getSalt());
        user.setPwd(md5Pwd);
        user.setTelephone(phone);
        user.setSex(0);
        user.setStatus(1);
        user.setSource(1);
        user.setValid(1);
        userMapper.insert(user);

        //把验证码从Redis中删除
        cacheTemplate.del(UserCacheKey.REG_VCODE+phone);
        cacheTemplate.del(UserCacheKey.SEND_REG_VCODE_ONE_DAY_LIMIT + phone);

    }

    @Override
    public String login(String phone, String pwd) {
        User record = new User();
        record.setTelephone(phone);
        //首先根据用户名查询用户
        User user = userMapper.getUserByParam(record);
        if (user == null) {
            throw new BizException("用户不存在！");
        }
        //检验密码是否正确
        if (!CodecUtils.md5Hex(pwd, user.getSalt()).equals(user.getPwd())) {
            //密码不正确
            throw new BizException("密码不正确！");
        }

        UserInfo userInfo = new UserInfo(user.getId(), user.getTelephone());
        //生成Token
        String token = JwtUtils.generateToken(userInfo, jwtConfig.getPrivateKey(), jwtConfig.getExpire());
        return token;
    }

    public static void main(String[] args) {
        User user = new User();
        BeanUtils.generateBeanSetCodeNoGet(user);
    }
}
