package com.cjb.mall.common.result;

/**
 * 统一结果封装类
 * @author wulinli
 * @date 2018/09/05
 */
public class ResultUtils {

	/**
	 * sucess 返回成功
	 * @return
	 */
	public static<T> ResultVO<T> ok(){
		ResultVO<T> vo = new ResultVO<T>(null);
		vo.setCode(ResultCodeEnum.SUCCESS.getCode());
		vo.setMsg(ResultCodeEnum.SUCCESS.getMsg());
		return vo;
	}

	/**
	 * success 返回业务成功
	 * @param t  数据信息
	 * @param msg 自定义msg
	 * @return
	 */
	public static<T> ResultVO<T> ok(T t,String msg){
		ResultVO<T> vo = new ResultVO<T>(t);
		vo.setCode(ResultCodeEnum.SUCCESS.getCode());
		vo.setMsg(msg);
		return vo;
	}

	/**
	 * sucess 返回成功
	 * @param t 数据
	 * @return
	 */
	public static<T> ResultVO<T> ok(T t){
		ResultVO<T> vo = new ResultVO<T>(t);
		vo.setCode(ResultCodeEnum.SUCCESS.getCode());
		vo.setMsg(ResultCodeEnum.SUCCESS.getMsg());
		return vo;
	}


	
	/**
	 * error 返回错误信息
	 * @return
	 */
	public static<T> ResultVO<T> error(){
		ResultVO<T> vo = new ResultVO<T>(null);
		vo.setCode(ResultCodeEnum.ERROR.getCode());
		vo.setMsg(ResultCodeEnum.ERROR.getMsg());
		return vo;
	}
	
	/**
	 * error 返回业务错误信息
	 * @param msg 自定义msg
	 * @return
	 */
	public static<T> ResultVO<T> error(String msg){
		ResultVO<T> vo = new ResultVO<T>(null);
		vo.setCode(ResultCodeEnum.ERROR.getCode());
		vo.setMsg(msg);
		return vo;
	}

}
