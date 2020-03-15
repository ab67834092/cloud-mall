package com.cjb.mall.auth.center.service.impl;

import com.cjb.mall.auth.center.client.UserClient;
import com.cjb.mall.auth.center.config.JwtConfig;
import com.cjb.mall.auth.center.service.AuthService;
import com.cjb.mall.auth.center.vo.LoginInfoVo;
import com.cjb.mall.common.exception.BizException;
import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.user.UserInfo;
import com.cjb.mall.common.utils.JwtUtils;
import com.cjb.mall.user.vo.UserVo;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwt;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserClient userClient;

    @Autowired
    JwtConfig jwtConfig;

    @Override
    public LoginInfoVo login(String phone, String pwd) {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
            throw new BizException("参数错误！");
        }
        UserVo userVo = userClient.queryUser(phone, pwd);
        if(userVo==null){
            throw new BizException("用户名或密码错误！");
        }
        UserInfo userInfo = new UserInfo(userVo.getId());
        DateTime currentDateTime = DateTime.now();
        //生成Token
        String token = JwtUtils.generateToken(userInfo, jwtConfig.getPrivateKey(), currentDateTime.plusMinutes(jwtConfig.getExpire()).toDate());
        //生成刷新token
        String refreshToken = JwtUtils.generateToken(userInfo, jwtConfig.getPrivateKey(), currentDateTime.plusMinutes(jwtConfig.getRefreshExpire()).toDate());
        return new LoginInfoVo(token,refreshToken);
    }

    @Override
    public LoginInfoVo refreshToken(String refreshToken) {
        if(StringUtils.isEmpty(refreshToken)){
            throw new BizException("参数错误");
        }
        try{
            UserInfo userInfo = JwtUtils.getUserInfo(jwtConfig.getPublicKey(), refreshToken);
            DateTime currentDateTime = DateTime.now();
            //生成新的Token
            String token = JwtUtils.generateToken(userInfo, jwtConfig.getPrivateKey(), currentDateTime.plusMinutes(jwtConfig.getExpire()).toDate());
            //生成刷新token
            String newRefreshToken = JwtUtils.generateToken(userInfo, jwtConfig.getPrivateKey(), currentDateTime.plusMinutes(jwtConfig.getRefreshExpire()).toDate());
            return new LoginInfoVo(token,newRefreshToken);
        }catch (ExpiredJwtException e){
            throw new BizException(1000,"请重新登录");
        }catch (Exception e){
            throw new BizException("token非法");
        }
    }
}
