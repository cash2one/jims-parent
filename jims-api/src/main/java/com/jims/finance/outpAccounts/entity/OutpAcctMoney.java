package com.jims.finance.outpAccounts.entity;


import com.jims.common.persistence.DataEntity;

/**
 * 门诊收费结帐金额分类Entity
 * @author pq
 * @version 2016-06-01
 */
public class OutpAcctMoney extends DataEntity<OutpAcctMoney> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 医院ID
	private String acctId;		// 结账主记录ID
	private String acctNo;		// 结帐序号
	private String moneyType;		// 金额类别
	private Double incomeAmount;		// 数额
	private Double refundedAmount;		// 退数额
	
	public OutpAcctMoney() {
		super();
	}

	public OutpAcctMoney(String id){
		super(id);
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}


	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}


	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}


	public Double getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(Double incomeAmount) {
		this.incomeAmount = incomeAmount;
	}


	public Double getRefundedAmount() {
		return refundedAmount;
	}

	public void setRefundedAmount(Double refundedAmount) {
		this.refundedAmount = refundedAmount;
	}

}