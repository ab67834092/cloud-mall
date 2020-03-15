package com.cjb.mall.common.user;

import java.util.Date;

public class UserInfo {

    private Integer id;
    private String name;
    private Date tokenExpire;

    public UserInfo(Integer id) {
        this.id = id;
    }

    public UserInfo() {
    }

    public UserInfo(Integer id, Date tokenExpire) {
        this.id = id;
        this.tokenExpire = tokenExpire;
    }

    public UserInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserInfo(Integer id, String name, Date tokenExpire) {
        this.id = id;
        this.name = name;
        this.tokenExpire = tokenExpire;
    }

    public Date getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Date tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
