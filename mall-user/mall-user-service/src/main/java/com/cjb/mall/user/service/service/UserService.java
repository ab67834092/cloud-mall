package com.cjb.mall.user.service.service;

/**
 * Created by cjb on 2019/1/23.
 */
public interface UserService {

    /**
     * 验证手机号是否已被注册
     * @param phone
     */
    boolean checkPhoneCanReg(String phone);

    /**
     * 发送短信验证码
     * @param phone
     */
    void sendRegPhoneVCode(String phone);




}
