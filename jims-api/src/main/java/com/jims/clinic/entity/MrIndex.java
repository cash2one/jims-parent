/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 病案索引Entity
 * @author zhaoning
 * @version 2016-06-29
 */
public class MrIndex extends DataEntity<MrIndex> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人ID
	private String mrStatus;		// 病案状态
	private String storageVolumeLabel;		// 卷标
	private String accessPath;		// 访问路径
	private Date lastAccessDateTime;		// 最近访问时间
	private String lastAccessUser;		// last_access_user
	
	public MrIndex() {
		super();
	}

	public MrIndex(String id){
		super(id);
	}

	@Length(min=0, max=128, message="病人ID长度必须介于 0 和 128 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=2, message="病案状态长度必须介于 0 和 2 之间")
	public String getMrStatus() {
		return mrStatus;
	}

	public void setMrStatus(String mrStatus) {
		this.mrStatus = mrStatus;
	}
	
	@Length(min=0, max=64, message="卷标长度必须介于 0 和 64 之间")
	public String getStorageVolumeLabel() {
		return storageVolumeLabel;
	}

	public void setStorageVolumeLabel(String storageVolumeLabel) {
		this.storageVolumeLabel = storageVolumeLabel;
	}
	
	@Length(min=0, max=80, message="访问路径长度必须介于 0 和 80 之间")
	public String getAccessPath() {
		return accessPath;
	}

	public void setAccessPath(String accessPath) {
		this.accessPath = accessPath;
	}

	public Date getLastAccessDateTime() {
		return lastAccessDateTime;
	}

	public void setLastAccessDateTime(Date lastAccessDateTime) {
		this.lastAccessDateTime = lastAccessDateTime;
	}
	
	@Length(min=0, max=40, message="last_access_user长度必须介于 0 和 40 之间")
	public String getLastAccessUser() {
		return lastAccessUser;
	}

	public void setLastAccessUser(String lastAccessUser) {
		this.lastAccessUser = lastAccessUser;
	}
	
}