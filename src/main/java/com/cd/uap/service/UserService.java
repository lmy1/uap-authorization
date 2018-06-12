package com.cd.uap.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.cd.uap.dao.RoleUserDao;
import com.cd.uap.dao.UserDao;
import com.cd.uap.entity.Role;
import com.cd.uap.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleUserDao roleUserDao;

	public String getUserByUsername(String username) throws UsernameNotFoundException {

		Map<String, Object> map = new HashMap<>();
		
		User user = null;
		// TODO 以后要改
		if (null != username && username.matches("^(1[345789]{1})\\d{9}$")) {
			user = userDao.findByPhoneNumber(username);
		} else {
			user = userDao.findByUsername(username);
		}

		if (null == user) {
			throw new UsernameNotFoundException("该用户不存在");
		}
		Integer id = user.getId();
		List<Role> roles = roleUserDao.findRolesByUserId(id);
		List<String> roleNames = new ArrayList<>();
		if (roles == null) {
			throw new UsernameNotFoundException("该用户没有角色");
		}
		for (Role role : roles) {
			roleNames.add(role.getRoleName());
		}
		map.put("username", user.getUsername());
		map.put("nickname", user.getNickname());
		map.put("createDateTime", user.getCreatedDatetime());
		map.put("phoneNumber", user.getPhoneNumber());
		map.put("remarker", user.getRemarker());
		map.put("roles", roleNames);
		String userJson = JSON.toJSONString(map);
		return userJson;
	}
}
