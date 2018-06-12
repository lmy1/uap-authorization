package com.cd.uap;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cd.uap.bean.Response;
import com.cd.uap.bean.ResultCode;


@ControllerAdvice
public class ControllerExceptionHandler {
	
	/**
	 * 定义MyException异常的处理方式，下面可以定义多个方法
	 * 
	 * @param e
	 * @return
	 */
	@ExceptionHandler(Exception.class)	//指定该方法捕获的异常
	@ResponseStatus(HttpStatus.OK)		//这里可以指定返回状态码，HttpStatus是状态码枚举，但是我使用的是自定义的错误码，所以返回200
	@ResponseBody
	public Response handleMyException(Exception e) {	//这里要和上面异常匹配
		
		Response response = new Response(1, ResultCode.FAILED_LOGIC, null);
		return response;
	}
	
	
}
