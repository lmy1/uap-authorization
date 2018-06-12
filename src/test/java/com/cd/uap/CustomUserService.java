package com.cd.uap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cd.uap.dao.RoleAuthorityDao;
import com.cd.uap.dao.RoleUserDao;
import com.cd.uap.dao.UserDao;
import com.cd.uap.entity.Authority;
import com.cd.uap.entity.Role;
import com.cd.uap.entity.User;
import com.cd.uap.service.UserService;

@Service
public class CustomUserService implements UserDetailsService {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private UserDao userDao;
	
@Autowired
private RoleUserDao roleUserDao;

@Autowired
private RoleAuthorityDao roleAuthorityDao;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//如果是手机号方式登录
		if (null != username && username.matches("^(1[345789]{1})\\d{9}$")) {
			
			User user = userDao.findByPhoneNumber(username);
			if (null == user) {
				throw new UsernameNotFoundException("手机号码不存在");
			}
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			String userJson = userService.getUserByUsername(username);
			authorities.add(new SimpleGrantedAuthority(userJson));
			
			BoundValueOperations<String, String> boundValueOps = redisTemplate.boundValueOps("regist" + username);
			String currentValidateCode = boundValueOps.get();
			if (null == currentValidateCode) {
				logger.info("验证码已过期");
				throw new UsernameNotFoundException("验证码已过期");
			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), currentValidateCode, authorities);
		}
		
		//通过用户名查用户对象
		User user = userDao.findByUsername(username);
		if(user == null){
			throw new UsernameNotFoundException("用户名不存在");
		}
		//此处将权限信息添加到 authorities 对象中，在后面进行权限验证时会使用 authorities 对象
		String userJson = userService.getUserByUsername(username);
//TODO 	
		List<Role> findRolesByUserId = roleUserDao.findRolesByUserId(user.getId());
		List<String> authorityList = new ArrayList<>();
		for (Role role : findRolesByUserId) {
			List<Authority> authoritys = roleAuthorityDao.findAuthoritiesByRoleId(role.getId());
			for (Authority authority : authoritys) {
				authorityList.add(authority.getAuthorityName());
			}
		}
		String authorityString = authorityList.toString();
		authorityString = authorityString.substring(1, authorityString.length()-1);
		return new org.springframework.security.core.userdetails.User(userJson, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString));
		
	}

	@Test
	public void test1() {
		List<String> authorityList = new ArrayList<>();
		authorityList.add("1111");
		authorityList.add("22222");
		authorityList.add("33");
		
		String authorityString = authorityList.toString();
		System.out.println(authorityString);
		String subSequence = authorityString.substring(1, authorityString.length()-1);
		System.out.println(subSequence);
	}
	
	@Test
	public void test2() {
		List<Role> findRolesByUserId = roleUserDao.findRolesByUserId(94);
		for (Role role : findRolesByUserId) {
			System.out.println(role.getRoleName());
			
		}
	}
}














