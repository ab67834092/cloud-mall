package com.cjb.mall.user.service.po;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class User {
    
    private Integer id;

    private String telephone;

    private String pwd;

    private String salt;

    private String nickname;

    private String headUrl;

    private Integer sex;

    private Date regTime;

    private Integer status;

    private Integer source;

    private String sourceId;

    private String province;

    private String city;

    private String area;

    private String openId;

    private Integer subscribe;

    private String wxId;

    private Date createTime;

    private Date updateTime;

    private String createUser;

    private String updateUser;

    private Integer valid;

    public User(String telephone) {
        this.telephone = telephone;
    }

    public User() {
    }
}