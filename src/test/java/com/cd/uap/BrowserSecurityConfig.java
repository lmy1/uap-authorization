package com.cd.uap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration		//@Configuration可理解为用spring的时候xml里面的<beans>标签,相当于指定当前类是一个配置文件
					//@Bean可理解为用spring的时候xml里面的<bean>标签
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {	//web端安全配置适配器
	
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
//		http.httpBasic()	//指定基础登录方式
		http.formLogin()	//指定登录方式为表单
			.loginPage("/login.html")
			.failureHandler(authenticationFailureHandler)
			.and()
			.authorizeRequests()	//下面的都是授权的配配置
			.antMatchers("/login.html").permitAll()
			.anyRequest()			//所有的请求
			.authenticated()		//都要进行身份认证
			.and()
			.csrf().disable();		//springsecurity默认有跨域保护，该配置是给禁掉
	}
	
}
