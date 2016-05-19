package com.jims.register.entity;



import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 退号
 * @author zhangyao
 * @version 2016-05-19
 */
public class ClinicReturnedAcct extends DataEntity<ClinicReturnedAcct> {
	
	private static final long serialVersionUID = 1L;
	private String clinicId;		// 挂号表主键
	private Date visitDate;		// 就诊日期
	private Integer visitNo;		// 就诊序号
	private String clinicLabel;		// 号别
	private String timeDesc;		// 就诊时间描述
	private String patientId;		// 病人标识
	private String patientName;		// 姓名
	private Double registFee;		// 挂号费
	private Double clinicFee;		// 诊疗费
	private Double otherFee;		// 其它费
	private Double clinicCharge;		// 实收费用
	private String operator;		// 挂号员
	private Date returnedDate;		// 退号日期
	private String returnedOperator;		// 退号挂号员
	private String acctNo;		// 结账号
	private Date acctDateTime;		// 结账时间
	private Integer serialNo;		// 挂号结帐主记录
	private String reFlag;		// 挂号结帐主记录
	private String payWay;		// 支付方式
	private String invoiceNo;		// 发票号
	private String autoFlag;		// auto_flag
	private String printOperator;		// 打印操作员
	
	public ClinicReturnedAcct() {
		super();
	}

	public ClinicReturnedAcct(String id){
		super(id);
	}


	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}


	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}


	public Integer getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}


	public String getClinicLabel() {
		return clinicLabel;
	}

	public void setClinicLabel(String clinicLabel) {
		this.clinicLabel = clinicLabel;
	}


	public String getTimeDesc() {
		return timeDesc;
	}

	public void setTimeDesc(String timeDesc) {
		this.timeDesc = timeDesc;
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}


	public Double getRegistFee() {
		return registFee;
	}

	public void setRegistFee(Double registFee) {
		this.registFee = registFee;
	}


	public Double getClinicFee() {
		return clinicFee;
	}

	public void setClinicFee(Double clinicFee) {
		this.clinicFee = clinicFee;
	}


	public Double getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}


	public Double getClinicCharge() {
		return clinicCharge;
	}

	public void setClinicCharge(Double clinicCharge) {
		this.clinicCharge = clinicCharge;
	}


	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}


	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}


	public String getReturnedOperator() {
		return returnedOperator;
	}

	public void setReturnedOperator(String returnedOperator) {
		this.returnedOperator = returnedOperator;
	}


	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}


	public Date getAcctDateTime() {
		return acctDateTime;
	}

	public void setAcctDateTime(Date acctDateTime) {
		this.acctDateTime = acctDateTime;
	}


	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}


	public String getReFlag() {
		return reFlag;
	}

	public void setReFlag(String reFlag) {
		this.reFlag = reFlag;
	}


	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}


	public String getAutoFlag() {
		return autoFlag;
	}

	public void setAutoFlag(String autoFlag) {
		this.autoFlag = autoFlag;
	}


	public String getPrintOperator() {
		return printOperator;
	}

	public void setPrintOperator(String printOperator) {
		this.printOperator = printOperator;
	}

}