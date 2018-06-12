package com.cd.uap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cd.uap.entity.Authority;
import com.cd.uap.entity.RoleAuthority;

public interface RoleAuthorityDao extends JpaRepository<RoleAuthority, Integer>,JpaSpecificationExecutor<RoleAuthority> {

	/**
	 * 通过角色id查找权限
	 * @param roleId
	 * @return
	 */
	@Query("select new Authority(t2.id,t2.authorityName,t2.introduction,t2.createdAdminId,t2.createdDatetime,t2.appId,t2.remarker) from RoleAuthority as t1,Authority as t2 where t2.id = t1.authorityId AND t1.roleId = ?")
	public List<Authority> findAuthoritiesByRoleId(Integer roleId);
}
