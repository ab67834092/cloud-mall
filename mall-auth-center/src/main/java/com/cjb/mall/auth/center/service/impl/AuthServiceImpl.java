package com.cjb.mall.auth.center.service.impl;

import com.cjb.mall.auth.center.client.UserClient;
import com.cjb.mall.auth.center.config.JwtConfig;
import com.cjb.mall.auth.center.service.AuthService;
import com.cjb.mall.common.exception.BizException;
import com.cjb.mall.common.user.UserInfo;
import com.cjb.mall.common.utils.JwtUtils;
import com.cjb.mall.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserClient userClient;

    @Autowired
    JwtConfig jwtConfig;

    @Override
    public String login(String phone, String pwd) {
        UserVo userVo = userClient.queryUser(phone, pwd);
        if(userVo==null){
            throw new BizException("用户名或密码错误！");
        }
        UserInfo userInfo = new UserInfo(userVo.getId(), userVo.getTelephone());
        //生成Token
        String token = JwtUtils.generateToken(userInfo, jwtConfig.getPrivateKey(), jwtConfig.getExpire());
        return token;
    }
}
