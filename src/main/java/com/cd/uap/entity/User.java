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
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * TblUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tbl_user")
public class User implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotBlank(message="用户名不能为空")
	@Size(max=20,message="用户名过长")
	private String username;
	
	@NotEmpty(message="密码不能为空")
	@Size(max=64,message="密码过长")
	@Pattern(regexp="^([A-Z]|[a-z]|[0-9]|[`~!@#$%^&*()+=|{}':;',\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“'。，、？])+$",message="密码格式错误")
	private String password;
	
	@Size(max=32,message="昵称过长")
	private String nickname;
	
	@NotNull(message="手机号不能为空")
	@Pattern(regexp="^(1[345789]{1})\\d{9}$",message="手机号格式错误")
	private String phoneNumber;
	
	@NotNull(message="状态为空")
	@Range(min=0,max=1,message="状态类型错误")
	private Integer status;
	
	private Integer createdAdminId;
	
	@NotNull(message="创建时间不能为空")
	@Past(message="创建时间必须小于当前时间")
	private Date createdDatetime;
	
	private String remarker;

	// Constructors

	/** default constructor */
	public User() {
	}

	/** minimal constructor */
	public User(String username, String password, String phoneNumber,
			Integer createdAdminId, Date createdDatetime) {
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.createdAdminId = createdAdminId;
		this.createdDatetime = createdDatetime;
	}

	/** full constructor */
	public User(String username, String password, String nickname,
			String phoneNumber, Integer status, Integer createdAdminId,
			Date createdDatetime, String remarker) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.createdAdminId = createdAdminId;
		this.createdDatetime = createdDatetime;
		this.remarker = remarker;
	}
	
	public User(Integer id, String username, String password, String nickname, String phoneNumber, Integer status,
			Integer createdAdminId, Date createdDatetime, String remarker) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.phoneNumber = phoneNumber;
		this.status = status;
		this.createdAdminId = createdAdminId;
		this.createdDatetime = createdDatetime;
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

	@Column(name = "USERNAME", nullable = false, length = 20)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "PASSWORD", nullable = false, length = 64)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NICKNAME", length = 32)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "PHONE_NUMBER", nullable = false, length = 20)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "STATUS")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "CREATED_ADMIN_ID", nullable = false)
	public Integer getCreatedAdminId() {
		return this.createdAdminId;
	}

	public void setCreatedAdminId(Integer createdAdminId) {
		this.createdAdminId = createdAdminId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATETIME", nullable = false, length = 10)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	public Date getCreatedDatetime() {
		return this.createdDatetime;
	}

	public void setCreatedDatetime(Date createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	@Column(name = "REMARKER", length = 200)
	public String getRemarker() {
		return this.remarker;
	}

	public void setRemarker(String remarker) {
		this.remarker = remarker;
	}

}