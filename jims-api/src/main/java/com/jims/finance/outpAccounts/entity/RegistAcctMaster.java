package com.jims.finance.outpAccounts.entity;


import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 挂号结帐主记录Entity
 * @author CTQ
 * @version 2016-06-01
 */
public class RegistAcctMaster extends DataEntity<RegistAcctMaster> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 医院ID
	private String clinicId;		// 就诊记录ID
	private String acctNo;		// 结帐序号
	private String operatorNo;		// 收款员号
	private Date acctDate;		// 结帐日期
	private Double registNum;		// 挂号人次
	private Double refundNum;		// 退费人次
	private Double refundAmount;		// 退费金额
	private Double totalCosts;		// 总费用
	private Double totalIncomes;		// 总收入
	private Date tallyDate;		// 记帐日期
	private Date fulfillDateTime;		// fulfill_date_time
	
	public RegistAcctMaster() {
		super();
	}

	public RegistAcctMaster(String id){
		super(id);
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}


	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}


	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}


	public Date getAcctDate() {
		return acctDate;
	}

	public void setAcctDate(Date acctDate) {
		this.acctDate = acctDate;
	}


	public Double getRegistNum() {
		return registNum;
	}

	public void setRegistNum(Double registNum) {
		this.registNum = registNum;
	}


	public Double getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Double refundNum) {
		this.refundNum = refundNum;
	}


	public Double getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Double refundAmount) {
		this.refundAmount = refundAmount;
	}


	public Double getTotalCosts() {
		return totalCosts;
	}

	public void setTotalCosts(Double totalCosts) {
		this.totalCosts = totalCosts;
	}


	public Double getTotalIncomes() {
		return totalIncomes;
	}

	public void setTotalIncomes(Double totalIncomes) {
		this.totalIncomes = totalIncomes;
	}


	public Date getTallyDate() {
		return tallyDate;
	}

	public void setTallyDate(Date tallyDate) {
		this.tallyDate = tallyDate;
	}


	public Date getFulfillDateTime() {
		return fulfillDateTime;
	}

	public void setFulfillDateTime(Date fulfillDateTime) {
		this.fulfillDateTime = fulfillDateTime;
	}

}