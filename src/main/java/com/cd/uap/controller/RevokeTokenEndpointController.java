package com.cd.uap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cd.uap.bean.Response;
import com.cd.uap.bean.ResultCode;


/**
 * 注销access_token
 */
@FrameworkEndpoint
public class RevokeTokenEndpointController {
	@Autowired
	@Qualifier("consumerTokenServices")
	ConsumerTokenServices consumerTokenServices;

	@RequestMapping(method = RequestMethod.DELETE, value = "/oauth/token")
	@ResponseBody
	public Response revokeToken(String access_token) {
		if (consumerTokenServices.revokeToken(access_token)){
			return new Response(0, ResultCode.SUCCESS, null);
		}else{
			return new Response(1, ResultCode.FAILED_LOGOUT, null);
		}
	}

}
