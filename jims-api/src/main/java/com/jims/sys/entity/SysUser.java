/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 登录信息Entity
 * @author yangruidong
 * @version 2016-04-13
 */
public class SysUser extends DataEntity<SysUser> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录名
	private String password;		// 密码
	private Date lastLoginTime;		// 最后登陆时间
	private String perisonId;		// 人员
	
	public SysUser() {
		super();
	}

	public SysUser(String id){
		super(id);
	}

	@Length(min=0, max=20, message="登录名长度必须介于 0 和 20 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=100, message="密码长度必须介于 0 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Length(min=0, max=64, message="人员长度必须介于 0 和 64 之间")
	public String getPerisonId() {
		return perisonId;
	}

	public void setPerisonId(String perisonId) {
		this.perisonId = perisonId;
	}
	
}