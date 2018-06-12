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
 * TblApplication entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_application")
public class Application implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1927742382141578724L;
	
	private Integer id;
	@NotNull(message="应用名不能为空")
	@Size(max=20,message="应用名长度不能大于20位")
	private String appName;
	@Size(max=16,min=16,message="clientId长度必须为16位")
	private String clientId;
	@NotNull(message="secret不能为空")
	@Size(max=32,min=32,message="secret长度必须为32位")
	private String secret;
	@Size(max=200,message="应用首页URL长度不能大于200个字符")
	private String appUrl;
	@Size(max=50,message="应用介绍长度不能大于50个字符")
	private String introduction;
	private Integer createdAdminId;
	private Date createdDatetime;
	@Size(max=500,message="应用备注长度不能大于500个字符")
	private String remarker;

	private String scope;
	
	// Constructors

	/** default constructor */
	public Application() {
	}

	/** minimal constructor */
	public Application(String clientId, String secret, Integer createdAdminId) {
		this.clientId = clientId;
		this.secret = secret;
		this.createdAdminId = createdAdminId;
	}

	/** full constructor */
	public Application(Integer id, String appName, String clientId, String secret, String appUrl, String introduction,
			Integer createdAdminId, Date createdDatetime, String remarker, String scope) {
		super();
		this.id = id;
		this.appName = appName;
		this.clientId = clientId;
		this.secret = secret;
		this.appUrl = appUrl;
		this.introduction = introduction;
		this.createdAdminId = createdAdminId;
		this.createdDatetime = createdDatetime;
		this.remarker = remarker;
		this.scope = scope;
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

	@Column(name = "APP_NAME", nullable = false, length = 20)
	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	
	@Column(name = "CLIENT_ID", nullable = false, length = 16)
	public String getClientId() {
		return this.clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@Column(name = "SECRET", nullable = false, length = 32)
	public String getSecret() {
		return this.secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Column(name = "APP_URL", length = 200)
	public String getAppUrl() {
		return this.appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	@Column(name = "INTRODUCTION", length = 50)
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

	@Column(name = "REMARKER", length = 500)
	public String getRemarker() {
		return this.remarker;
	}

	public void setRemarker(String remarker) {
		this.remarker = remarker;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	
}