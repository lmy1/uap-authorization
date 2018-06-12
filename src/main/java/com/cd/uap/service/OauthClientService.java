package com.cd.uap.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.cd.uap.dao.ApplicationDao;
import com.cd.uap.entity.Application;

@Service
public class OauthClientService implements ClientDetailsService {
	
	@Autowired
	ApplicationDao applicationDao;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		//通过clientId查找application应用对象
		Application application = applicationDao.findByClientId(clientId);
		if(application == null) {
			throw new ClientRegistrationException("clientId不存在");
		}
		System.out.println("ClientId："+application.getClientId()+" ===== ClientSecret："+application.getSecret());
		
		BaseClientDetails baseClientDetails = new BaseClientDetails();
		baseClientDetails.setClientId(application.getClientId());//ClientId必填
		baseClientDetails.setClientSecret(application.getSecret());//ClientSecret必填
		
		//Scope必填
		List<String> scopeList = new ArrayList<String>();
		scopeList.add("read");
		scopeList.add("write");
		baseClientDetails.setScope(scopeList);
		
		//grantType选填
		List<String> grantTypeList = new ArrayList<String>();
		grantTypeList.add("password");
		grantTypeList.add("refresh_token");
		grantTypeList.add("client_credentials");
		baseClientDetails.setAuthorizedGrantTypes(grantTypeList);
		//设置access_token有效时间
		baseClientDetails.setAccessTokenValiditySeconds(60*60*24*7);
		//设置刷新码
		baseClientDetails.setRefreshTokenValiditySeconds(60*60*24*30);
		
		return baseClientDetails;
	}

}
