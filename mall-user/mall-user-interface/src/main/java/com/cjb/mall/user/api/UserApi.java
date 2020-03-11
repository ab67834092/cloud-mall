package com.cjb.mall.user.api;

import com.cjb.mall.user.vo.UserVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param pwd
     * @return
     */
    @GetMapping("query")
    UserVo queryUser(@RequestParam("username") String username, @RequestParam("pwd") String pwd);
}
