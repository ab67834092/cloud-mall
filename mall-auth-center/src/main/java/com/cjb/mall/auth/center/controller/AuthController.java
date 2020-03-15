package com.cjb.mall.auth.center.controller;

import com.cjb.mall.auth.center.service.AuthService;
import com.cjb.mall.auth.center.vo.LoginInfoVo;
import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.result.ResultVO;
import com.cjb.mall.common.utils.CookieUtils;
import com.cjb.mall.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * 用户名密码登录授权
     * @param pwd
     * @return
     */
    @PostMapping("login")
    public ResultVO login(String phone, String pwd, HttpServletRequest request,
                          HttpServletResponse response) {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
            return ResultUtils.error("参数错误！");
        }
        LoginInfoVo vo = authService.login(phone, pwd);
        System.out.println("token:"+vo.getToken());
        System.out.println("refreshToken:"+vo.getRefreshToken());
        return ResultUtils.ok(vo);
    }

    /**
     * 刷新token
     * @param refreshToken
     * @return
     */
    @PostMapping("refreshToken")
    public ResultVO refreshToken(String refreshToken) {
        LoginInfoVo loginInfoVo = authService.refreshToken(refreshToken);
        System.out.println(loginInfoVo.getToken());
        System.out.println(loginInfoVo.getRefreshToken());
        return ResultUtils.ok(loginInfoVo);
    }


    /**
     * 注销登录
     * @param token
     * @return
     */
    @PostMapping("logout")
    public ResultVO logout(String token) {
        return ResultUtils.ok();
    }
}
