package com.cjb.mall.user.service.controller;

import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.result.ResultVO;
import com.cjb.mall.user.service.service.UserService;
import com.cjb.mall.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/user")
public class UserController {


    @Autowired
    private UserService userService;

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
     * 查询用户
     * @return
     */
    @GetMapping("query")
    public UserVo queryUser(@RequestParam("username") String username, @RequestParam("pwd") String pwd){
        UserVo userVo = userService.queryUser(username, pwd);
        return userVo;
    }

}
