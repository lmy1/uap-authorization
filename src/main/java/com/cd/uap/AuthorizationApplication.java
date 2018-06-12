package com.cd.uap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;

import com.cd.uap.service.CustomUserService;
import com.cd.uap.service.OauthClientService;

@SpringBootApplication
@EnableAuthorizationServer	//发送token
public class AuthorizationApplication {
	
	/**
	 * 项目启动入口
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationApplication.class, args);
	}



/*	@Configuration
	static class LoginConfig extends WebSecurityConfigurerAdapter {
		
		@Autowired
		private AuthenticationFailureHandler myAuthenticationFailureHandler;
		
		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.
//				.formLogin().loginPage("/login") //指定登录页是"/login",并访问 /login无需登录认证权限
				.formLogin()
				.failureHandler(myAuthenticationFailureHandler)
				.and() 
				.requestMatchers()
				.antMatchers("/", "/login", "/oauth/authorize", "/oauth/confirm_access")//登陆后之后可以访问"/", "/login"等方法
				.and()
				.authorizeRequests()
				.anyRequest().authenticated(); //其他所有资源都需要认证，登陆后访问
		}
	}
	
	@Configuration
	static class LoginConfig extends AuthorizationServerConfigurerAdapter {
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer
	                .allowFormAuthenticationForClients().tokenKeyAccess("permitAll()")
	                .checkTokenAccess("isAuthenticated()") //allow check token
	                .allowFormAuthenticationForClients()
	                .;
	    }

	}
	
	@Profile("!cloud")
	@Bean
	RequestDumperFilter requestDumperFilter() {
		return new RequestDumperFilter();
	}
*/	
	/**
	 * 配置自定义的ClientId、ClientSecret
	 */
	@Configuration
	public static class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {
		@Autowired
		private AuthenticationManager authenticationManager;

		@Autowired
		private OauthClientService oauthClientService;
		
		@Autowired
		private CustomUserService customUserService;
		
		@Autowired
		private MyExceptionTranslator myExceptionTranslator;

		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager)
				.userDetailsService(customUserService)
				.exceptionTranslator(myExceptionTranslator);
		}

		/**
		 * 从数据库读取ClientId、ClientSecret
		 */
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients.withClientDetails(oauthClientService).clients(oauthClientService);
		}
		
		/**
		 * 令牌校验check_token
		 */
		@Override
		public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
			oauthServer.checkTokenAccess("permitAll()");
		}
	}

	
	@Configuration
	public static class MyExceptionTranslator implements WebResponseExceptionTranslator{

		@Override
		public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
			
			HttpHeaders headers= new HttpHeaders();
			headers.set("authenticateStatus", "1");
			ResponseEntity<OAuth2Exception> esponseEntity = new ResponseEntity<OAuth2Exception>(new OAuth2Exception(e.getMessage()), headers, HttpStatus.OK);//(new OAuth2Exception(e.getMessage()), HttpStatus.OK);
			return esponseEntity;
		}
	}
}



















