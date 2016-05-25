package com.jims.finance.entity;


import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 转科病人记录Entity
 * @author CTQ
 * @version 2016-05-25
 */
public class PatsInTransferring extends DataEntity<PatsInTransferring> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人标识号
	private String deptTransferedFrom;		// 转出科室
	private String deptTransferedTo;		// 转向科室
	private Date transferDateTime;		// 转出日期及时间
	
	public PatsInTransferring() {
		super();
	}

	public PatsInTransferring(String id){
		super(id);
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getDeptTransferedFrom() {
		return deptTransferedFrom;
	}

	public void setDeptTransferedFrom(String deptTransferedFrom) {
		this.deptTransferedFrom = deptTransferedFrom;
	}


	public String getDeptTransferedTo() {
		return deptTransferedTo;
	}

	public void setDeptTransferedTo(String deptTransferedTo) {
		this.deptTransferedTo = deptTransferedTo;
	}


	public Date getTransferDateTime() {
		return transferDateTime;
	}

	public void setTransferDateTime(Date transferDateTime) {
		this.transferDateTime = transferDateTime;
	}

}