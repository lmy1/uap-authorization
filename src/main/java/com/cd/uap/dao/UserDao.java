package com.cd.uap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cd.uap.entity.User;

public interface UserDao extends JpaRepository<User, Integer>,JpaSpecificationExecutor<User> {

	public User findByUsername(String username);
	
	public User findByPhoneNumber(String phoneNumber);

}
