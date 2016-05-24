package com.jims.clinic.entity;



import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 待发处方门诊主表Entity
 * @author pq
 * @version 2016-05-24
 */
public class DrugPrescMasterTemp extends DataEntity<DrugPrescMasterTemp> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 医院ID
	private Date prescDate;		// 处方日期
	private Integer prescNo;		// 处方号
	private String dispensary;		// 发药药局
	private String queueId;		// 发药队列号
	private Integer status;		// 处理状态
	private String patientId;		// 病人标识号
	private String name;		// 姓名
	private String namePhonetic;		// 姓名拼音
	private String identity;		// 身份
	private String chargeType;		// 费别
	private String unitInContract;		// 病人合同单位
	private String prescType;		// 处方类别
	private String prescAttr;		// 厨房属性
	private Integer prescSource;		// 处方来源
	private Integer repetition;		// 剂数
	private Double costs;		// 费用
	private Double payments;		// 实付费用
	private String orderedBy;		// 开单科室
	private String prescribedBy;		// 开方医生
	private String enteredBy;		// 录方人
	private Integer chargeIndicator;		// 费用类别
	private String rcptNo;		// 收据号
	private String sex;		// 性别
	private Integer age;		// 年龄
	private Integer countPerRepetition;		// 每剂煎几副
	private Date enteredDatetime;		// 录入日期
	private Date dispensingDatetime;		// 发药日期
	private String memo;		// 备注
	private String dispensingProvider;		// 调剂者
	private Integer discgTakingIndicator;		// discg_taking_indicator
	private String doctorUser;		// 医生
	private Integer decoction;		// decoction
	private Double dosageEach;		// dosage_each
	private String verifyBy;		// verify_by
	private Date verifiedDatetime;		// verified_datetime
	private String dispensationBy;		// dispensation_by
	private Date dispensationDate;		// dispensation_date
	private Date printDateTime;		// print_date_time
	private Integer listNo;		// list_no
	private String dispensarySub;		// 发药子药局
	private Integer flag;		// 退药标志
	private String clinicNo;		// clinic_no
	
	public DrugPrescMasterTemp() {
		super();
	}

	public DrugPrescMasterTemp(String id){
		super(id);
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public Date getPrescDate() {
		return prescDate;
	}

	public void setPrescDate(Date prescDate) {
		this.prescDate = prescDate;
	}


	public Integer getPrescNo() {
		return prescNo;
	}

	public void setPrescNo(Integer prescNo) {
		this.prescNo = prescNo;
	}


	public String getDispensary() {
		return dispensary;
	}

	public void setDispensary(String dispensary) {
		this.dispensary = dispensary;
	}


	public String getQueueId() {
		return queueId;
	}

	public void setQueueId(String queueId) {
		this.queueId = queueId;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getNamePhonetic() {
		return namePhonetic;
	}

	public void setNamePhonetic(String namePhonetic) {
		this.namePhonetic = namePhonetic;
	}


	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}


	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}


	public String getUnitInContract() {
		return unitInContract;
	}

	public void setUnitInContract(String unitInContract) {
		this.unitInContract = unitInContract;
	}


	public String getPrescType() {
		return prescType;
	}

	public void setPrescType(String prescType) {
		this.prescType = prescType;
	}


	public String getPrescAttr() {
		return prescAttr;
	}

	public void setPrescAttr(String prescAttr) {
		this.prescAttr = prescAttr;
	}


	public Integer getPrescSource() {
		return prescSource;
	}

	public void setPrescSource(Integer prescSource) {
		this.prescSource = prescSource;
	}


	public Integer getRepetition() {
		return repetition;
	}

	public void setRepetition(Integer repetition) {
		this.repetition = repetition;
	}


	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}


	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}


	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}


	public String getPrescribedBy() {
		return prescribedBy;
	}

	public void setPrescribedBy(String prescribedBy) {
		this.prescribedBy = prescribedBy;
	}


	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}


	public Integer getChargeIndicator() {
		return chargeIndicator;
	}

	public void setChargeIndicator(Integer chargeIndicator) {
		this.chargeIndicator = chargeIndicator;
	}


	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}


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


	public Integer getCountPerRepetition() {
		return countPerRepetition;
	}

	public void setCountPerRepetition(Integer countPerRepetition) {
		this.countPerRepetition = countPerRepetition;
	}


	public Date getEnteredDatetime() {
		return enteredDatetime;
	}

	public void setEnteredDatetime(Date enteredDatetime) {
		this.enteredDatetime = enteredDatetime;
	}


	public Date getDispensingDatetime() {
		return dispensingDatetime;
	}

	public void setDispensingDatetime(Date dispensingDatetime) {
		this.dispensingDatetime = dispensingDatetime;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getDispensingProvider() {
		return dispensingProvider;
	}

	public void setDispensingProvider(String dispensingProvider) {
		this.dispensingProvider = dispensingProvider;
	}


	public Integer getDiscgTakingIndicator() {
		return discgTakingIndicator;
	}

	public void setDiscgTakingIndicator(Integer discgTakingIndicator) {
		this.discgTakingIndicator = discgTakingIndicator;
	}


	public String getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(String doctorUser) {
		this.doctorUser = doctorUser;
	}


	public Integer getDecoction() {
		return decoction;
	}

	public void setDecoction(Integer decoction) {
		this.decoction = decoction;
	}


	public Double getDosageEach() {
		return dosageEach;
	}

	public void setDosageEach(Double dosageEach) {
		this.dosageEach = dosageEach;
	}


	public String getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(String verifyBy) {
		this.verifyBy = verifyBy;
	}


	public Date getVerifiedDatetime() {
		return verifiedDatetime;
	}

	public void setVerifiedDatetime(Date verifiedDatetime) {
		this.verifiedDatetime = verifiedDatetime;
	}


	public String getDispensationBy() {
		return dispensationBy;
	}

	public void setDispensationBy(String dispensationBy) {
		this.dispensationBy = dispensationBy;
	}


	public Date getDispensationDate() {
		return dispensationDate;
	}

	public void setDispensationDate(Date dispensationDate) {
		this.dispensationDate = dispensationDate;
	}


	public Date getPrintDateTime() {
		return printDateTime;
	}

	public void setPrintDateTime(Date printDateTime) {
		this.printDateTime = printDateTime;
	}


	public Integer getListNo() {
		return listNo;
	}

	public void setListNo(Integer listNo) {
		this.listNo = listNo;
	}


	public String getDispensarySub() {
		return dispensarySub;
	}

	public void setDispensarySub(String dispensarySub) {
		this.dispensarySub = dispensarySub;
	}


	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public String getClinicNo() {
		return clinicNo;
	}

	public void setClinicNo(String clinicNo) {
		this.clinicNo = clinicNo;
	}

}