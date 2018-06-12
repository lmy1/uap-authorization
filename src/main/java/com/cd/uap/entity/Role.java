package com.cd.uap.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * TblRole entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_role")
public class Role implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 7033172690275513645L;
	
	private Integer id;
	@NotNull(message="角色名称不能为空")
	@Size(max=50,message="角色名称长度不能大于50个字符")
	private String roleName;
	@Size(max=50,message="角色介绍长度不能大于50个字符")
	private String introduction;
	private Integer createdAdminId;
	private Date createdDatetime;
	@NotNull(message="应用名称不能为空")
	private Integer appId;
	@Size(max=50,message="角色介绍长度不能大于50个字符")
	private String remarker;

	// Constructors
	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Role(Integer id, String roleName, String introduction, Integer createdAdminId, Date createdDatetime,
			Integer appId, String remarker) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.introduction = introduction;
		this.createdAdminId = createdAdminId;
		this.createdDatetime = createdDatetime;
		this.appId = appId;
		this.remarker = remarker;
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

	@Column(name = "ROLE_NAME", nullable = false, length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "INTRODUCTION", length = 200)
	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "CREATED_ADMIN_ID", nullable = false, length = 10)
	public Integer getCreatedAdminId() {
		return createdAdminId;
	}

	public void setCreatedAdminId(Integer createdAdminId) {
		this.createdAdminId = createdAdminId;
	}

	
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATETIME", length = 10)
	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	@Column(name = "APP_ID", nullable = false)
	public Integer getAppId() {
		return this.appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	@Column(name = "REMARKER", length = 200)
	public String getRemarker() {
		return this.remarker;
	}

	public void setRemarker(String remarker) {
		this.remarker = remarker;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
		result = prime * result + ((createdAdminId == null) ? 0 : createdAdminId.hashCode());
		result = prime * result + ((createdDatetime == null) ? 0 : createdDatetime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((introduction == null) ? 0 : introduction.hashCode());
		result = prime * result + ((remarker == null) ? 0 : remarker.hashCode());
		result = prime * result + ((roleName == null) ? 0 : roleName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		if (createdAdminId == null) {
			if (other.createdAdminId != null)
				return false;
		} else if (!createdAdminId.equals(other.createdAdminId))
			return false;
		if (createdDatetime == null) {
			if (other.createdDatetime != null)
				return false;
		} else if (!createdDatetime.equals(other.createdDatetime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (introduction == null) {
			if (other.introduction != null)
				return false;
		} else if (!introduction.equals(other.introduction))
			return false;
		if (remarker == null) {
			if (other.remarker != null)
				return false;
		} else if (!remarker.equals(other.remarker))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		return true;
	}

	

}