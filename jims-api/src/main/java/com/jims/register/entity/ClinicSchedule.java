/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 号别安排Entity
 * @author zhaoning
 * @version 2016-05-17
 */
public class ClinicSchedule extends DataEntity<ClinicSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 所属组织
	private String clinicLabel;		// 号别
	private String dayOfWeek;		// 星期
	private String timeDesc;		// 门诊时间描述
	private Integer registrationLimits;		// 限号数
	private Integer appointmentLimits;		// 限预约号数
	private Integer phoneLimits;		// 电话预约号数
	private Integer webLimits;		// 网上预约号数
	
	public ClinicSchedule() {
		super();
	}

	public ClinicSchedule(String id){
		super(id);
	}

	@Length(min=0, max=64, message="所属组织长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=1, max=64, message="号别长度必须介于 1 和 64 之间")
	public String getClinicLabel() {
		return clinicLabel;
	}

	public void setClinicLabel(String clinicLabel) {
		this.clinicLabel = clinicLabel;
	}
	
	@Length(min=1, max=64, message="星期长度必须介于 1 和 64 之间")
	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	@Length(min=1, max=64, message="门诊时间描述长度必须介于 1 和 64 之间")
	public String getTimeDesc() {
		return timeDesc;
	}

	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
	}
	
	public Integer getRegistrationLimits() {
		return registrationLimits;
	}

	public void setRegistrationLimits(Integer registrationLimits) {
		this.registrationLimits = registrationLimits;
	}
	
	public Integer getAppointmentLimits() {
		return appointmentLimits;
	}

	public void setAppointmentLimits(Integer appointmentLimits) {
		this.appointmentLimits = appointmentLimits;
	}
	
	public Integer getPhoneLimits() {
		return phoneLimits;
	}

	public void setPhoneLimits(Integer phoneLimits) {
		this.phoneLimits = phoneLimits;
	}
	
	public Integer getWebLimits() {
		return webLimits;
	}

	public void setWebLimits(Integer webLimits) {
		this.webLimits = webLimits;
	}
	
}