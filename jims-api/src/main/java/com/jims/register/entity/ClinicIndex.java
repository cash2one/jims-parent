/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 号别Entity
 * @author zhaoning
 * @version 2016-05-17
 */
public class ClinicIndex extends DataEntity<ClinicIndex> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 所属组织
	private String clinicLabel;		// 号别名称
	private String clinicDept;		// 门诊科室
	private String doctor;		// 医生
	private String doctorTitle;		// 医生职称
	private String clinicType;		// 号类
	private String inputCode;		// 输入码
	private String clinicPosition;		// 门诊科室地址
	private Integer serialNo;		// 排序号
	
	public ClinicIndex() {
		super();
	}

	public ClinicIndex(String id){
		super(id);
	}

	@Length(min=0, max=64, message="所属组织长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=1, max=200, message="号别名称长度必须介于 1 和 200 之间")
	public String getClinicLabel() {
		return clinicLabel;
	}

	public void setClinicLabel(String clinicLabel) {
		this.clinicLabel = clinicLabel;
	}
	
	@Length(min=0, max=64, message="门诊科室长度必须介于 0 和 64 之间")
	public String getClinicDept() {
		return clinicDept;
	}

	public void setClinicDept(String clinicDept) {
		this.clinicDept = clinicDept;
	}
	
	@Length(min=0, max=64, message="医生长度必须介于 0 和 64 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	@Length(min=0, max=64, message="医生职称长度必须介于 0 和 64 之间")
	public String getDoctorTitle() {
		return doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}
	
	@Length(min=0, max=64, message="号类长度必须介于 0 和 64 之间")
	public String getClinicType() {
		return clinicType;
	}

	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}
	
	@Length(min=0, max=100, message="输入码长度必须介于 0 和 100 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=400, message="门诊科室地址长度必须介于 0 和 400 之间")
	public String getClinicPosition() {
		return clinicPosition;
	}

	public void setClinicPosition(String clinicPosition) {
		this.clinicPosition = clinicPosition;
	}
	
	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	
}