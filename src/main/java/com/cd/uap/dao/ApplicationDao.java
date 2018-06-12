package com.cd.uap.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cd.uap.entity.Application;

public interface ApplicationDao extends JpaRepository<Application, Integer>,JpaSpecificationExecutor<Application> {
	
	public Application findByClientId(String clientId);
}
