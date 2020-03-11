package com.cjb.mall.user.service.service;

import com.cjb.mall.common.user.UserInfo;
import com.cjb.mall.user.vo.UserVo;

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

    /**
     * 注册
     * @param phone
     * @param vcode
     */
    void register(String phone, String vcode,String pwd);

    /**
     * 查询用户
     * @param username
     * @param pwd
     * @return
     */
    UserVo queryUser(String username, String pwd);
}
