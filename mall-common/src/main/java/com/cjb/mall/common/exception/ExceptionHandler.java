package com.cjb.mall.common.exception;

import com.cjb.mall.common.result.ResultUtils;
import com.cjb.mall.common.result.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 * @author wulinli
 * @date 2018/09/06
 */
@ControllerAdvice
public class ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	/**
	 * 统一异常处理方法，返回统一异常信息
	 * @param e 异常对象
	 * @param req 请求对象
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	@ResponseBody
	public Object exceptionHandler(Exception e, HttpServletRequest req) {
		logger.error("请求异常路径为:" + req.getServletPath() + "：", e.getMessage(), e);
		ResultVO<Object> result = ResultUtils.error();
		logger.error(e.getMessage(),e);
		if (e instanceof BizException) {
			result.setMsg(e.getMessage());
		}else{
			result.setMsg(e.getMessage());
		}
		return result;
	}


}