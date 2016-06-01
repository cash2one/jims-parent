package com.jims.finance.entity;



import com.jims.common.persistence.DataEntity;
import com.jims.finance.outpAccounts.entity.OutpAcctDetail;
import com.jims.finance.outpAccounts.entity.OutpAcctMoney;

import java.util.Date;
import java.util.List;

/**
 * 门诊收费结帐主记录Entity
 * @author pq
 * @version 2016-05-31
 */
public class OutpAcctMaster extends DataEntity<OutpAcctMaster> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 医院ID
	private String acctNo;		// 结帐序号
	private String operatorNo;		// 收款员号
	private Date acctDate;		// 结帐日期
	private String minRcptNo;		// 最小收据序号
	private String maxRcptNo;		// 最大收据序号
	private Integer rcptsNum;		// 收据张数
	private Integer freeOfChargeNum;		// 免费人次
	private Integer refundNum;		// 退费收据张数
	private Double refundAmount;		// 退费金额
	private Double totalCosts;		// 总费用
	private Double totalIncomes;		// 总收入
	private Date tallyDate;		// 记帐日期
	private Double rcptTotal;		// rcpt_total
	private Double rcptCnt;		// rcpt_cnt
	private Double rcptRefund;		// rcpt_refund

	
	public OutpAcctMaster() {
		super();
	}

	public OutpAcctMaster(String id){
		super(id);
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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


	public String getMinRcptNo() {
		return minRcptNo;
	}

	public void setMinRcptNo(String minRcptNo) {
		this.minRcptNo = minRcptNo;
	}


	public String getMaxRcptNo() {
		return maxRcptNo;
	}

	public void setMaxRcptNo(String maxRcptNo) {
		this.maxRcptNo = maxRcptNo;
	}


	public Integer getRcptsNum() {
		return rcptsNum;
	}

	public void setRcptsNum(Integer rcptsNum) {
		this.rcptsNum = rcptsNum;
	}


	public Integer getFreeOfChargeNum() {
		return freeOfChargeNum;
	}

	public void setFreeOfChargeNum(Integer freeOfChargeNum) {
		this.freeOfChargeNum = freeOfChargeNum;
	}


	public Integer getRefundNum() {
		return refundNum;
	}

	public void setRefundNum(Integer refundNum) {
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


	public Double getRcptTotal() {
		return rcptTotal;
	}

	public void setRcptTotal(Double rcptTotal) {
		this.rcptTotal = rcptTotal;
	}


	public Double getRcptCnt() {
		return rcptCnt;
	}

	public void setRcptCnt(Double rcptCnt) {
		this.rcptCnt = rcptCnt;
	}


	public Double getRcptRefund() {
		return rcptRefund;
	}

	public void setRcptRefund(Double rcptRefund) {
		this.rcptRefund = rcptRefund;
	}


}