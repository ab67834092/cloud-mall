package com.cjb.mall.common.user;

public class UserInfo {

    private Integer id;
    private String name;

    public UserInfo() {
    }

    public UserInfo(Integer id, String name) {
        this.id = id;
        this.name = name;
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
