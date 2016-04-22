/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 病人就诊记录Entity
 * @author zhaoning
 * @version 2016-04-20
 */
public class ClinicMaster extends DataEntity<ClinicMaster> {
	
	private static final long serialVersionUID = 1L;
	private String hosid;		// 医院ID
	private Date visitDate;		// 就诊日期
	private Integer visitNo;		// 就诊序号
	private String clinicLabel;		// 号别
	private String visitTimeDesc;		// 就诊时间描述
	private Integer serialNo;		// 号码
	private String name;		// 姓名
	private String namePhonetic;		// 姓名拼音
	private String sex;		// 性别
	private Integer age;		// 年龄
	private String identity;		// 身份
	private String chargeType;		// 费别
	private String insuranceType;		// 医保类别
	private String insuranceNo;		// 医疗保险号
	private String unitInContract;		// 合同单位
	private String clinicType;		// 号类
	private Integer firstVisitIndicator;		// 初诊标志
	private String visitDept;		// 就诊科室
	private String visitSpecialClinic;		// 就诊专科
	private String doctor;		// 医生
	private Integer mrProvideIndicator;		// 提供病案标志
	private Integer registrationStatus;		// 挂号状态
	private Date registeringDate;		// 挂号日期
	private String symptom;		// 症状
	private Double registFee;		// 挂号费
	private Double clinicFee;		// 诊疗费
	private Double otherFee;		// 其它费
	private Double clinicCharge;		// 实收费用
	private String operator;		// 挂号员
	private Date returnedDate;		// 退号日期
	private String returnedOperator;		// 退号挂号员
	private String modeCode;		// 挂号模式
	private String cardName;		// 卡名
	private String cardNo;		// 卡号
	private Date acctDateTime;		// 结帐时间
	private String acctNo;		// 结帐号码
	private String payWay;		// 支付方式
	private Integer mrProvidedIndicator;		// 病案传送否
	private String invoiceNo;		// 发票号码
	private String clinicNo;		// clinic_no
	private String mrNo;		// mr_no
	private Integer isprn;		// 病历单是否已经打印
	private String patType;		// pat_type
	private Date validDate;		// valid_date
	private String autoFlag;		// auto_flag
	private String printOperator;		// 打印操作员
	private Integer peVisitId;		// pe_visit_id
	private String mailingAddress;		// 住址
	
	public ClinicMaster() {
		super();
	}

	public ClinicMaster(String id){
		super(id);
	}

	@Length(min=0, max=128, message="医院ID长度必须介于 0 和 128 之间")
	public String getHosid() {
		return hosid;
	}

	public void setHosid(String hosid) {
		this.hosid = hosid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	
	@Length(min=0, max=64, message="号别长度必须介于 0 和 64 之间")
	public String getClinicLabel() {
		return clinicLabel;
	}

	public void setClinicLabel(String clinicLabel) {
		this.clinicLabel = clinicLabel;
	}
	
	@Length(min=0, max=200, message="就诊时间描述长度必须介于 0 和 200 之间")
	public String getVisitTimeDesc() {
		return visitTimeDesc;
	}

	public void setVisitTimeDesc(String visitTimeDesc) {
		this.visitTimeDesc = visitTimeDesc;
	}
	
	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=0, max=80, message="姓名长度必须介于 0 和 80 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=40, message="姓名拼音长度必须介于 0 和 40 之间")
	public String getNamePhonetic() {
		return namePhonetic;
	}

	public void setNamePhonetic(String namePhonetic) {
		this.namePhonetic = namePhonetic;
	}
	
	@Length(min=0, max=128, message="性别长度必须介于 0 和 128 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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
	
	@Length(min=0, max=128, message="医保类别长度必须介于 0 和 128 之间")
	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}
	
	@Length(min=0, max=128, message="医疗保险号长度必须介于 0 和 128 之间")
	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	
	@Length(min=0, max=80, message="合同单位长度必须介于 0 和 80 之间")
	public String getUnitInContract() {
		return unitInContract;
	}

	public void setUnitInContract(String unitInContract) {
		this.unitInContract = unitInContract;
	}
	
	@Length(min=0, max=128, message="号类长度必须介于 0 和 128 之间")
	public String getClinicType() {
		return clinicType;
	}

	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}
	
	public Integer getFirstVisitIndicator() {
		return firstVisitIndicator;
	}

	public void setFirstVisitIndicator(Integer firstVisitIndicator) {
		this.firstVisitIndicator = firstVisitIndicator;
	}
	
	@Length(min=0, max=128, message="就诊科室长度必须介于 0 和 128 之间")
	public String getVisitDept() {
		return visitDept;
	}

	public void setVisitDept(String visitDept) {
		this.visitDept = visitDept;
	}
	
	@Length(min=0, max=200, message="就诊专科长度必须介于 0 和 200 之间")
	public String getVisitSpecialClinic() {
		return visitSpecialClinic;
	}

	public void setVisitSpecialClinic(String visitSpecialClinic) {
		this.visitSpecialClinic = visitSpecialClinic;
	}
	
	@Length(min=0, max=128, message="医生长度必须介于 0 和 128 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	public Integer getMrProvideIndicator() {
		return mrProvideIndicator;
	}

	public void setMrProvideIndicator(Integer mrProvideIndicator) {
		this.mrProvideIndicator = mrProvideIndicator;
	}
	
	public Integer getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(Integer registrationStatus) {
		this.registrationStatus = registrationStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getRegisteringDate() {
		return registeringDate;
	}

	public void setRegisteringDate(Date registeringDate) {
		this.registeringDate = registeringDate;
	}
	
	@Length(min=0, max=80, message="症状长度必须介于 0 和 80 之间")
	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
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
	
	@Length(min=0, max=100, message="挂号员长度必须介于 0 和 100 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(Date returnedDate) {
		this.returnedDate = returnedDate;
	}
	
	@Length(min=0, max=100, message="退号挂号员长度必须介于 0 和 100 之间")
	public String getReturnedOperator() {
		return returnedOperator;
	}

	public void setReturnedOperator(String returnedOperator) {
		this.returnedOperator = returnedOperator;
	}
	
	@Length(min=0, max=1, message="挂号模式长度必须介于 0 和 1 之间")
	public String getModeCode() {
		return modeCode;
	}

	public void setModeCode(String modeCode) {
		this.modeCode = modeCode;
	}
	
	@Length(min=0, max=60, message="卡名长度必须介于 0 和 60 之间")
	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	@Length(min=0, max=40, message="卡号长度必须介于 0 和 40 之间")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAcctDateTime() {
		return acctDateTime;
	}

	public void setAcctDateTime(Date acctDateTime) {
		this.acctDateTime = acctDateTime;
	}
	
	@Length(min=0, max=40, message="结帐号码长度必须介于 0 和 40 之间")
	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}
	
	@Length(min=0, max=128, message="支付方式长度必须介于 0 和 128 之间")
	public String getPayWay() {
		return payWay;
	}

	public void setPayWay(String payWay) {
		this.payWay = payWay;
	}
	
	public Integer getMrProvidedIndicator() {
		return mrProvidedIndicator;
	}

	public void setMrProvidedIndicator(Integer mrProvidedIndicator) {
		this.mrProvidedIndicator = mrProvidedIndicator;
	}
	
	@Length(min=0, max=40, message="发票号码长度必须介于 0 和 40 之间")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	@Length(min=0, max=128, message="clinic_no长度必须介于 0 和 128 之间")
	public String getClinicNo() {
		return clinicNo;
	}

	public void setClinicNo(String clinicNo) {
		this.clinicNo = clinicNo;
	}
	
	@Length(min=0, max=40, message="mr_no长度必须介于 0 和 40 之间")
	public String getMrNo() {
		return mrNo;
	}

	public void setMrNo(String mrNo) {
		this.mrNo = mrNo;
	}
	
	public Integer getIsprn() {
		return isprn;
	}

	public void setIsprn(Integer isprn) {
		this.isprn = isprn;
	}
	
	@Length(min=0, max=128, message="pat_type长度必须介于 0 和 128 之间")
	public String getPatType() {
		return patType;
	}

	public void setPatType(String patType) {
		this.patType = patType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	@Length(min=0, max=4, message="auto_flag长度必须介于 0 和 4 之间")
	public String getAutoFlag() {
		return autoFlag;
	}

	public void setAutoFlag(String autoFlag) {
		this.autoFlag = autoFlag;
	}
	
	@Length(min=0, max=16, message="打印操作员长度必须介于 0 和 16 之间")
	public String getPrintOperator() {
		return printOperator;
	}

	public void setPrintOperator(String printOperator) {
		this.printOperator = printOperator;
	}
	
	public Integer getPeVisitId() {
		return peVisitId;
	}

	public void setPeVisitId(Integer peVisitId) {
		this.peVisitId = peVisitId;
	}
	
	@Length(min=0, max=400, message="住址长度必须介于 0 和 400 之间")
	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
}