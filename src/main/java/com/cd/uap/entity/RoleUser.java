package com.cd.uap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * TblRoleUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_role_user")
public class RoleUser implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -1273865491784490382L;
	
	private Integer id;
	@NotNull(message="角色不能为空")
	private Integer roleId;
	@NotNull(message="用户不能为空")
	private Integer userId;

	// Constructors

	/** default constructor */
	public RoleUser() {
	}

	/** full constructor */
	public RoleUser(Integer roleId, Integer userId) {
		this.roleId = roleId;
		this.userId = userId;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "ROLE_ID", nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "USER_ID", nullable = false)
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}