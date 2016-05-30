/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.patient.entity;

import com.jims.common.utils.CustomDateDeSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.hibernate.validator.constraints.Length;
import java.util.Date;

import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;

/**
 * 病人住院记录信息Entity
 * @author che
 * @version 2016-04-19
 */
public class PatVisit extends DataEntity<PatVisit> {

    private static final long serialVersionUID = 1L;
    private String hosid;		// 医院ID
    private String patientId;//病人主索引ID
    private String clinicId;//病人就诊记录ID
    private Integer visitId;		// 病人本次住院标识
    private String deptAdmissionTo;		// 入院科室
    private Date admissionDateTime;		// 入院日期及时间
    private String deptDischargeFrom;		// 出院科室
    private Date dischargeDateTime;		// 出院日期及时间
    private String occupation;		// 职业
    private String maritalStatus;		// 婚姻状况
    private String identity;		// 身份
    private String armedServices;		// 军种
    private String duty;		// 勤务
    private String topUnit;		// 隶属大单位
    private Integer serviceSystemIndicator;		//
    private String unitInContract;		// 合同单位
    private String chargeType;		// 费别
    private Integer workingStatus;		// 在职标志
    private String insuranceType;		// 工作单位
    private String insuranceNo;		// 医疗保险号
    private String serviceAgency;		// 医疗体系病人标志
    private String mailingAddress;		// 通信地址(户口地址)
    private String zipCode;		// 邮政编码
    private String nextOfKin;		// 联系人姓名
    private String relationship;		// 与联系人关系
    private String nextOfKinAddr;		// 联系人地址
    private String nextOfKinZipcode;		// 联系人邮政编码
    private String nextOfKinPhone;		// 联系人电话
    private String patientClass;		// 入院方式
    private String admissionCause;		// 住院目的
    private Date consultingDate;		// 接诊日期
    private String patAdmCondition;		// 入院病情
    private String consultingDoctor;		// 门诊医师
    private String admittedBy;		// 办理住院者
    private Integer emerTreatTimes;		// 抢救次数
    private Integer escEmerTimes;		// 抢救成功次数
    private Integer seriousCondDays;		// 病重天数
    private Integer criticalCondDays;		// 病危天数
    private Integer icuDays;		// ICU天数
    private Integer ccuDays;		// CCU天数
    private Integer specLevelNursDays;		// 特别护理天数
    private Integer firstLevelNursDays;		// 一级护理天数
    private Integer secondLevelNursDays;		// 二级护理天数
    private Integer autopsyIndicator;		// 尸检标识
    private String bloodType;		// 血型
    private String bloodTypeRh;		// Rh血型
    private Integer infusionReactTimes;		// 输液反应次数
    private Integer bloodTranTimes;		// 输血次数
    private Integer bloodTranVol;		// 输血总量
    private Integer bloodTranReactTimes;		// 输血反应次数
    private Integer decubitalUlcerTimes;		// 发生褥疮次数
    private String alergyDrugs;		// 过敏药物
    private String adverseReactionDrugs;		// 不良反应药物
    private String mrValue;		// 病案价值
    private String mrQuality;		// 病案质量
    private Integer followIndicator;		// 随诊标志
    private Integer followInterval;		// 随诊期限
    private String followIntervalUnits;		// 随诊期限单位
    private String director;		// 科主任
    private String attendingDoctor;		// 主治医师
    private String doctorInCharge;		// 经治医师
    private String dischargeDisposition;		// 出院方式
    private Double totalCosts;		// 总费用
    private Double totalPayments;		// 实付费用
    private Date catalogDate;		// 编目日期
    private String cataloger;		// 编目人
    private Integer hbsagIndicator;		// HbsAg
    private Integer hcvAbIndicator;		// HCV-Ab
    private Integer hivAbIndicator;		// HIV_AB
    private String chiefDoctor;		// 主任医师
    private String advancedStudiesDoctor;		// 进修医师
    private String practiceDoctorOfGraduate;		// 研究生实习医师
    private String practiceDoctor;		// 实习医师
    private String doctorOfControlQuality;		// 质控医师
    private String nurseOfControlQuality;		// 质控护士
    private Date dateOfControlQuality;		// 质控日期
    private Integer firstCaseIndicator;		// 是否为本院第一例
    private Integer thirdLevelNursDays;		// 三级护理天数
    private String xExamNo;		// X线号
    private String treatedInOthersIndicator;		// 入院前经外院诊治
    private String treatMethod;		// 治疗类别
    private String hospMadeMedicineIndicator;		// 自制中药制剂
    private String pathologyNo;		// 病理号
    private String upperDoctorGuideEffect;		// 上级医生指导作用
    private String emerTreatMethod;		// 抢救方法
    private String ictusIndicator;		// 住院期间是否出现急症
    private String difficultyIndicator;		// 住院期间是否出现危难
    private String fromOtherPlaceIndicator;		// 外县市来病人
    private String suspicionIndicator;		// 出院诊断疑诊
    private String chineseMedicineIndicator;		// 中医特色治疗
    private String operationScale;		// 手术级别
    private String diagnosisCorrectness;		// 辨证准确度
    private String treatMethodCorrectness;		// 治法准确度
    private String prescriptionCorrectness;		// 方药准确度
    private String mrCompleteIndicator;		// 病案书写齐全
    private String medicalTermCorrectness;		// 医学术语应用正确
    private String pperDoctorGuideEffect;		// pper_doctor_guide_effect
    private String treatMethodJudgement;		// 治疗类别判断
    private String dutyNurse;		// 责任护士
    private String deathReason;		// 死亡原因
    private Date deathDateTime;		// 死亡时间
    private String scienceResearchIndicator;		// 科研病历
    private String firstOperationIndicator;		// 手术为本院第一例
    private String firstTreatmentIndicator;		// 治疗为本院第一例
    private String firstExaminationIndicator;		// 检查为本院第一例
    private String firstDiagnosisIndicator;		// 诊断为本院第一例
    private String infusionReactIndicator;		// infusion_react_indicator
    private String seriousIndicator;		// 住院期间是否出现危重
    private String adtRoomNo;		// 入院病室
    private String ddtRoomNo;		// 出院病室
    private Integer infectIndicator;		// 感染类别
    private String healthLevel;		// 健康等级
    private String mrInfectReport;		// 诊断错漏
    private String newborn;		// 是否新生儿
    private Integer thirdLevelNurseDays;		// 三级护理天数
    private String insuranceAera;		// 保险地区
    private Double bodyWeight;		// 体重
    private Double bodyHeight;		// 身高
    private String businessZipCode;		// 工作单位邮政编码
    private Long infusionTranTimes;		// 输液次数
    private Integer changeIndicator;		// change_indicator
    private Date documDate;		// docum_date
    private Integer documDays;		// docum_days
    private String documPerson;		// docum_person
    private String zymosisIndicator;		// 传染病标志(1已报 2未报 3无)
    private Date zymosisDate;		// 上报日期
    private Integer breathMachTimes;		// 呼吸机用时
    private Integer comaTimesB1;		// 昏迷时间(入院前小时)
    private Integer comaTimesB2;		// 入院前分钟
    private Integer comaTimesA1;		// 入院后小时
    private Integer comaTimesA2;		// 入院后分钟
    private String transHospital;		// 转入医院名称
    private String transCommunity;		// 转社区名称
    private String phoneNumberBusiness;		// 工作单位电话
    private String mrBinder;		// 整理者
    private Long carryPersonNumber;		// carry_person_number
    private String changeOperator;		// change_operator
    private Integer dischargeBedNo;		// discharge_bed_no
    private String dischargeWardCode;		// discharge_ward_code
    private String inpSerialNo;		// inp_serial_no
    private String nhzyh;		// nhzyh
    private String ffXsdm;		// ff_xsdm
    private String transFlag;		// trans_flag
    private String gzFlag;		// gz_flag
    private Date visitDate;		// visit_date
    private Integer visitNo;		// visit_no
    private String phoneNumberHome;		// phone_number_home
    private String patientArea;		// patient_area
    private String catalogStatus;		// catalog_status
    private Date lockedDate;		// locked_date
    private Date diagnoseDate;		// diagnose_date
    private String medicalPayWay;		// medical_pay_way
    private Date statisticsDiagnoseDate;		// statistics_diagnose_date
    private Integer documFlag;		// docum_flag
    private Integer statisticsFlag;		// statistics_flag
    private Integer weightBirth;		// 新生儿体重，单位克
    private Integer comaTimesB0;		// coma_times_b0
    private Integer comaTimesA0;		// coma_times_a0
    private String patientAreaAddress;		// patient_area_address
    private String patStreetAddress;		// pat_street_address
    private String patPhone;		// pat_phone
    private String patZip;		// pat_zip
    private String plan31Admission;		// plan_31_admission
    private String reason31Admission;		// reason_31_admission
    private String tumorT;		// tumor_t
    private String tumorN;		// tumor_n
    private String tumorM;		// tumor_m
    private String tumorStage;		// tumor_stage
    private Double adlAdm;		// adl_adm
    private Double adlDis;		// adl_dis
    private String basisOn;		// basis_on
    private String diffId;		// diff_id
    private String lockedUser;		// locked_user
    private String inpNo;		// inp_no
    private String returnIndicator;		// return_indicator
    private Integer patChargeType;		// 住院收费类型
    private String operator;		// 收费类型录入人
    private Date enterDate;		// 收费类型录入时间
    private Integer parityNo;		// 胎次
    private Date onsetDate;		// 发病日期
    private String nhSerialNo;		// 农合登记流水号
    private String ip;		// 农合IP
    private String registAttr;		// 农合登记属性
    private String visitType;		// 就诊类型
    private String antibioticsUsed;		// antibiotics_used
    private Integer antibioticsCount;		// antibiotics_count
    private String antibioticsUnion;		// antibiotics_union
    private String germicultureDrugTest;		// germiculture_drug_test
    private String infectiousDiseaseReport;		// infectious_disease_report
    private String bloodTranFlag;		// blood_tran_flag
    private String drgs;		// drgs
    private Integer admissionBedNo;		// admission_bed_no
    private Integer newbornInhBodyWeight;		// newborn_inh_body_weight
    private Integer newbornBodyWeight;		// newborn_body_weight
    private Integer pofFlag;		// pof_flag
    private String clinicalPathway;		// clinical_pathway
    private String chineseMedEquipment;		// chinese_med_equipment
    private String chineseMedTechnology;		// chinese_med_technology
    private String dialecticalNursing;		// dialectical_nursing
    private String ybInp;		// 医保住院号
    private String nhzzType;		// nhzz_type

    public PatVisit() {
        super();
    }

    public PatVisit(String id){
        super(id);
    }

    @Length(min=1, max=64, message="医院ID长度必须介于 1 和 64 之间")
    public String getHosid() {
        return hosid;
    }

    public void setHosid(String hosid) {
        this.hosid = hosid;
    }

    public Integer getVisitId() {
        return visitId;
    }

    public void setVisitId(Integer visitId) {
        this.visitId = visitId;
    }

    @Length(min=0, max=80, message="入院科室长度必须介于 0 和 80 之间")
    public String getDeptAdmissionTo() {
        return deptAdmissionTo;
    }

    public void setDeptAdmissionTo(String deptAdmissionTo) {
        this.deptAdmissionTo = deptAdmissionTo;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getAdmissionDateTime() {
        return admissionDateTime;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setAdmissionDateTime(Date admissionDateTime) {
        this.admissionDateTime = admissionDateTime;
    }

    @Length(min=0, max=80, message="出院科室长度必须介于 0 和 80 之间")
    public String getDeptDischargeFrom() {
        return deptDischargeFrom;
    }

    public void setDeptDischargeFrom(String deptDischargeFrom) {
        this.deptDischargeFrom = deptDischargeFrom;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDischargeDateTime() {
        return dischargeDateTime;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setDischargeDateTime(Date dischargeDateTime) {
        this.dischargeDateTime = dischargeDateTime;
    }

    @Length(min=0, max=80, message="职业长度必须介于 0 和 80 之间")
    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    @Length(min=0, max=80, message="婚姻状况长度必须介于 0 和 80 之间")
    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Length(min=0, max=10, message="身份长度必须介于 0 和 10 之间")
    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Length(min=0, max=80, message="军种长度必须介于 0 和 80 之间")
    public String getArmedServices() {
        return armedServices;
    }

    public void setArmedServices(String armedServices) {
        this.armedServices = armedServices;
    }

    @Length(min=0, max=80, message="勤务长度必须介于 0 和 80 之间")
    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    @Length(min=0, max=80, message="隶属大单位长度必须介于 0 和 80 之间")
    public String getTopUnit() {
        return topUnit;
    }

    public void setTopUnit(String topUnit) {
        this.topUnit = topUnit;
    }

    public Integer getServiceSystemIndicator() {
        return serviceSystemIndicator;
    }

    public void setServiceSystemIndicator(Integer serviceSystemIndicator) {
        this.serviceSystemIndicator = serviceSystemIndicator;
    }

    @Length(min=0, max=11, message="合同单位长度必须介于 0 和 11 之间")
    public String getUnitInContract() {
        return unitInContract;
    }

    public void setUnitInContract(String unitInContract) {
        this.unitInContract = unitInContract;
    }

    @Length(min=0, max=80, message="医保类别长度必须介于 0 和 80 之间")
    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public Integer getWorkingStatus() {
        return workingStatus;
    }

    public void setWorkingStatus(Integer workingStatus) {
        this.workingStatus = workingStatus;
    }

    @Length(min=0, max=16, message="工作单位长度必须介于 0 和 16 之间")
    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    @Length(min=0, max=18, message="医疗保险号长度必须介于 0 和 18 之间")
    public String getInsuranceNo() {
        return insuranceNo;
    }

    public void setInsuranceNo(String insuranceNo) {
        this.insuranceNo = insuranceNo;
    }

    @Length(min=0, max=40, message="医疗体系病人标志长度必须介于 0 和 40 之间")
    public String getServiceAgency() {
        return serviceAgency;
    }

    public void setServiceAgency(String serviceAgency) {
        this.serviceAgency = serviceAgency;
    }

    @Length(min=0, max=100, message="通信地址(户口地址)长度必须介于 0 和 100 之间")
    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    @Length(min=0, max=80, message="邮政编码长度必须介于 0 和 80 之间")
    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Length(min=0, max=80, message="联系人姓名长度必须介于 0 和 80 之间")
    public String getNextOfKin() {
        return nextOfKin;
    }

    public void setNextOfKin(String nextOfKin) {
        this.nextOfKin = nextOfKin;
    }

    @Length(min=0, max=80, message="与联系人关系长度必须介于 0 和 80 之间")
    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    @Length(min=0, max=40, message="联系人地址长度必须介于 0 和 40 之间")
    public String getNextOfKinAddr() {
        return nextOfKinAddr;
    }

    public void setNextOfKinAddr(String nextOfKinAddr) {
        this.nextOfKinAddr = nextOfKinAddr;
    }

    @Length(min=0, max=80, message="联系人邮政编码长度必须介于 0 和 80 之间")
    public String getNextOfKinZipcode() {
        return nextOfKinZipcode;
    }

    public void setNextOfKinZipcode(String nextOfKinZipcode) {
        this.nextOfKinZipcode = nextOfKinZipcode;
    }

    @Length(min=0, max=16, message="联系人电话长度必须介于 0 和 16 之间")
    public String getNextOfKinPhone() {
        return nextOfKinPhone;
    }

    public void setNextOfKinPhone(String nextOfKinPhone) {
        this.nextOfKinPhone = nextOfKinPhone;
    }

    @Length(min=0, max=80, message="入院方式长度必须介于 0 和 80 之间")
    public String getPatientClass() {
        return patientClass;
    }

    public void setPatientClass(String patientClass) {
        this.patientClass = patientClass;
    }

    @Length(min=0, max=80, message="住院目的长度必须介于 0 和 80 之间")
    public String getAdmissionCause() {
        return admissionCause;
    }

    public void setAdmissionCause(String admissionCause) {
        this.admissionCause = admissionCause;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getConsultingDate() {
        return consultingDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setConsultingDate(Date consultingDate) {
        this.consultingDate = consultingDate;
    }

    @Length(min=0, max=80, message="入院病情长度必须介于 0 和 80 之间")
    public String getPatAdmCondition() {
        return patAdmCondition;
    }

    public void setPatAdmCondition(String patAdmCondition) {
        this.patAdmCondition = patAdmCondition;
    }

    @Length(min=0, max=80, message="门诊医师长度必须介于 0 和 80 之间")
    public String getConsultingDoctor() {
        return consultingDoctor;
    }

    public void setConsultingDoctor(String consultingDoctor) {
        this.consultingDoctor = consultingDoctor;
    }

    @Length(min=0, max=80, message="办理住院者长度必须介于 0 和 80 之间")
    public String getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(String admittedBy) {
        this.admittedBy = admittedBy;
    }

    public Integer getEmerTreatTimes() {
        return emerTreatTimes;
    }

    public void setEmerTreatTimes(Integer emerTreatTimes) {
        this.emerTreatTimes = emerTreatTimes;
    }

    public Integer getEscEmerTimes() {
        return escEmerTimes;
    }

    public void setEscEmerTimes(Integer escEmerTimes) {
        this.escEmerTimes = escEmerTimes;
    }

    public Integer getSeriousCondDays() {
        return seriousCondDays;
    }

    public void setSeriousCondDays(Integer seriousCondDays) {
        this.seriousCondDays = seriousCondDays;
    }

    public Integer getCriticalCondDays() {
        return criticalCondDays;
    }

    public void setCriticalCondDays(Integer criticalCondDays) {
        this.criticalCondDays = criticalCondDays;
    }

    public Integer getIcuDays() {
        return icuDays;
    }

    public void setIcuDays(Integer icuDays) {
        this.icuDays = icuDays;
    }

    public Integer getCcuDays() {
        return ccuDays;
    }

    public void setCcuDays(Integer ccuDays) {
        this.ccuDays = ccuDays;
    }

    public Integer getSpecLevelNursDays() {
        return specLevelNursDays;
    }

    public void setSpecLevelNursDays(Integer specLevelNursDays) {
        this.specLevelNursDays = specLevelNursDays;
    }

    public Integer getFirstLevelNursDays() {
        return firstLevelNursDays;
    }

    public void setFirstLevelNursDays(Integer firstLevelNursDays) {
        this.firstLevelNursDays = firstLevelNursDays;
    }

    public Integer getSecondLevelNursDays() {
        return secondLevelNursDays;
    }

    public void setSecondLevelNursDays(Integer secondLevelNursDays) {
        this.secondLevelNursDays = secondLevelNursDays;
    }

    public Integer getAutopsyIndicator() {
        return autopsyIndicator;
    }

    public void setAutopsyIndicator(Integer autopsyIndicator) {
        this.autopsyIndicator = autopsyIndicator;
    }

    @Length(min=0, max=80, message="血型长度必须介于 0 和 80 之间")
    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    @Length(min=0, max=80, message="Rh血型长度必须介于 0 和 80 之间")
    public String getBloodTypeRh() {
        return bloodTypeRh;
    }

    public void setBloodTypeRh(String bloodTypeRh) {
        this.bloodTypeRh = bloodTypeRh;
    }

    public Integer getInfusionReactTimes() {
        return infusionReactTimes;
    }

    public void setInfusionReactTimes(Integer infusionReactTimes) {
        this.infusionReactTimes = infusionReactTimes;
    }

    public Integer getBloodTranTimes() {
        return bloodTranTimes;
    }

    public void setBloodTranTimes(Integer bloodTranTimes) {
        this.bloodTranTimes = bloodTranTimes;
    }

    public Integer getBloodTranVol() {
        return bloodTranVol;
    }

    public void setBloodTranVol(Integer bloodTranVol) {
        this.bloodTranVol = bloodTranVol;
    }

    public Integer getBloodTranReactTimes() {
        return bloodTranReactTimes;
    }

    public void setBloodTranReactTimes(Integer bloodTranReactTimes) {
        this.bloodTranReactTimes = bloodTranReactTimes;
    }

    public Integer getDecubitalUlcerTimes() {
        return decubitalUlcerTimes;
    }

    public void setDecubitalUlcerTimes(Integer decubitalUlcerTimes) {
        this.decubitalUlcerTimes = decubitalUlcerTimes;
    }

    @Length(min=0, max=80, message="过敏药物长度必须介于 0 和 80 之间")
    public String getAlergyDrugs() {
        return alergyDrugs;
    }

    public void setAlergyDrugs(String alergyDrugs) {
        this.alergyDrugs = alergyDrugs;
    }

    @Length(min=0, max=80, message="不良反应药物长度必须介于 0 和 80 之间")
    public String getAdverseReactionDrugs() {
        return adverseReactionDrugs;
    }

    public void setAdverseReactionDrugs(String adverseReactionDrugs) {
        this.adverseReactionDrugs = adverseReactionDrugs;
    }

    @Length(min=0, max=80, message="病案价值长度必须介于 0 和 80 之间")
    public String getMrValue() {
        return mrValue;
    }

    public void setMrValue(String mrValue) {
        this.mrValue = mrValue;
    }

    @Length(min=0, max=80, message="病案质量长度必须介于 0 和 80 之间")
    public String getMrQuality() {
        return mrQuality;
    }

    public void setMrQuality(String mrQuality) {
        this.mrQuality = mrQuality;
    }

    public Integer getFollowIndicator() {
        return followIndicator;
    }

    public void setFollowIndicator(Integer followIndicator) {
        this.followIndicator = followIndicator;
    }

    public Integer getFollowInterval() {
        return followInterval;
    }

    public void setFollowInterval(Integer followInterval) {
        this.followInterval = followInterval;
    }

    @Length(min=0, max=2, message="随诊期限单位长度必须介于 0 和 2 之间")
    public String getFollowIntervalUnits() {
        return followIntervalUnits;
    }

    public void setFollowIntervalUnits(String followIntervalUnits) {
        this.followIntervalUnits = followIntervalUnits;
    }

    @Length(min=0, max=80, message="科主任长度必须介于 0 和 80 之间")
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Length(min=0, max=80, message="主治医师长度必须介于 0 和 80 之间")
    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    public void setAttendingDoctor(String attendingDoctor) {
        this.attendingDoctor = attendingDoctor;
    }

    @Length(min=0, max=80, message="经治医师长度必须介于 0 和 80 之间")
    public String getDoctorInCharge() {
        return doctorInCharge;
    }

    public void setDoctorInCharge(String doctorInCharge) {
        this.doctorInCharge = doctorInCharge;
    }

    @Length(min=0, max=80, message="出院方式长度必须介于 0 和 80 之间")
    public String getDischargeDisposition() {
        return dischargeDisposition;
    }

    public void setDischargeDisposition(String dischargeDisposition) {
        this.dischargeDisposition = dischargeDisposition;
    }

    public Double getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(Double totalCosts) {
        this.totalCosts = totalCosts;
    }

    public Double getTotalPayments() {
        return totalPayments;
    }

    public void setTotalPayments(Double totalPayments) {
        this.totalPayments = totalPayments;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCatalogDate() {
        return catalogDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setCatalogDate(Date catalogDate) {
        this.catalogDate = catalogDate;
    }

    @Length(min=0, max=80, message="编目人长度必须介于 0 和 80 之间")
    public String getCataloger() {
        return cataloger;
    }

    public void setCataloger(String cataloger) {
        this.cataloger = cataloger;
    }

    public Integer getHbsagIndicator() {
        return hbsagIndicator;
    }

    public void setHbsagIndicator(Integer hbsagIndicator) {
        this.hbsagIndicator = hbsagIndicator;
    }

    public Integer getHcvAbIndicator() {
        return hcvAbIndicator;
    }

    public void setHcvAbIndicator(Integer hcvAbIndicator) {
        this.hcvAbIndicator = hcvAbIndicator;
    }

    public Integer getHivAbIndicator() {
        return hivAbIndicator;
    }

    public void setHivAbIndicator(Integer hivAbIndicator) {
        this.hivAbIndicator = hivAbIndicator;
    }

    @Length(min=0, max=80, message="主任医师长度必须介于 0 和 80 之间")
    public String getChiefDoctor() {
        return chiefDoctor;
    }

    public void setChiefDoctor(String chiefDoctor) {
        this.chiefDoctor = chiefDoctor;
    }

    @Length(min=0, max=80, message="进修医师长度必须介于 0 和 80 之间")
    public String getAdvancedStudiesDoctor() {
        return advancedStudiesDoctor;
    }

    public void setAdvancedStudiesDoctor(String advancedStudiesDoctor) {
        this.advancedStudiesDoctor = advancedStudiesDoctor;
    }

    @Length(min=0, max=80, message="研究生实习医师长度必须介于 0 和 80 之间")
    public String getPracticeDoctorOfGraduate() {
        return practiceDoctorOfGraduate;
    }

    public void setPracticeDoctorOfGraduate(String practiceDoctorOfGraduate) {
        this.practiceDoctorOfGraduate = practiceDoctorOfGraduate;
    }

    @Length(min=0, max=80, message="实习医师长度必须介于 0 和 80 之间")
    public String getPracticeDoctor() {
        return practiceDoctor;
    }

    public void setPracticeDoctor(String practiceDoctor) {
        this.practiceDoctor = practiceDoctor;
    }

    @Length(min=0, max=80, message="质控医师长度必须介于 0 和 80 之间")
    public String getDoctorOfControlQuality() {
        return doctorOfControlQuality;
    }

    public void setDoctorOfControlQuality(String doctorOfControlQuality) {
        this.doctorOfControlQuality = doctorOfControlQuality;
    }

    @Length(min=0, max=80, message="质控护士长度必须介于 0 和 80 之间")
    public String getNurseOfControlQuality() {
        return nurseOfControlQuality;
    }

    public void setNurseOfControlQuality(String nurseOfControlQuality) {
        this.nurseOfControlQuality = nurseOfControlQuality;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDateOfControlQuality() {
        return dateOfControlQuality;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setDateOfControlQuality(Date dateOfControlQuality) {
        this.dateOfControlQuality = dateOfControlQuality;
    }

    public Integer getFirstCaseIndicator() {
        return firstCaseIndicator;
    }

    public void setFirstCaseIndicator(Integer firstCaseIndicator) {
        this.firstCaseIndicator = firstCaseIndicator;
    }

    public Integer getThirdLevelNursDays() {
        return thirdLevelNursDays;
    }

    public void setThirdLevelNursDays(Integer thirdLevelNursDays) {
        this.thirdLevelNursDays = thirdLevelNursDays;
    }

    @Length(min=0, max=80, message="X线号长度必须介于 0 和 80 之间")
    public String getXExamNo() {
        return xExamNo;
    }

    public void setXExamNo(String xExamNo) {
        this.xExamNo = xExamNo;
    }

    @Length(min=0, max=80, message="入院前经外院诊治长度必须介于 0 和 80 之间")
    public String getTreatedInOthersIndicator() {
        return treatedInOthersIndicator;
    }

    public void setTreatedInOthersIndicator(String treatedInOthersIndicator) {
        this.treatedInOthersIndicator = treatedInOthersIndicator;
    }

    @Length(min=0, max=80, message="治疗类别长度必须介于 0 和 80 之间")
    public String getTreatMethod() {
        return treatMethod;
    }

    public void setTreatMethod(String treatMethod) {
        this.treatMethod = treatMethod;
    }

    @Length(min=0, max=80, message="自制中药制剂长度必须介于 0 和 80 之间")
    public String getHospMadeMedicineIndicator() {
        return hospMadeMedicineIndicator;
    }

    public void setHospMadeMedicineIndicator(String hospMadeMedicineIndicator) {
        this.hospMadeMedicineIndicator = hospMadeMedicineIndicator;
    }

    @Length(min=0, max=80, message="病理号长度必须介于 0 和 80 之间")
    public String getPathologyNo() {
        return pathologyNo;
    }

    public void setPathologyNo(String pathologyNo) {
        this.pathologyNo = pathologyNo;
    }

    @Length(min=0, max=80, message="上级医生指导作用长度必须介于 0 和 80 之间")
    public String getUpperDoctorGuideEffect() {
        return upperDoctorGuideEffect;
    }

    public void setUpperDoctorGuideEffect(String upperDoctorGuideEffect) {
        this.upperDoctorGuideEffect = upperDoctorGuideEffect;
    }

    @Length(min=0, max=80, message="抢救方法长度必须介于 0 和 80 之间")
    public String getEmerTreatMethod() {
        return emerTreatMethod;
    }

    public void setEmerTreatMethod(String emerTreatMethod) {
        this.emerTreatMethod = emerTreatMethod;
    }

    @Length(min=0, max=80, message="住院期间是否出现急症长度必须介于 0 和 80 之间")
    public String getIctusIndicator() {
        return ictusIndicator;
    }

    public void setIctusIndicator(String ictusIndicator) {
        this.ictusIndicator = ictusIndicator;
    }

    @Length(min=0, max=80, message="住院期间是否出现危难长度必须介于 0 和 80 之间")
    public String getDifficultyIndicator() {
        return difficultyIndicator;
    }

    public void setDifficultyIndicator(String difficultyIndicator) {
        this.difficultyIndicator = difficultyIndicator;
    }

    @Length(min=0, max=80, message="外县市来病人长度必须介于 0 和 80 之间")
    public String getFromOtherPlaceIndicator() {
        return fromOtherPlaceIndicator;
    }

    public void setFromOtherPlaceIndicator(String fromOtherPlaceIndicator) {
        this.fromOtherPlaceIndicator = fromOtherPlaceIndicator;
    }

    @Length(min=0, max=80, message="出院诊断疑诊长度必须介于 0 和 80 之间")
    public String getSuspicionIndicator() {
        return suspicionIndicator;
    }

    public void setSuspicionIndicator(String suspicionIndicator) {
        this.suspicionIndicator = suspicionIndicator;
    }

    @Length(min=0, max=80, message="中医特色治疗长度必须介于 0 和 80 之间")
    public String getChineseMedicineIndicator() {
        return chineseMedicineIndicator;
    }

    public void setChineseMedicineIndicator(String chineseMedicineIndicator) {
        this.chineseMedicineIndicator = chineseMedicineIndicator;
    }

    @Length(min=0, max=80, message="手术级别长度必须介于 0 和 80 之间")
    public String getOperationScale() {
        return operationScale;
    }

    public void setOperationScale(String operationScale) {
        this.operationScale = operationScale;
    }

    @Length(min=0, max=80, message="辨证准确度长度必须介于 0 和 80 之间")
    public String getDiagnosisCorrectness() {
        return diagnosisCorrectness;
    }

    public void setDiagnosisCorrectness(String diagnosisCorrectness) {
        this.diagnosisCorrectness = diagnosisCorrectness;
    }

    @Length(min=0, max=80, message="治法准确度长度必须介于 0 和 80 之间")
    public String getTreatMethodCorrectness() {
        return treatMethodCorrectness;
    }

    public void setTreatMethodCorrectness(String treatMethodCorrectness) {
        this.treatMethodCorrectness = treatMethodCorrectness;
    }

    @Length(min=0, max=80, message="方药准确度长度必须介于 0 和 80 之间")
    public String getPrescriptionCorrectness() {
        return prescriptionCorrectness;
    }

    public void setPrescriptionCorrectness(String prescriptionCorrectness) {
        this.prescriptionCorrectness = prescriptionCorrectness;
    }

    @Length(min=0, max=80, message="病案书写齐全长度必须介于 0 和 80 之间")
    public String getMrCompleteIndicator() {
        return mrCompleteIndicator;
    }

    public void setMrCompleteIndicator(String mrCompleteIndicator) {
        this.mrCompleteIndicator = mrCompleteIndicator;
    }

    @Length(min=0, max=80, message="医学术语应用正确长度必须介于 0 和 80 之间")
    public String getMedicalTermCorrectness() {
        return medicalTermCorrectness;
    }

    public void setMedicalTermCorrectness(String medicalTermCorrectness) {
        this.medicalTermCorrectness = medicalTermCorrectness;
    }

    @Length(min=0, max=80, message="pper_doctor_guide_effect长度必须介于 0 和 80 之间")
    public String getPperDoctorGuideEffect() {
        return pperDoctorGuideEffect;
    }

    public void setPperDoctorGuideEffect(String pperDoctorGuideEffect) {
        this.pperDoctorGuideEffect = pperDoctorGuideEffect;
    }

    @Length(min=0, max=80, message="治疗类别判断长度必须介于 0 和 80 之间")
    public String getTreatMethodJudgement() {
        return treatMethodJudgement;
    }

    public void setTreatMethodJudgement(String treatMethodJudgement) {
        this.treatMethodJudgement = treatMethodJudgement;
    }

    @Length(min=0, max=80, message="责任护士长度必须介于 0 和 80 之间")
    public String getDutyNurse() {
        return dutyNurse;
    }

    public void setDutyNurse(String dutyNurse) {
        this.dutyNurse = dutyNurse;
    }

    @Length(min=0, max=40, message="死亡原因长度必须介于 0 和 40 之间")
    public String getDeathReason() {
        return deathReason;
    }

    public void setDeathReason(String deathReason) {
        this.deathReason = deathReason;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDeathDateTime() {
        return deathDateTime;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setDeathDateTime(Date deathDateTime) {
        this.deathDateTime = deathDateTime;
    }

    @Length(min=0, max=80, message="科研病历长度必须介于 0 和 80 之间")
    public String getScienceResearchIndicator() {
        return scienceResearchIndicator;
    }

    public void setScienceResearchIndicator(String scienceResearchIndicator) {
        this.scienceResearchIndicator = scienceResearchIndicator;
    }

    @Length(min=0, max=80, message="手术为本院第一例长度必须介于 0 和 80 之间")
    public String getFirstOperationIndicator() {
        return firstOperationIndicator;
    }

    public void setFirstOperationIndicator(String firstOperationIndicator) {
        this.firstOperationIndicator = firstOperationIndicator;
    }

    @Length(min=0, max=80, message="治疗为本院第一例长度必须介于 0 和 80 之间")
    public String getFirstTreatmentIndicator() {
        return firstTreatmentIndicator;
    }

    public void setFirstTreatmentIndicator(String firstTreatmentIndicator) {
        this.firstTreatmentIndicator = firstTreatmentIndicator;
    }

    @Length(min=0, max=80, message="检查为本院第一例长度必须介于 0 和 80 之间")
    public String getFirstExaminationIndicator() {
        return firstExaminationIndicator;
    }

    public void setFirstExaminationIndicator(String firstExaminationIndicator) {
        this.firstExaminationIndicator = firstExaminationIndicator;
    }

    @Length(min=0, max=80, message="诊断为本院第一例长度必须介于 0 和 80 之间")
    public String getFirstDiagnosisIndicator() {
        return firstDiagnosisIndicator;
    }

    public void setFirstDiagnosisIndicator(String firstDiagnosisIndicator) {
        this.firstDiagnosisIndicator = firstDiagnosisIndicator;
    }

    @Length(min=0, max=80, message="infusion_react_indicator长度必须介于 0 和 80 之间")
    public String getInfusionReactIndicator() {
        return infusionReactIndicator;
    }

    public void setInfusionReactIndicator(String infusionReactIndicator) {
        this.infusionReactIndicator = infusionReactIndicator;
    }

    @Length(min=0, max=80, message="住院期间是否出现危重长度必须介于 0 和 80 之间")
    public String getSeriousIndicator() {
        return seriousIndicator;
    }

    public void setSeriousIndicator(String seriousIndicator) {
        this.seriousIndicator = seriousIndicator;
    }

    @Length(min=0, max=80, message="入院病室长度必须介于 0 和 80 之间")
    public String getAdtRoomNo() {
        return adtRoomNo;
    }

    public void setAdtRoomNo(String adtRoomNo) {
        this.adtRoomNo = adtRoomNo;
    }

    @Length(min=0, max=80, message="出院病室长度必须介于 0 和 80 之间")
    public String getDdtRoomNo() {
        return ddtRoomNo;
    }

    public void setDdtRoomNo(String ddtRoomNo) {
        this.ddtRoomNo = ddtRoomNo;
    }

    public Integer getInfectIndicator() {
        return infectIndicator;
    }

    public void setInfectIndicator(Integer infectIndicator) {
        this.infectIndicator = infectIndicator;
    }

    @Length(min=0, max=2, message="健康等级长度必须介于 0 和 2 之间")
    public String getHealthLevel() {
        return healthLevel;
    }

    public void setHealthLevel(String healthLevel) {
        this.healthLevel = healthLevel;
    }

    @Length(min=0, max=4, message="诊断错漏长度必须介于 0 和 4 之间")
    public String getMrInfectReport() {
        return mrInfectReport;
    }

    public void setMrInfectReport(String mrInfectReport) {
        this.mrInfectReport = mrInfectReport;
    }

    @Length(min=0, max=1, message="是否新生儿长度必须介于 0 和 1 之间")
    public String getNewborn() {
        return newborn;
    }

    public void setNewborn(String newborn) {
        this.newborn = newborn;
    }

    public Integer getThirdLevelNurseDays() {
        return thirdLevelNurseDays;
    }

    public void setThirdLevelNurseDays(Integer thirdLevelNurseDays) {
        this.thirdLevelNurseDays = thirdLevelNurseDays;
    }

    @Length(min=0, max=60, message="保险地区长度必须介于 0 和 60 之间")
    public String getInsuranceAera() {
        return insuranceAera;
    }

    public void setInsuranceAera(String insuranceAera) {
        this.insuranceAera = insuranceAera;
    }

    public Double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public Double getBodyHeight() {
        return bodyHeight;
    }

    public void setBodyHeight(Double bodyHeight) {
        this.bodyHeight = bodyHeight;
    }

    @Length(min=0, max=6, message="工作单位邮政编码长度必须介于 0 和 6 之间")
    public String getBusinessZipCode() {
        return businessZipCode;
    }

    public void setBusinessZipCode(String businessZipCode) {
        this.businessZipCode = businessZipCode;
    }

    public Long getInfusionTranTimes() {
        return infusionTranTimes;
    }

    public void setInfusionTranTimes(Long infusionTranTimes) {
        this.infusionTranTimes = infusionTranTimes;
    }

    public Integer getChangeIndicator() {
        return changeIndicator;
    }

    public void setChangeIndicator(Integer changeIndicator) {
        this.changeIndicator = changeIndicator;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDocumDate() {
        return documDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setDocumDate(Date documDate) {
        this.documDate = documDate;
    }

    public Integer getDocumDays() {
        return documDays;
    }

    public void setDocumDays(Integer documDays) {
        this.documDays = documDays;
    }

    @Length(min=0, max=80, message="docum_person长度必须介于 0 和 80 之间")
    public String getDocumPerson() {
        return documPerson;
    }

    public void setDocumPerson(String documPerson) {
        this.documPerson = documPerson;
    }

    @Length(min=0, max=80, message="传染病标志(1已报 2未报 3无)长度必须介于 0 和 80 之间")
    public String getZymosisIndicator() {
        return zymosisIndicator;
    }

    public void setZymosisIndicator(String zymosisIndicator) {
        this.zymosisIndicator = zymosisIndicator;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getZymosisDate() {
        return zymosisDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setZymosisDate(Date zymosisDate) {
        this.zymosisDate = zymosisDate;
    }

    public Integer getBreathMachTimes() {
        return breathMachTimes;
    }

    public void setBreathMachTimes(Integer breathMachTimes) {
        this.breathMachTimes = breathMachTimes;
    }

    public Integer getComaTimesB1() {
        return comaTimesB1;
    }

    public void setComaTimesB1(Integer comaTimesB1) {
        this.comaTimesB1 = comaTimesB1;
    }

    public Integer getComaTimesB2() {
        return comaTimesB2;
    }

    public void setComaTimesB2(Integer comaTimesB2) {
        this.comaTimesB2 = comaTimesB2;
    }

    public Integer getComaTimesA1() {
        return comaTimesA1;
    }

    public void setComaTimesA1(Integer comaTimesA1) {
        this.comaTimesA1 = comaTimesA1;
    }

    public Integer getComaTimesA2() {
        return comaTimesA2;
    }

    public void setComaTimesA2(Integer comaTimesA2) {
        this.comaTimesA2 = comaTimesA2;
    }

    @Length(min=0, max=100, message="转入医院名称长度必须介于 0 和 100 之间")
    public String getTransHospital() {
        return transHospital;
    }

    public void setTransHospital(String transHospital) {
        this.transHospital = transHospital;
    }

    @Length(min=0, max=100, message="转社区名称长度必须介于 0 和 100 之间")
    public String getTransCommunity() {
        return transCommunity;
    }

    public void setTransCommunity(String transCommunity) {
        this.transCommunity = transCommunity;
    }

    @Length(min=0, max=80, message="工作单位电话长度必须介于 0 和 80 之间")
    public String getPhoneNumberBusiness() {
        return phoneNumberBusiness;
    }

    public void setPhoneNumberBusiness(String phoneNumberBusiness) {
        this.phoneNumberBusiness = phoneNumberBusiness;
    }

    @Length(min=0, max=80, message="整理者长度必须介于 0 和 80 之间")
    public String getMrBinder() {
        return mrBinder;
    }

    public void setMrBinder(String mrBinder) {
        this.mrBinder = mrBinder;
    }

    public Long getCarryPersonNumber() {
        return carryPersonNumber;
    }

    public void setCarryPersonNumber(Long carryPersonNumber) {
        this.carryPersonNumber = carryPersonNumber;
    }

    @Length(min=0, max=80, message="change_operator长度必须介于 0 和 80 之间")
    public String getChangeOperator() {
        return changeOperator;
    }

    public void setChangeOperator(String changeOperator) {
        this.changeOperator = changeOperator;
    }

    public Integer getDischargeBedNo() {
        return dischargeBedNo;
    }

    public void setDischargeBedNo(Integer dischargeBedNo) {
        this.dischargeBedNo = dischargeBedNo;
    }

    @Length(min=0, max=32, message="discharge_ward_code长度必须介于 0 和 32 之间")
    public String getDischargeWardCode() {
        return dischargeWardCode;
    }

    public void setDischargeWardCode(String dischargeWardCode) {
        this.dischargeWardCode = dischargeWardCode;
    }

    @Length(min=0, max=80, message="inp_serial_no长度必须介于 0 和 80 之间")
    public String getInpSerialNo() {
        return inpSerialNo;
    }

    public void setInpSerialNo(String inpSerialNo) {
        this.inpSerialNo = inpSerialNo;
    }

    @Length(min=0, max=32, message="nhzyh长度必须介于 0 和 32 之间")
    public String getNhzyh() {
        return nhzyh;
    }

    public void setNhzyh(String nhzyh) {
        this.nhzyh = nhzyh;
    }

    @Length(min=0, max=32, message="ff_xsdm长度必须介于 0 和 32 之间")
    public String getFfXsdm() {
        return ffXsdm;
    }

    public void setFfXsdm(String ffXsdm) {
        this.ffXsdm = ffXsdm;
    }

    @Length(min=0, max=32, message="trans_flag长度必须介于 0 和 32 之间")
    public String getTransFlag() {
        return transFlag;
    }

    public void setTransFlag(String transFlag) {
        this.transFlag = transFlag;
    }

    @Length(min=0, max=32, message="gz_flag长度必须介于 0 和 32 之间")
    public String getGzFlag() {
        return gzFlag;
    }

    public void setGzFlag(String gzFlag) {
        this.gzFlag = gzFlag;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getVisitDate() {
        return visitDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(Integer visitNo) {
        this.visitNo = visitNo;
    }

    @Length(min=0, max=80, message="phone_number_home长度必须介于 0 和 80 之间")
    public String getPhoneNumberHome() {
        return phoneNumberHome;
    }

    public void setPhoneNumberHome(String phoneNumberHome) {
        this.phoneNumberHome = phoneNumberHome;
    }

    @Length(min=0, max=80, message="patient_area长度必须介于 0 和 80 之间")
    public String getPatientArea() {
        return patientArea;
    }

    public void setPatientArea(String patientArea) {
        this.patientArea = patientArea;
    }

    @Length(min=0, max=80, message="catalog_status长度必须介于 0 和 80 之间")
    public String getCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(String catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getLockedDate() {
        return lockedDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDiagnoseDate() {
        return diagnoseDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setDiagnoseDate(Date diagnoseDate) {
        this.diagnoseDate = diagnoseDate;
    }

    @Length(min=0, max=80, message="medical_pay_way长度必须介于 0 和 80 之间")
    public String getMedicalPayWay() {
        return medicalPayWay;
    }

    public void setMedicalPayWay(String medicalPayWay) {
        this.medicalPayWay = medicalPayWay;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getStatisticsDiagnoseDate() {
        return statisticsDiagnoseDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setStatisticsDiagnoseDate(Date statisticsDiagnoseDate) {
        this.statisticsDiagnoseDate = statisticsDiagnoseDate;
    }

    public Integer getDocumFlag() {
        return documFlag;
    }

    public void setDocumFlag(Integer documFlag) {
        this.documFlag = documFlag;
    }

    public Integer getStatisticsFlag() {
        return statisticsFlag;
    }

    public void setStatisticsFlag(Integer statisticsFlag) {
        this.statisticsFlag = statisticsFlag;
    }

    public Integer getWeightBirth() {
        return weightBirth;
    }

    public void setWeightBirth(Integer weightBirth) {
        this.weightBirth = weightBirth;
    }

    public Integer getComaTimesB0() {
        return comaTimesB0;
    }

    public void setComaTimesB0(Integer comaTimesB0) {
        this.comaTimesB0 = comaTimesB0;
    }

    public Integer getComaTimesA0() {
        return comaTimesA0;
    }

    public void setComaTimesA0(Integer comaTimesA0) {
        this.comaTimesA0 = comaTimesA0;
    }

    @Length(min=0, max=800, message="patient_area_address长度必须介于 0 和 800 之间")
    public String getPatientAreaAddress() {
        return patientAreaAddress;
    }

    public void setPatientAreaAddress(String patientAreaAddress) {
        this.patientAreaAddress = patientAreaAddress;
    }

    @Length(min=0, max=80, message="pat_street_address长度必须介于 0 和 80 之间")
    public String getPatStreetAddress() {
        return patStreetAddress;
    }

    public void setPatStreetAddress(String patStreetAddress) {
        this.patStreetAddress = patStreetAddress;
    }

    @Length(min=0, max=80, message="pat_phone长度必须介于 0 和 80 之间")
    public String getPatPhone() {
        return patPhone;
    }

    public void setPatPhone(String patPhone) {
        this.patPhone = patPhone;
    }

    @Length(min=0, max=80, message="pat_zip长度必须介于 0 和 80 之间")
    public String getPatZip() {
        return patZip;
    }

    public void setPatZip(String patZip) {
        this.patZip = patZip;
    }

    @Length(min=0, max=80, message="plan_31_admission长度必须介于 0 和 80 之间")
    public String getPlan31Admission() {
        return plan31Admission;
    }

    public void setPlan31Admission(String plan31Admission) {
        this.plan31Admission = plan31Admission;
    }

    @Length(min=0, max=100, message="reason_31_admission长度必须介于 0 和 100 之间")
    public String getReason31Admission() {
        return reason31Admission;
    }

    public void setReason31Admission(String reason31Admission) {
        this.reason31Admission = reason31Admission;
    }

    @Length(min=0, max=80, message="tumor_t长度必须介于 0 和 80 之间")
    public String getTumorT() {
        return tumorT;
    }

    public void setTumorT(String tumorT) {
        this.tumorT = tumorT;
    }

    @Length(min=0, max=80, message="tumor_n长度必须介于 0 和 80 之间")
    public String getTumorN() {
        return tumorN;
    }

    public void setTumorN(String tumorN) {
        this.tumorN = tumorN;
    }

    @Length(min=0, max=80, message="tumor_m长度必须介于 0 和 80 之间")
    public String getTumorM() {
        return tumorM;
    }

    public void setTumorM(String tumorM) {
        this.tumorM = tumorM;
    }

    @Length(min=0, max=80, message="tumor_stage长度必须介于 0 和 80 之间")
    public String getTumorStage() {
        return tumorStage;
    }

    public void setTumorStage(String tumorStage) {
        this.tumorStage = tumorStage;
    }

    public Double getAdlAdm() {
        return adlAdm;
    }

    public void setAdlAdm(Double adlAdm) {
        this.adlAdm = adlAdm;
    }

    public Double getAdlDis() {
        return adlDis;
    }

    public void setAdlDis(Double adlDis) {
        this.adlDis = adlDis;
    }

    @Length(min=0, max=8, message="basis_on长度必须介于 0 和 8 之间")
    public String getBasisOn() {
        return basisOn;
    }

    public void setBasisOn(String basisOn) {
        this.basisOn = basisOn;
    }

    @Length(min=0, max=80, message="diff_id长度必须介于 0 和 80 之间")
    public String getDiffId() {
        return diffId;
    }

    public void setDiffId(String diffId) {
        this.diffId = diffId;
    }

    @Length(min=0, max=80, message="locked_user长度必须介于 0 和 80 之间")
    public String getLockedUser() {
        return lockedUser;
    }

    public void setLockedUser(String lockedUser) {
        this.lockedUser = lockedUser;
    }

    @Length(min=0, max=80, message="inp_no长度必须介于 0 和 80 之间")
    public String getInpNo() {
        return inpNo;
    }

    public void setInpNo(String inpNo) {
        this.inpNo = inpNo;
    }

    @Length(min=0, max=80, message="return_indicator长度必须介于 0 和 80 之间")
    public String getReturnIndicator() {
        return returnIndicator;
    }

    public void setReturnIndicator(String returnIndicator) {
        this.returnIndicator = returnIndicator;
    }

    public Integer getPatChargeType() {
        return patChargeType;
    }

    public void setPatChargeType(Integer patChargeType) {
        this.patChargeType = patChargeType;
    }

    @Length(min=0, max=80, message="收费类型录入人长度必须介于 0 和 80 之间")
    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getEnterDate() {
        return enterDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Integer getParityNo() {
        return parityNo;
    }

    public void setParityNo(Integer parityNo) {
        this.parityNo = parityNo;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getOnsetDate() {
        return onsetDate;
    }
    @JsonDeserialize(using = CustomDateDeSerializer.class)
    public void setOnsetDate(Date onsetDate) {
        this.onsetDate = onsetDate;
    }

    @Length(min=0, max=50, message="农合登记流水号长度必须介于 0 和 50 之间")
    public String getNhSerialNo() {
        return nhSerialNo;
    }

    public void setNhSerialNo(String nhSerialNo) {
        this.nhSerialNo = nhSerialNo;
    }

    @Length(min=0, max=50, message="农合IP长度必须介于 0 和 50 之间")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Length(min=0, max=80, message="农合登记属性长度必须介于 0 和 80 之间")
    public String getRegistAttr() {
        return registAttr;
    }

    public void setRegistAttr(String registAttr) {
        this.registAttr = registAttr;
    }

    @Length(min=0, max=80, message="就诊类型长度必须介于 0 和 80 之间")
    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    @Length(min=0, max=80, message="antibiotics_used长度必须介于 0 和 80 之间")
    public String getAntibioticsUsed() {
        return antibioticsUsed;
    }

    public void setAntibioticsUsed(String antibioticsUsed) {
        this.antibioticsUsed = antibioticsUsed;
    }

    public Integer getAntibioticsCount() {
        return antibioticsCount;
    }

    public void setAntibioticsCount(Integer antibioticsCount) {
        this.antibioticsCount = antibioticsCount;
    }

    @Length(min=0, max=80, message="antibiotics_union长度必须介于 0 和 80 之间")
    public String getAntibioticsUnion() {
        return antibioticsUnion;
    }

    public void setAntibioticsUnion(String antibioticsUnion) {
        this.antibioticsUnion = antibioticsUnion;
    }

    @Length(min=0, max=80, message="germiculture_drug_test长度必须介于 0 和 80 之间")
    public String getGermicultureDrugTest() {
        return germicultureDrugTest;
    }

    public void setGermicultureDrugTest(String germicultureDrugTest) {
        this.germicultureDrugTest = germicultureDrugTest;
    }

    @Length(min=0, max=80, message="infectious_disease_report长度必须介于 0 和 80 之间")
    public String getInfectiousDiseaseReport() {
        return infectiousDiseaseReport;
    }

    public void setInfectiousDiseaseReport(String infectiousDiseaseReport) {
        this.infectiousDiseaseReport = infectiousDiseaseReport;
    }

    @Length(min=0, max=80, message="blood_tran_flag长度必须介于 0 和 80 之间")
    public String getBloodTranFlag() {
        return bloodTranFlag;
    }

    public void setBloodTranFlag(String bloodTranFlag) {
        this.bloodTranFlag = bloodTranFlag;
    }

    @Length(min=0, max=80, message="drgs长度必须介于 0 和 80 之间")
    public String getDrgs() {
        return drgs;
    }

    public void setDrgs(String drgs) {
        this.drgs = drgs;
    }

    public Integer getAdmissionBedNo() {
        return admissionBedNo;
    }

    public void setAdmissionBedNo(Integer admissionBedNo) {
        this.admissionBedNo = admissionBedNo;
    }

    public Integer getNewbornInhBodyWeight() {
        return newbornInhBodyWeight;
    }

    public void setNewbornInhBodyWeight(Integer newbornInhBodyWeight) {
        this.newbornInhBodyWeight = newbornInhBodyWeight;
    }

    public Integer getNewbornBodyWeight() {
        return newbornBodyWeight;
    }

    public void setNewbornBodyWeight(Integer newbornBodyWeight) {
        this.newbornBodyWeight = newbornBodyWeight;
    }

    public Integer getPofFlag() {
        return pofFlag;
    }

    public void setPofFlag(Integer pofFlag) {
        this.pofFlag = pofFlag;
    }

    @Length(min=0, max=80, message="clinical_pathway长度必须介于 0 和 80 之间")
    public String getClinicalPathway() {
        return clinicalPathway;
    }

    public void setClinicalPathway(String clinicalPathway) {
        this.clinicalPathway = clinicalPathway;
    }

    @Length(min=0, max=80, message="chinese_med_equipment长度必须介于 0 和 80 之间")
    public String getChineseMedEquipment() {
        return chineseMedEquipment;
    }

    public void setChineseMedEquipment(String chineseMedEquipment) {
        this.chineseMedEquipment = chineseMedEquipment;
    }

    @Length(min=0, max=80, message="chinese_med_technology长度必须介于 0 和 80 之间")
    public String getChineseMedTechnology() {
        return chineseMedTechnology;
    }

    public void setChineseMedTechnology(String chineseMedTechnology) {
        this.chineseMedTechnology = chineseMedTechnology;
    }

    @Length(min=0, max=80, message="dialectical_nursing长度必须介于 0 和 80 之间")
    public String getDialecticalNursing() {
        return dialecticalNursing;
    }

    public void setDialecticalNursing(String dialecticalNursing) {
        this.dialecticalNursing = dialecticalNursing;
    }

    @Length(min=0, max=50, message="医保住院号长度必须介于 0 和 50 之间")
    public String getYbInp() {
        return ybInp;
    }

    public void setYbInp(String ybInp) {
        this.ybInp = ybInp;
    }

    @Length(min=0, max=80, message="nhzz_type长度必须介于 0 和 80 之间")
    public String getNhzzType() {
        return nhzzType;
    }

    public void setNhzzType(String nhzzType) {
        this.nhzzType = nhzzType;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }
}