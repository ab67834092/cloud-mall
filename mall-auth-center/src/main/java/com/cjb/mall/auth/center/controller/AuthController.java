package com.cjb.mall.auth.center.controller;

import com.cjb.mall.auth.center.service.AuthService;
import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.result.ResultVO;
import com.cjb.mall.common.utils.CookieUtils;
import com.cjb.mall.common.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
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
        String token = authService.login(phone, pwd);
        return ResultUtils.ok(token);
    }


    /**
     * 注销登录
     *
     * @param token
     * @return
     */
    @PostMapping("logout")
    public ResultVO logout(String token) {
        return ResultUtils.ok();
    }
}
