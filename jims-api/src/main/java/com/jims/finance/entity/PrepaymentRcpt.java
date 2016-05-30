package com.jims.finance.entity;


import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateDeSerializer;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 预交金记录Entity
 * @author CTQ
 * @version 2016-05-25
 */
public class PrepaymentRcpt extends DataEntity<PrepaymentRcpt> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人标识
	private String rcptNo;		// 预交金收据号
	private Double amount;		// 金额
	private String payWay;		// 支付方式
	private String bank;		// 开户银行
	private String checkNo;		// 支票号
	private String transactType;		// 操作类型
	private Date transactDate;		// 操作日期
	private String operatorNo;		// 收款员号
	private String refundedRcptNo;		// 退费收据号
	private String acctNo;		// 预交金结帐序号
	private String addr;		// addr
	private String checkDiv;		// 合同单位
	private String bankCode;		// 银行帐号
	private String visitId;		// visit_id
	private String settledNo;		// settled_no
	private String usedRcptNo;		// used_rcpt_no
	private String usedFlag;		// used_flag
	private String bankAuountNo;		// bank_auount_no
	private String invoiceNo;		// 收款发票号
	private String serialNo;		// serial_no
	private String settledPreNo;		// settled_pre_no
	private Double settleBalance;		// settle_balance
	
	public PrepaymentRcpt() {
		super();
	}

	public PrepaymentRcpt(String id){
		super(id);
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}


	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}


	public String getCheckNo() {
		return checkNo;
	}

	public void setCheckNo(String checkNo) {
		this.checkNo = checkNo;
	}


	public String getTransactType() {
		return transactType;
	}

	public void setTransactType(String transactType) {
		this.transactType = transactType;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getTransactDate() {
		return transactDate;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setTransactDate(Date transactDate) {
		this.transactDate = transactDate;
	}


	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}


	public String getRefundedRcptNo() {
		return refundedRcptNo;
	}

	public void setRefundedRcptNo(String refundedRcptNo) {
		this.refundedRcptNo = refundedRcptNo;
	}


	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}


	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}


	public String getCheckDiv() {
		return checkDiv;
	}

	public void setCheckDiv(String checkDiv) {
		this.checkDiv = checkDiv;
	}


	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}


	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}


	public String getSettledNo() {
		return settledNo;
	}

	public void setSettledNo(String settledNo) {
		this.settledNo = settledNo;
	}


	public String getUsedRcptNo() {
		return usedRcptNo;
	}

	public void setUsedRcptNo(String usedRcptNo) {
		this.usedRcptNo = usedRcptNo;
	}


	public String getUsedFlag() {
		return usedFlag;
	}

	public void setUsedFlag(String usedFlag) {
		this.usedFlag = usedFlag;
	}


	public String getBankAuountNo() {
		return bankAuountNo;
	}

	public void setBankAuountNo(String bankAuountNo) {
		this.bankAuountNo = bankAuountNo;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}


	public String getSettledPreNo() {
		return settledPreNo;
	}

	public void setSettledPreNo(String settledPreNo) {
		this.settledPreNo = settledPreNo;
	}


	public Double getSettleBalance() {
		return settleBalance;
	}

	public void setSettleBalance(Double settleBalance) {
		this.settleBalance = settleBalance;
	}

}