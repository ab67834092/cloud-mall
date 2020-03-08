package com.cjb.mall.common.result;

/**
 * 统一的返回结果
 */
public class ResultVO<T>{
	
    private int code;
    private String msg;
    private T result;
    
    public ResultVO(T result){
        this(ResultCodeEnum.SUCCESS,result);
    }

    public ResultVO(ResultCodeEnum resultCodeEnum, T result){
        this.result=result;
        this.code=resultCodeEnum.getCode();
        this.msg=resultCodeEnum.getMsg();
    }

    public ResultVO(Integer code, String message){
        this.code=code;
        this.msg=message;
    }

    public ResultVO(Integer code, String message, T result){
        this.code=code;
        this.msg=message;
        this.result=result;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static<T> ResultVO<T> createSuccess(T result){
        return new ResultVO(result);
    }

    public boolean isOk(){
        if(ResultCodeEnum.SUCCESS.getCode() == this.getCode())
            return true;
        else
            return false;
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

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
