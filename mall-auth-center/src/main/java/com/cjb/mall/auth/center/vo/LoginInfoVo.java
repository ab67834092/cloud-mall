package com.cjb.mall.auth.center.vo;

public class LoginInfoVo {

    private String token;

    private String refreshToken;

    public LoginInfoVo() {
    }

    public LoginInfoVo(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
