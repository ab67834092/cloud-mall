package com.cjb.mall.auth.center.service;

import com.cjb.mall.auth.center.vo.LoginInfoVo;

public interface AuthService {


    /**
     * 登录认证方法
     * @param phone
     * @param pwd
     * @return
     */
    LoginInfoVo login(String phone, String pwd);

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    LoginInfoVo refreshToken(String refreshToken);
}
