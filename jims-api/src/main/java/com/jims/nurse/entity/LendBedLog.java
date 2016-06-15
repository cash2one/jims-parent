package com.jims.nurse.entity;


import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 借床日志Entity
 * @author CTQ
 * @version 2016-06-06
 */
public class LendBedLog extends DataEntity<LendBedLog> {
	
	private static final long serialVersionUID = 1L;
	private String wardCode;		// 护理单元
	private Date lendStartDate;		// 借床起始日期
	private String patientId;		// 病人ID
	private String visitId;		// 住院次数
	private String deptCode;		// 部门代码
	private String lendWardCode;		// 借床护理单元
	private String lendDeptCode;		// 借床科室代码
	private Date lendEndDate;		// 借床结束日期
	private String orgId;		// 机构标识
	
	public LendBedLog() {
		super();
	}

	public LendBedLog(String id){
		super(id);
	}


	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}


	public Date getLendStartDate() {
		return lendStartDate;
	}

	public void setLendStartDate(Date lendStartDate) {
		this.lendStartDate = lendStartDate;
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


	public String getLendWardCode() {
		return lendWardCode;
	}

	public void setLendWardCode(String lendWardCode) {
		this.lendWardCode = lendWardCode;
	}


	public String getLendDeptCode() {
		return lendDeptCode;
	}

	public void setLendDeptCode(String lendDeptCode) {
		this.lendDeptCode = lendDeptCode;
	}


	public Date getLendEndDate() {
		return lendEndDate;
	}

	public void setLendEndDate(Date lendEndDate) {
		this.lendEndDate = lendEndDate;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}