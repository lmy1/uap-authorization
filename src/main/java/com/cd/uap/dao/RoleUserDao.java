package com.cd.uap.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.cd.uap.entity.Role;
import com.cd.uap.entity.RoleUser;

public interface RoleUserDao extends JpaRepository<RoleUser, Integer>,JpaSpecificationExecutor<RoleUser> {
	
	/**
	 * 根据用户id查找角色
	 * @param Id
	 * @return
	 */
	@Query("select new Role(t2.id,t2.roleName,t2.introduction,t2.createdAdminId,t2.createdDatetime,t2.appId,t2.remarker) from RoleUser as t1, Role as t2 where t1.roleId = t2.id and t1.userId = ?")
	public List<Role> findRolesByUserId(Integer Id);
}
