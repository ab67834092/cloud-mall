package com.cjb.mall.common.result;

/**
 * 返回结果状态枚举
 */
public enum ResultCodeEnum {
	
    SUCCESS(0,"操作成功！"),
    ERROR(1,"系统异常！"),
    LOG_TIMEOUT(1000,"登录超时");
	
    private int code;
    private String msg;
    
    ResultCodeEnum(int code, String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
