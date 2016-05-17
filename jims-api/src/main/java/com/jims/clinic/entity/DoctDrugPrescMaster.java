package com.jims.clinic.entity;


import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 待发药住院处方主记录Entity
 * @author CTQ
 * @version 2016-05-16
 */
public class DoctDrugPrescMaster extends DataEntity<DoctDrugPrescMaster> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 机构ID
	private String patientId;		// 病人ID
	private String visitId;		// 住院ID
	private Date prescDate;		// 处方日期
	private Integer prescNo;		// 处方号
	private String dispensary;		// 发药药局
	private String name;		// 姓名
	private String namePhonetic;		// 姓名拼音码
	private String identity;		// 身份
	private String chargeType;		// 费别
	private String unitInContract;		// 合同单位
	private Integer prescType;		// 处方类型
	private String prescAttr;		// 处方属性
	private Integer prescSource;		// 处方来源
	private Integer dischargeTakingIndicator;		// 出院带药标志
	private String bindingPrescTitle;		// 处方名
	private Integer repetition;		// 剂数
	private Integer countPerRepetition;		// 每剂份数
	private Double costs;		// 计价金额
	private Double payments;		// 应收金额
	private String orderedBy;		// 开单科室
	private String prescribedBy;		// 执行人代码
	private String enteredBy;		// 确认人代码
	private Integer prescStatus;		// 处方状态
	private String dispensingProvider;		// 确认人名称
	private String usage;		// 说明
	private Integer decoction;		// 是否代煎
	private String doctorUser;		// 医生代码
	private String newlyPrint;		// 是否重打
	private String verifyBy;		// verify_by
	private Date verifiedDatetime;		// verified_datetime
	private String diagnosisName;		// diagnosis_name
	private String dispensarySub;		// 发药子药局
	private Date createData;		// 创建时间
	private Date updateData;		// 更新时间

	//
	private String bedNo;
	private String prepayment;
	private String dianosis;
	private String bedLabel;
	private int longTerm;
	private int tempTerm;
	
	public DoctDrugPrescMaster() {
		super();
	}

	public DoctDrugPrescMaster(String id){
		super(id);
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
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


	public Integer getPrescType() {
		return prescType;
	}

	public void setPrescType(Integer prescType) {
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


	public Integer getDischargeTakingIndicator() {
		return dischargeTakingIndicator;
	}

	public void setDischargeTakingIndicator(Integer dischargeTakingIndicator) {
		this.dischargeTakingIndicator = dischargeTakingIndicator;
	}


	public String getBindingPrescTitle() {
		return bindingPrescTitle;
	}

	public void setBindingPrescTitle(String bindingPrescTitle) {
		this.bindingPrescTitle = bindingPrescTitle;
	}


	public Integer getRepetition() {
		return repetition;
	}

	public void setRepetition(Integer repetition) {
		this.repetition = repetition;
	}


	public Integer getCountPerRepetition() {
		return countPerRepetition;
	}

	public void setCountPerRepetition(Integer countPerRepetition) {
		this.countPerRepetition = countPerRepetition;
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


	public Integer getPrescStatus() {
		return prescStatus;
	}

	public void setPrescStatus(Integer prescStatus) {
		this.prescStatus = prescStatus;
	}


	public String getDispensingProvider() {
		return dispensingProvider;
	}

	public void setDispensingProvider(String dispensingProvider) {
		this.dispensingProvider = dispensingProvider;
	}


	public String getUsage() {
		return usage;
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}


	public Integer getDecoction() {
		return decoction;
	}

	public void setDecoction(Integer decoction) {
		this.decoction = decoction;
	}


	public String getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(String doctorUser) {
		this.doctorUser = doctorUser;
	}


	public String getNewlyPrint() {
		return newlyPrint;
	}

	public void setNewlyPrint(String newlyPrint) {
		this.newlyPrint = newlyPrint;
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


	public String getDiagnosisName() {
		return diagnosisName;
	}

	public void setDiagnosisName(String diagnosisName) {
		this.diagnosisName = diagnosisName;
	}


	public String getDispensarySub() {
		return dispensarySub;
	}

	public void setDispensarySub(String dispensarySub) {
		this.dispensarySub = dispensarySub;
	}


	public Date getCreateData() {
		return createData;
	}

	public void setCreateData(Date createData) {
		this.createData = createData;
	}


	public Date getUpdateData() {
		return updateData;
	}

	public void setUpdateData(Date updateData) {
		this.updateData = updateData;
	}

	public String getBedNo() {
		return bedNo;
	}

	public void setBedNo(String bedNo) {
		this.bedNo = bedNo;
	}

	public String getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(String prepayment) {
		this.prepayment = prepayment;
	}

	public String getDianosis() {
		return dianosis;
	}

	public void setDianosis(String dianosis) {
		this.dianosis = dianosis;
	}

	public String getBedLabel() {
		return bedLabel;
	}

	public void setBedLabel(String bedLabel) {
		this.bedLabel = bedLabel;
	}

	public int getLongTerm() {
		return longTerm;
	}

	public void setLongTerm(int longTerm) {
		this.longTerm = longTerm;
	}

	public int getTempTerm() {
		return tempTerm;
	}

	public void setTempTerm(int tempTerm) {
		this.tempTerm = tempTerm;
	}
}