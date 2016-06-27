package com.jims.nurse.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 管床医生记录Entity
 * @author CTQ
 * @version 2016-06-06
 */
public class OrdersGroupRec extends DataEntity<OrdersGroupRec> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人ID
	private String visitId;		// 住院次数
	private String deptCode;		// 科室代码
	private String orderGroup;		// 核算组代码
	private String orderDoctor;		// 经治医生
	private String doctorUser;		// 医生代码
	private String superDoctorId;		// super_doctor_id
	private String parentDoctorId;		// parent_doctor_id
	private String orgId;		// 机构标识
	
	public OrdersGroupRec() {
		super();
	}

	public OrdersGroupRec(String id){
		super(id);
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}


	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public String getOrderGroup() {
		return orderGroup;
	}

	public void setOrderGroup(String orderGroup) {
		this.orderGroup = orderGroup;
	}


	public String getOrderDoctor() {
		return orderDoctor;
	}

	public void setOrderDoctor(String orderDoctor) {
		this.orderDoctor = orderDoctor;
	}


	public String getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(String doctorUser) {
		this.doctorUser = doctorUser;
	}


	public String getSuperDoctorId() {
		return superDoctorId;
	}

	public void setSuperDoctorId(String superDoctorId) {
		this.superDoctorId = superDoctorId;
	}


	public String getParentDoctorId() {
		return parentDoctorId;
	}

	public void setParentDoctorId(String parentDoctorId) {
		this.parentDoctorId = parentDoctorId;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}