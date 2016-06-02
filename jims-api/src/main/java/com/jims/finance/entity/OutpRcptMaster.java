/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.entity;


import com.jims.common.persistence.DataEntity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 门诊医疗收据记录Entity
 * @author zhaoning
 * @version 2016-05-26
 */
public class OutpRcptMaster extends DataEntity<OutpRcptMaster> {
	
	private static final long serialVersionUID = 1L;
	private String rcptNo;		// 收据号
	private String patientid;		// 病人基本信息
	private String name;		// 姓名
	private String namePhonetic;		// 姓名拼音
	private String identity;		// 身份
	private String chargeType;		// 费别
	private String unitInContract;		// 合同单位
	private Date visitDate;		// 就诊日期
	private Double totalCosts;		// 总费用
	private Double totalCharges;		// 应收费
	private String operatorNo;		// 收款员
	private Integer chargeIndicator;		// 交费标志
	private String refundedRcptNo;		// 退费收据号
	private String acctNo;		// 结帐序号
	private String printedOperatorNo;		// printed_operator_no
	private Date printedDate;		// printed_date
	private String cardFlag;		// card_flag
	private String printedRcptNo;		// printed_rcpt_no
	private String flag;		// flag
	private String invoiceNo;		// invoice_no
	private String bz;		// bz
	private String insuranceNo;		// insurance_no
	private String ordInvoiceNo;		// ord_invoice_no
	private String reckonNo;		// 银海医保清算流水号
	private String rcptPrint;		// 序列号
	
	public OutpRcptMaster() {
		super();
	}

	public OutpRcptMaster(String id){
		super(id);
	}

	@Length(min=0, max=128, message="收据号长度必须介于 0 和 128 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
	@Length(min=0, max=128, message="病人基本信息长度必须介于 0 和 128 之间")
	public String getPatientid() {
		return patientid;
	}

	public void setPatientid(String patientid) {
		this.patientid = patientid;
	}
	
	@Length(min=0, max=120, message="姓名长度必须介于 0 和 120 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="姓名拼音长度必须介于 0 和 32 之间")
	public String getNamePhonetic() {
		return namePhonetic;
	}

	public void setNamePhonetic(String namePhonetic) {
		this.namePhonetic = namePhonetic;
	}
	
	@Length(min=0, max=128, message="身份长度必须介于 0 和 128 之间")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	@Length(min=0, max=128, message="费别长度必须介于 0 和 128 之间")
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	@Length(min=0, max=200, message="合同单位长度必须介于 0 和 200 之间")
	public String getUnitInContract() {
		return unitInContract;
	}

	public void setUnitInContract(String unitInContract) {
		this.unitInContract = unitInContract;
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	public Double getTotalCosts() {
		return totalCosts;
	}

	public void setTotalCosts(Double totalCosts) {
		this.totalCosts = totalCosts;
	}
	
	public Double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(Double totalCharges) {
		this.totalCharges = totalCharges;
	}
	
	@Length(min=0, max=128, message="收款员长度必须介于 0 和 128 之间")
	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}
	
	public Integer getChargeIndicator() {
		return chargeIndicator;
	}

	public void setChargeIndicator(Integer chargeIndicator) {
		this.chargeIndicator = chargeIndicator;
	}
	
	@Length(min=0, max=100, message="退费收据号长度必须介于 0 和 100 之间")
	public String getRefundedRcptNo() {
		return refundedRcptNo;
	}

	public void setRefundedRcptNo(String refundedRcptNo) {
		this.refundedRcptNo = refundedRcptNo;
	}
	
	@Length(min=0, max=20, message="结帐序号长度必须介于 0 和 20 之间")
	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	@Length(min=0, max=128, message="printed_operator_no长度必须介于 0 和 128 之间")
	public String getPrintedOperatorNo() {
		return printedOperatorNo;
	}

	public void setPrintedOperatorNo(String printedOperatorNo) {
		this.printedOperatorNo = printedOperatorNo;
	}

	public Date getPrintedDate() {
		return printedDate;
	}

	public void setPrintedDate(Date printedDate) {
		this.printedDate = printedDate;
	}
	
	@Length(min=0, max=1, message="card_flag长度必须介于 0 和 1 之间")
	public String getCardFlag() {
		return cardFlag;
	}

	public void setCardFlag(String cardFlag) {
		this.cardFlag = cardFlag;
	}
	
	@Length(min=0, max=128, message="printed_rcpt_no长度必须介于 0 和 128 之间")
	public String getPrintedRcptNo() {
		return printedRcptNo;
	}

	public void setPrintedRcptNo(String printedRcptNo) {
		this.printedRcptNo = printedRcptNo;
	}
	
	@Length(min=0, max=128, message="flag长度必须介于 0 和 128 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@Length(min=0, max=128, message="invoice_no长度必须介于 0 和 128 之间")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	@Length(min=0, max=128, message="bz长度必须介于 0 和 128 之间")
	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}
	
	@Length(min=0, max=128, message="insurance_no长度必须介于 0 和 128 之间")
	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	
	@Length(min=0, max=128, message="ord_invoice_no长度必须介于 0 和 128 之间")
	public String getOrdInvoiceNo() {
		return ordInvoiceNo;
	}

	public void setOrdInvoiceNo(String ordInvoiceNo) {
		this.ordInvoiceNo = ordInvoiceNo;
	}
	
	@Length(min=0, max=128, message="银海医保清算流水号长度必须介于 0 和 128 之间")
	public String getReckonNo() {
		return reckonNo;
	}

	public void setReckonNo(String reckonNo) {
		this.reckonNo = reckonNo;
	}
	
	@Length(min=0, max=128, message="序列号长度必须介于 0 和 128 之间")
	public String getRcptPrint() {
		return rcptPrint;
	}

	public void setRcptPrint(String rcptPrint) {
		this.rcptPrint = rcptPrint;
	}
	
}