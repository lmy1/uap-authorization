package com.cd.uap.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.cd.uap.util.MD5Util;


/**
 * 密码加密类
 * @author li.mingyang
 *
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/**
	 * 密码加密，在密码存入数据库时需要****人为****调用
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		String encodeMD5 = null;
		try {
			encodeMD5 = MD5Util.md5Digest((String) rawPassword);
		} catch (Exception e) {
			logger.error("MD5加密错误");
		}
		return encodeMD5;
	}

	/**
	 * 匹配登录和数据库中的密码是否一致，是****springsecurity****自动调用的,调用时机就是在UserDetailsService实现类返回User的时候将返回值中的密码和传入的密码进行比对
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			// TODO 可能报错
			String password = (String)encodedPassword;
			if (password != null && password.matches("[0-9]{6}")) {
				if (null == rawPassword) {
					return false;
				} else if (rawPassword.equals(encodedPassword)) {
					return true;
				} else {
					return false;
				}
			}
			
			String encodeMD5 = MD5Util.md5Digest((String)rawPassword);
			if (null != encodeMD5 && encodeMD5.equals(encodedPassword)) {
				return true;
			}
		} catch (Exception e) {
			logger.error("MD5加密错误");
		}
		return false;
	}

}
