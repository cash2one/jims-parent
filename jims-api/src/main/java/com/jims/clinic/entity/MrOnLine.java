/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 联机病历描述Entity
 * @author zhaoning
 * @version 2016-06-29
 */
public class MrOnLine extends DataEntity<MrOnLine> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人ID
	private String status;		// 病历状态
	private String requestDoctorId;		// 请求医生
	private Date requestDateTime;		// 请求时间
	private String superDoctorId;		// 主任医师
	private String parentDoctorId;		// 上级医师
	private String otherDoctorId;		// other_doctor_id
	
	public MrOnLine() {
		super();
	}

	public MrOnLine(String id){
		super(id);
	}

	@Length(min=0, max=128, message="病人ID长度必须介于 0 和 128 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=2, message="病历状态长度必须介于 0 和 2 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=1, max=128, message="请求医生长度必须介于 1 和 128 之间")
	public String getRequestDoctorId() {
		return requestDoctorId;
	}

	public void setRequestDoctorId(String requestDoctorId) {
		this.requestDoctorId = requestDoctorId;
	}

	public Date getRequestDateTime() {
		return requestDateTime;
	}

	public void setRequestDateTime(Date requestDateTime) {
		this.requestDateTime = requestDateTime;
	}
	
	@Length(min=0, max=128, message="主任医师长度必须介于 0 和 128 之间")
	public String getSuperDoctorId() {
		return superDoctorId;
	}

	public void setSuperDoctorId(String superDoctorId) {
		this.superDoctorId = superDoctorId;
	}
	
	@Length(min=0, max=128, message="上级医师长度必须介于 0 和 128 之间")
	public String getParentDoctorId() {
		return parentDoctorId;
	}

	public void setParentDoctorId(String parentDoctorId) {
		this.parentDoctorId = parentDoctorId;
	}
	
	@Length(min=0, max=128, message="other_doctor_id长度必须介于 0 和 128 之间")
	public String getOtherDoctorId() {
		return otherDoctorId;
	}

	public void setOtherDoctorId(String otherDoctorId) {
		this.otherDoctorId = otherDoctorId;
	}
	
}