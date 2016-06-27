package com.jims.nurse.entity;

import com.jims.common.persistence.DataEntity;
import java.util.Date;

/**
 * 病人入出转及状态变化日志Entity
 * @author CTQ
 * @version 2016-06-03
 */
public class AdtLog extends DataEntity<AdtLog> {
	
	private static final long serialVersionUID = 1L;
	private String wardCode;		// 病房代码
	private String deptCode;		// 科室代码
	private Date logDateTime;		// 记录日期及时间
	private String patientId;		// 病人标识号
	private String orgId;		// 机构标识
	private String visitId;		// 病人本次住院标识
	private String action;		// 变化
	private String operatorNo;		// operator_no
	
	public AdtLog() {
		super();
	}

	public AdtLog(String id){
		super(id);
	}


	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}


	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public Date getLogDateTime() {
		return logDateTime;
	}

	public void setLogDateTime(Date logDateTime) {
		this.logDateTime = logDateTime;
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


	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}


	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}

}