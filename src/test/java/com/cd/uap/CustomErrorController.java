package com.cd.uap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cd.uap.bean.Response;
import com.cd.uap.bean.ResultCode;

@RestController
public class CustomErrorController {
	
	@RequestMapping(value = "/oauth/error", method = RequestMethod.POST)
	public Response customError() {
		return new Response(1, ResultCode.FAILED_LOGIN, null);
	}
}
