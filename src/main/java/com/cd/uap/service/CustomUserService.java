package com.cd.uap.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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

@Service
public class CustomUserService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserService userService;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Autowired
	private RoleUserDao roleUserDao;

	@Autowired
	private RoleAuthorityDao roleAuthorityDao;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		if (null == username) {
			throw new UsernameNotFoundException("手机号或用户不能为空");
		}
		
		if (username.matches("^(1[345789]{1})\\d{9}$")) {
			// 如果是手机号方式登录
			User user = userDao.findByPhoneNumber(username);
			if (null == user) {
				throw new UsernameNotFoundException("手机号码不存在");
			}
			
			//从Redis中校验短信验证码
			BoundValueOperations<String, String> boundValueOps = redisTemplate.boundValueOps("regist" + username);
			String currentValidateCode = boundValueOps.get();
			if (null == currentValidateCode) {
				logger.info("验证码已过期");
				throw new UsernameNotFoundException("验证码已过期");
			}
			
			String userJson = userService.getUserByUsername(username);
			// 此处将权限信息添加到 authorities 对象中
			List<GrantedAuthority> authorityList = getAuthorities(user);
			
			return new org.springframework.security.core.userdetails.User(userJson, currentValidateCode, authorityList);
			
		}else {
			// 如果是用户名密码方式登录
			User user = userDao.findByUsername(username);
			if (user == null) {
				throw new UsernameNotFoundException("用户名不存在");
			}
			
			String userJson = userService.getUserByUsername(username);
			// 此处将权限信息添加到 authorities 对象中
			List<GrantedAuthority> authorityList = getAuthorities(user);
			
			return new org.springframework.security.core.userdetails.User(userJson, user.getPassword(), authorityList);

		}
	}

	/**
	 * 通过user对象得到Authority
	 * @param user
	 * @return
	 */
	private List<GrantedAuthority> getAuthorities(User user) {
		List<Role> roleList = roleUserDao.findRolesByUserId(user.getId());
		
		List<String> authorityList = new ArrayList<>();
		String authorityString = "";
		
		//通过遍历角色，获得权限信息
		if(roleList != null && roleList.size() > 0) {
			for (Role role : roleList) {
				List<Authority> authoritys = roleAuthorityDao.findAuthoritiesByRoleId(role.getId());
				for (Authority authority : authoritys) {
					authorityList.add(authority.getAuthorityName());
				}
			}
			authorityString = authorityList.toString();
			authorityString = authorityString.substring(1, authorityString.length() - 1);
		}
		
		List<GrantedAuthority> allAuthorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString);
		return allAuthorityList;
	}

}
