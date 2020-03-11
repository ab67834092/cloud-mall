package com.cjb.mall.auth.center.service;

public interface AuthService {


    /**
     * 登录认证方法
     * @param phone
     * @param pwd
     * @return
     */
    String login(String phone, String pwd);
}
