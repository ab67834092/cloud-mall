package com.cjb.mall.user.service.controller;

import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.result.ResultVO;
import com.cjb.mall.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        if(!userService.checkPhoneCanReg(phone)){
            return ResultUtils.error("该手机号已经被注册！");
        }
        userService.sendRegPhoneVCode(phone);
        return ResultUtils.ok();
    }
}
