package com.jims.finance.outpAccounts.entity;


import com.jims.common.persistence.DataEntity;

/**
 * 门诊收费结帐明细记录Entity
 * @author pq
 * @version 2016-06-01
 */
public class OutpAcctDetail extends DataEntity<OutpAcctDetail> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 医院ID
	private String acctId;		// 结账主记录ID
	private String acctNo;		// 结帐序号
	private String tallyFeeClass;		// 费用帐目分类
	private Double costs;		// 费用
	private Double income;		// 收入
	
	public OutpAcctDetail() {
		super();
	}

	public OutpAcctDetail(String id){
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


	public String getTallyFeeClass() {
		return tallyFeeClass;
	}

	public void setTallyFeeClass(String tallyFeeClass) {
		this.tallyFeeClass = tallyFeeClass;
	}


	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}


	public Double getIncome() {
		return income;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

}