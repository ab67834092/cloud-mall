package com.cjb.mall.user.service.controller;

import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.result.ResultVO;
import com.cjb.mall.common.utils.CookieUtils;
import com.cjb.mall.user.service.config.JwtConfig;
import com.cjb.mall.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private JwtConfig jwtConfig;

    /**
     * 生成图片验证码(需要时候使用)
     * @param phone
     * @return
     */
    @PostMapping("createPhoneImgVCode")
    public void createPhoneImgVCode(String phone){

    }

    /**
     * 发送注册手机验证码
     * @param phone
     * @return
     */
    @PostMapping("sendRegPhoneVCode")
    public ResultVO sendRegPhoneVCode(String phone){
        if(StringUtils.isEmpty(phone)){
            return ResultUtils.error("注册手机号不能为空！");
        }
        userService.sendRegPhoneVCode(phone);
        return ResultUtils.ok();
    }

    /**
     * 用户注册
     * @param phone
     * @param vcode
     * @return
     */
    @PostMapping("register")
    public ResultVO register(String phone,String vcode,String pwd) {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(vcode) || StringUtils.isEmpty(pwd)){
            return ResultUtils.error("参数错误！");
        }
        userService.register(phone,vcode,pwd);
        return ResultUtils.ok();
    }

    /**
     * 用户名密码登录
     * @param pwd
     * @return
     */
    @PostMapping("login")
    public ResultVO login(String phone,String pwd,HttpServletRequest request,
                          HttpServletResponse response) {
        if(StringUtils.isEmpty(phone) || StringUtils.isEmpty(pwd)){
            return ResultUtils.error("参数错误！");
        }
        String token = userService.login(phone, pwd);
        //将Token写入cookie中
        CookieUtils.newBuilder(response).httpOnly().maxAge(jwtConfig.getCookieMaxAge()).request(request).build(jwtConfig.getCookieName(), token);
        return ResultUtils.ok();
    }


    /**
     * 注销登录
     *
     * @param token
     * @param response
     * @return
     */
    @GetMapping("logout")
    public ResultVO logout(@CookieValue("MALL_TOKEN") String token, HttpServletResponse response) {
        if (!StringUtils.isEmpty(token)) {
            CookieUtils.newBuilder(response).maxAge(0).build(jwtConfig.getCookieName(), token);
        }
        return ResultUtils.ok();
    }
}
