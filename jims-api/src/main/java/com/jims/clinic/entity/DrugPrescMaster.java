package com.jims.clinic.entity;



import com.jims.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 药品处方主记录Entity
 * @author pq
 * @version 2016-05-24
 */
public class DrugPrescMaster extends DataEntity<DrugPrescMaster> {
	
	private static final long serialVersionUID = 1L;
	private String clinicId;		// clinic_id
	private String visitId;		// visit_id
	private Date prescDate;		// 处方日期
	private Integer prescNo;		// 处方号
	private String orgId;		// 医院ID
	private String dispensary;		// 发药药局
	private String patientId;		// 病人标识号
	private String name;		// 姓名
	private String namePhonetic;		// 姓名拼音
	private String identity;		// 身份
	private String chargeType;		// 费别
	private String unitInContract;		// 病人合同单位
	private Integer prescType;		// 处方类别
	private String prescAttr;		// 处方属性
	private Integer prescSource;		// 处方来源
	private Integer repetition;		// 剂数
	private Double costs;		// 费用
	private String orderedBy;		// 开单科室
	private String prescribedBy;		// 开方医生
	private String enteredBy;		// 录方人
	private String dispensingProvider;		// 发药人
	private Integer countPerRepetition;		// 每剂煎几份
	private Date enteredDatetime;		// 录入日期
	private Date dispensingDatetime;		// 发药日期
	private String memo;		// 备注
	private String subStorage;		// 子库房
	private Integer flag;		// 退药标志
	private String doctorUser;		// 医生
	private Date enteredDatatime;		// 录入日期
	private String verifyBy;		// 审核
	private Integer dischargeTakingIndicator;		// 出院带药标志
	private Double payments;		// 实付费用
	private Integer decoction;		// decoction
	private Date verifiedDatetime;		// verified_datetime
	private String rcptNo;		// rcpt_no
	private Date originalPrescDate;		// original_presc_date
	private Integer originalPrescNo;		// original_presc_no
	private Integer returnVisitNo;		// 退费处方号
	private Date returnVisitDate;		// 退费处方日期
	private String batchProvideNo;		// 批量发药号
	private String dispensationBy;		// dispensation_by
	private Date dispensationDate;		// dispensation_date
	private String cfbhMz;		// 毒麻处方编号
	private String fhrMz;		// 毒麻处方复核人
	private String fhbzMz;		// 毒麻处方复核标志
	private String bzMz;		// 毒麻处方备注
	private String hsph;		// 毒麻处方回收批号
	private String ffph;		// 毒麻处方发放批号
	private String dispensarySub;		// 发药子药局
	private List<DrugPrescDetail> drugPrescDetailList;

	/*查询时所需的字段*/
	private String startDatePresc;
	private String stopDatePresc;
	private  String startDateDispense;
	private  String stopDateDispense;
	public DrugPrescMaster() {
		super();
	}

	public DrugPrescMaster(String id){
		super(id);
	}


	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
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


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getDispensary() {
		return dispensary;
	}

	public void setDispensary(String dispensary) {
		this.dispensary = dispensary;
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


	public String getDispensingProvider() {
		return dispensingProvider;
	}

	public void setDispensingProvider(String dispensingProvider) {
		this.dispensingProvider = dispensingProvider;
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


	public String getSubStorage() {
		return subStorage;
	}

	public void setSubStorage(String subStorage) {
		this.subStorage = subStorage;
	}


	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public String getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(String doctorUser) {
		this.doctorUser = doctorUser;
	}


	public Date getEnteredDatatime() {
		return enteredDatatime;
	}

	public void setEnteredDatatime(Date enteredDatatime) {
		this.enteredDatatime = enteredDatatime;
	}


	public String getVerifyBy() {
		return verifyBy;
	}

	public void setVerifyBy(String verifyBy) {
		this.verifyBy = verifyBy;
	}


	public Integer getDischargeTakingIndicator() {
		return dischargeTakingIndicator;
	}

	public void setDischargeTakingIndicator(Integer dischargeTakingIndicator) {
		this.dischargeTakingIndicator = dischargeTakingIndicator;
	}


	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}


	public Integer getDecoction() {
		return decoction;
	}

	public void setDecoction(Integer decoction) {
		this.decoction = decoction;
	}


	public Date getVerifiedDatetime() {
		return verifiedDatetime;
	}

	public void setVerifiedDatetime(Date verifiedDatetime) {
		this.verifiedDatetime = verifiedDatetime;
	}


	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}


	public Date getOriginalPrescDate() {
		return originalPrescDate;
	}

	public void setOriginalPrescDate(Date originalPrescDate) {
		this.originalPrescDate = originalPrescDate;
	}


	public Integer getOriginalPrescNo() {
		return originalPrescNo;
	}

	public void setOriginalPrescNo(Integer originalPrescNo) {
		this.originalPrescNo = originalPrescNo;
	}


	public Integer getReturnVisitNo() {
		return returnVisitNo;
	}

	public void setReturnVisitNo(Integer returnVisitNo) {
		this.returnVisitNo = returnVisitNo;
	}


	public Date getReturnVisitDate() {
		return returnVisitDate;
	}

	public void setReturnVisitDate(Date returnVisitDate) {
		this.returnVisitDate = returnVisitDate;
	}


	public String getBatchProvideNo() {
		return batchProvideNo;
	}

	public void setBatchProvideNo(String batchProvideNo) {
		this.batchProvideNo = batchProvideNo;
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


	public String getCfbhMz() {
		return cfbhMz;
	}

	public void setCfbhMz(String cfbhMz) {
		this.cfbhMz = cfbhMz;
	}


	public String getFhrMz() {
		return fhrMz;
	}

	public void setFhrMz(String fhrMz) {
		this.fhrMz = fhrMz;
	}


	public String getFhbzMz() {
		return fhbzMz;
	}

	public void setFhbzMz(String fhbzMz) {
		this.fhbzMz = fhbzMz;
	}


	public String getBzMz() {
		return bzMz;
	}

	public void setBzMz(String bzMz) {
		this.bzMz = bzMz;
	}


	public String getHsph() {
		return hsph;
	}

	public void setHsph(String hsph) {
		this.hsph = hsph;
	}


	public String getFfph() {
		return ffph;
	}

	public void setFfph(String ffph) {
		this.ffph = ffph;
	}


	public String getDispensarySub() {
		return dispensarySub;
	}

	public void setDispensarySub(String dispensarySub) {
		this.dispensarySub = dispensarySub;
	}


	public List<DrugPrescDetail> getDrugPrescDetailList() {
		return drugPrescDetailList;
	}

	public void setDrugPrescDetailList(List<DrugPrescDetail> drugPrescDetailList) {
		this.drugPrescDetailList = drugPrescDetailList;
	}

	public String getStartDatePresc() {
		return startDatePresc;
	}

	public void setStartDatePresc(String startDatePresc) {
		this.startDatePresc = startDatePresc;
	}

	public String getStopDatePresc() {
		return stopDatePresc;
	}

	public void setStopDatePresc(String stopDatePresc) {
		this.stopDatePresc = stopDatePresc;
	}

	public String getStartDateDispense() {
		return startDateDispense;
	}

	public void setStartDateDispense(String startDateDispense) {
		this.startDateDispense = startDateDispense;
	}

	public String getStopDateDispense() {
		return stopDateDispense;
	}

	public void setStopDateDispense(String stopDateDispense) {
		this.stopDateDispense = stopDateDispense;
	}
}