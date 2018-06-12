package com.cd.uap.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * TblAuthority entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_authority")
public class Authority implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 8337663901807815569L;

	private Integer id;
	
	@NotNull(message="权限名称不能为空")
	@Size(max=50, message="权限名称长度不能大于50位")
	private String authorityName;
	
	@Size(max=200, message="权限介绍长度不能大于200位")
	private String introduction;
	
	private Integer createdAdminId;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Date createdDatetime;
	
	@NotNull(message="该权限所属的应用不能为空")
	private Integer appId;
	
	@Size(max=200, message="权限备注长度不能大于200位")
	private String remarker;

	// Constructors

	/** default constructor */
	public Authority() {
	}

	/** minimal constructor */
	public Authority(String authorityName, Integer createdAdminId,
			Integer appId) {
		this.authorityName = authorityName;
		this.createdAdminId = createdAdminId;
		this.appId = appId;
	}

	/** full constructor */
	public Authority(Integer id, String authorityName, String introduction, Integer createdAdminId,
			Date createdDatetime, Integer appId, String remarker) {
		super();
		this.id = id;
		this.authorityName = authorityName;
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

	@Column(name = "AUTHORITY_NAME", nullable = false, length = 50)
	public String getAuthorityName() {
		return this.authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@Column(name = "INTRODUCTION", length = 200)
	public String getIntroduction() {
		return this.introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	@Column(name = "CREATED_ADMIN_ID", nullable = false)
	public Integer getCreatedAdminId() {
		return this.createdAdminId;
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

}