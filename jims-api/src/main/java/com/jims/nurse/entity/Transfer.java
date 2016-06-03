package com.jims.nurse.entity;



import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 病人在科记录Entity
 * @author CTQ
 * @version 2016-06-03
 */
public class Transfer extends DataEntity<Transfer> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人标识
	private String orgId;		// 组织机构标识
	private String visitId;		// 病人本次住院标识
	private String deptStayed;		// 所在科室
	private Date admissionDateTime;		// 入科日期及时间
	private Date dischargeDateTime;		// 出科日期及时间
	private String deptTransferedTo;		// 转向科室
	private String doctorInCharge;		// 经治医师
	private String deptCodeLend;		// dept_code_lend
	private String transFlag;		// 医保上传标记
	private String ywlsh;		// 医保业务流水号
	
	public Transfer() {
		super();
	}

	public Transfer(String id){
		super(id);
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}


	public String getDeptStayed() {
		return deptStayed;
	}

	public void setDeptStayed(String deptStayed) {
		this.deptStayed = deptStayed;
	}


	public Date getAdmissionDateTime() {
		return admissionDateTime;
	}

	public void setAdmissionDateTime(Date admissionDateTime) {
		this.admissionDateTime = admissionDateTime;
	}


	public Date getDischargeDateTime() {
		return dischargeDateTime;
	}

	public void setDischargeDateTime(Date dischargeDateTime) {
		this.dischargeDateTime = dischargeDateTime;
	}


	public String getDeptTransferedTo() {
		return deptTransferedTo;
	}

	public void setDeptTransferedTo(String deptTransferedTo) {
		this.deptTransferedTo = deptTransferedTo;
	}


	public String getDoctorInCharge() {
		return doctorInCharge;
	}

	public void setDoctorInCharge(String doctorInCharge) {
		this.doctorInCharge = doctorInCharge;
	}


	public String getDeptCodeLend() {
		return deptCodeLend;
	}

	public void setDeptCodeLend(String deptCodeLend) {
		this.deptCodeLend = deptCodeLend;
	}


	public String getTransFlag() {
		return transFlag;
	}

	public void setTransFlag(String transFlag) {
		this.transFlag = transFlag;
	}


	public String getYwlsh() {
		return ywlsh;
	}

	public void setYwlsh(String ywlsh) {
		this.ywlsh = ywlsh;
	}

}