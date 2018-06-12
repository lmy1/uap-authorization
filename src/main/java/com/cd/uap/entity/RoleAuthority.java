package com.cd.uap.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TblRoleAuthority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_role_authority")
public class RoleAuthority implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = -6294731073697573525L;
	
	private Integer id;
	private Integer roleId;
	private Integer authorityId;

	// Constructors

	/** default constructor */
	public RoleAuthority() {
	}

	/** full constructor */
	public RoleAuthority(Integer roleId, Integer authorityId) {
		this.roleId = roleId;
		this.authorityId = authorityId;
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

	@Column(name = "AUTHORITY_ID", nullable = false)
	public Integer getAuthorityId() {
		return this.authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

}