package com.cjb.mall.common.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BizException extends RuntimeException{

	/**
	 * 具体异常码
	 */
	private int code;

	/**
	 * 异常信息
	 */
	private String msg;

	public BizException(){
		super();
	}

	public BizException(int code, String msg){
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public BizException(String msg) {
		super(msg);
		this.code = 500;
		this.msg = msg;
	}


	
}
