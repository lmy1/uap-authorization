package com.cd.uap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cd.uap.entity.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Integer>,JpaSpecificationExecutor<Authority> {
	
}
