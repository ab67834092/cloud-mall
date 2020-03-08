package com.cjb.mall.common.redis.key;

public class UserCacheKey {

    //每个手机号一天内发送注册短信的次数限制
    public static final String SEND_REG_VCODE_ONE_DAY_LIMIT = "user:reg:vcode:oneday:limit:";
    //每个手机号一分钟内只能发送一次
    public static final String SEND_REG_VCODE_ONE_MINUTE_LIMIT = "user:reg:vcode:minute:limit:";
    //手机号注册验证码
    public static final String REG_VCODE = "user:reg:vcode:";
}
