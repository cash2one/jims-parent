/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.exam.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 检查主记录Entity
 * @author zhaoning
 * @version 2016-07-05
 */
public class ExamMaster extends DataEntity<ExamMaster> {
	
	private static final long serialVersionUID = 1L;
	private String examNo;		// 申请序号
	private String localIdClass;		// 检查号类别
	private String patientLocalId;		// 检查标识号
	private String patientId;		// 病人标识号
	private Integer visitId;		// 病人本次住院标识
	private String name;		// 姓名
	private String sex;		// 性别
	private Date dateOfBirth;		// 出生日期
	private String examClass;		// 检查类别
	private String examSubClass;		// 检查子类
	private Date spmRecvedDate;		// 标本收到日期及时间
	private String clinSymp;		// 临床症状
	private String physSign;		// 体征
	private String relevantLabTest;		// 相关化验结果
	private String relevantDiag;		// 其他诊断
	private String clinDiag;		// 临床诊断
	private String examMode;		// 检查方式
	private String examGroup;		// 检查分组
	private String device;		// 使用仪器
	private String performedBy;		// 执行科室
	private String patientSource;		// 病人来源
	private String facility;		// 外来医疗单位名称
	private Date reqDateTime;		// 申请日期及时间
	private String reqDept;		// 申请科室
	private String reqPhysician;		// 申请医生
	private String reqMemo;		// 申请备注
	private Date scheduledDateTime;		// 预约日期及时间
	private String notice;		// 注意事项
	private Date examDateTime;		// 检查日期及时间
	private Date reportDateTime;		// 报告日期及时间
	private String technician;		// 操作者
	private String reporter;		// 报告者
	private String resultStatus;		// 检查结果状态
	private Double costs;		// 费用
	private Double charges;		// 应收费用
	private Integer chargeIndicator;		// 计价标志
	private String chargeType;		// 病人费用类别
	private String identity;		// 身份
	private String rptstatus;		// rptstatus
	private String printStatus;		// print_status
	private String examSubdept;		// exam_subdept
	private String confirmDoct;		// confirm_doct
	private String studyUid;		// study_uid
	private String photoStatus;		// photo_status
	private Date confirmDateTime;		// confirm_date_time
	private String hpValue;		// hp_value
	private String diagHostname;		// diag_hostname
	private String reportLockStatus;		// report_lock_status
	private Integer queueNo;		// queue_no
	private String equipmentNo;		// equipment_no
	private String timeInterval;		// time_interval
	private Integer visitNo;		// visit_no
	private String register;		// register
	private Integer renIndicator;		// ren_indicator
	private String renExamNo;		// ren_exam_no
	private String cnsltNo;		// cnslt_no
	private String examReason;		// exam_reason
	private Integer priorityIndicator;		// priority_indicator
	private Integer specialIndicator;		// special_indicator
	private String shareExamNo;		// share_exam_no
	private String pauseIndicator;		// pause_indicator
	private String birthPlace;		// birth_place
	private String phoneNumber;		// phone_number
	private String zipCode;		// zip_code
	private String mailingAddress;		// mailing_address
	private String namePhonetic;		// 审核者
	private String doctorUser;		// 操作技师
	private String repIndicator;		// rep_indicator
	private String pacsFlag;		// 图像来源
	private String pacsPaths;		// pacs_paths
	private String rcptNo;		// 收据号
	
	public ExamMaster() {
		super();
	}

	public ExamMaster(String id){
		super(id);
	}

	@Length(min=0, max=10, message="申请序号长度必须介于 0 和 10 之间")
	public String getExamNo() {
		return examNo;
	}

	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	
	@Length(min=0, max=64, message="检查号类别长度必须介于 0 和 64 之间")
	public String getLocalIdClass() {
		return localIdClass;
	}

	public void setLocalIdClass(String localIdClass) {
		this.localIdClass = localIdClass;
	}
	
	@Length(min=0, max=64, message="检查标识号长度必须介于 0 和 64 之间")
	public String getPatientLocalId() {
		return patientLocalId;
	}

	public void setPatientLocalId(String patientLocalId) {
		this.patientLocalId = patientLocalId;
	}
	
	@Length(min=0, max=64, message="病人标识号长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	
	@Length(min=0, max=20, message="姓名长度必须介于 0 和 20 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="性别长度必须介于 0 和 64 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Length(min=0, max=64, message="检查类别长度必须介于 0 和 64 之间")
	public String getExamClass() {
		return examClass;
	}

	public void setExamClass(String examClass) {
		this.examClass = examClass;
	}
	
	@Length(min=0, max=64, message="检查子类长度必须介于 0 和 64 之间")
	public String getExamSubClass() {
		return examSubClass;
	}

	public void setExamSubClass(String examSubClass) {
		this.examSubClass = examSubClass;
	}
	

	public Date getSpmRecvedDate() {
		return spmRecvedDate;
	}

	public void setSpmRecvedDate(Date spmRecvedDate) {
		this.spmRecvedDate = spmRecvedDate;
	}
	
	@Length(min=0, max=400, message="临床症状长度必须介于 0 和 400 之间")
	public String getClinSymp() {
		return clinSymp;
	}

	public void setClinSymp(String clinSymp) {
		this.clinSymp = clinSymp;
	}
	
	@Length(min=0, max=400, message="体征长度必须介于 0 和 400 之间")
	public String getPhysSign() {
		return physSign;
	}

	public void setPhysSign(String physSign) {
		this.physSign = physSign;
	}
	
	@Length(min=0, max=200, message="相关化验结果长度必须介于 0 和 200 之间")
	public String getRelevantLabTest() {
		return relevantLabTest;
	}

	public void setRelevantLabTest(String relevantLabTest) {
		this.relevantLabTest = relevantLabTest;
	}
	
	@Length(min=0, max=400, message="其他诊断长度必须介于 0 和 400 之间")
	public String getRelevantDiag() {
		return relevantDiag;
	}

	public void setRelevantDiag(String relevantDiag) {
		this.relevantDiag = relevantDiag;
	}
	
	@Length(min=0, max=100, message="临床诊断长度必须介于 0 和 100 之间")
	public String getClinDiag() {
		return clinDiag;
	}

	public void setClinDiag(String clinDiag) {
		this.clinDiag = clinDiag;
	}
	
	@Length(min=0, max=1, message="检查方式长度必须介于 0 和 1 之间")
	public String getExamMode() {
		return examMode;
	}

	public void setExamMode(String examMode) {
		this.examMode = examMode;
	}
	
	@Length(min=0, max=16, message="检查分组长度必须介于 0 和 16 之间")
	public String getExamGroup() {
		return examGroup;
	}

	public void setExamGroup(String examGroup) {
		this.examGroup = examGroup;
	}
	
	@Length(min=0, max=100, message="使用仪器长度必须介于 0 和 100 之间")
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
	
	@Length(min=0, max=64, message="执行科室长度必须介于 0 和 64 之间")
	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}
	
	@Length(min=0, max=1, message="病人来源长度必须介于 0 和 1 之间")
	public String getPatientSource() {
		return patientSource;
	}

	public void setPatientSource(String patientSource) {
		this.patientSource = patientSource;
	}
	
	@Length(min=0, max=20, message="外来医疗单位名称长度必须介于 0 和 20 之间")
	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public Date getReqDateTime() {
		return reqDateTime;
	}

	public void setReqDateTime(Date reqDateTime) {
		this.reqDateTime = reqDateTime;
	}
	
	@Length(min=0, max=64, message="申请科室长度必须介于 0 和 64 之间")
	public String getReqDept() {
		return reqDept;
	}

	public void setReqDept(String reqDept) {
		this.reqDept = reqDept;
	}
	
	@Length(min=0, max=20, message="申请医生长度必须介于 0 和 20 之间")
	public String getReqPhysician() {
		return reqPhysician;
	}

	public void setReqPhysician(String reqPhysician) {
		this.reqPhysician = reqPhysician;
	}
	
	@Length(min=0, max=60, message="申请备注长度必须介于 0 和 60 之间")
	public String getReqMemo() {
		return reqMemo;
	}

	public void setReqMemo(String reqMemo) {
		this.reqMemo = reqMemo;
	}
	

	public Date getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(Date scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}
	
	@Length(min=0, max=400, message="注意事项长度必须介于 0 和 400 之间")
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
	

	public Date getExamDateTime() {
		return examDateTime;
	}

	public void setExamDateTime(Date examDateTime) {
		this.examDateTime = examDateTime;
	}
	

	public Date getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(Date reportDateTime) {
		this.reportDateTime = reportDateTime;
	}
	
	@Length(min=0, max=100, message="操作者长度必须介于 0 和 100 之间")
	public String getTechnician() {
		return technician;
	}

	public void setTechnician(String technician) {
		this.technician = technician;
	}
	
	@Length(min=0, max=100, message="报告者长度必须介于 0 和 100 之间")
	public String getReporter() {
		return reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	
	@Length(min=0, max=1, message="检查结果状态长度必须介于 0 和 1 之间")
	public String getResultStatus() {
		return resultStatus;
	}

	public void setResultStatus(String resultStatus) {
		this.resultStatus = resultStatus;
	}
	
	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}
	
	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}
	
	public Integer getChargeIndicator() {
		return chargeIndicator;
	}

	public void setChargeIndicator(Integer chargeIndicator) {
		this.chargeIndicator = chargeIndicator;
	}
	
	@Length(min=0, max=64, message="病人费用类别长度必须介于 0 和 64 之间")
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	@Length(min=0, max=64, message="身份长度必须介于 0 和 64 之间")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	@Length(min=0, max=100, message="rptstatus长度必须介于 0 和 100 之间")
	public String getRptstatus() {
		return rptstatus;
	}

	public void setRptstatus(String rptstatus) {
		this.rptstatus = rptstatus;
	}
	
	@Length(min=0, max=50, message="print_status长度必须介于 0 和 50 之间")
	public String getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}
	
	@Length(min=0, max=64, message="exam_subdept长度必须介于 0 和 64 之间")
	public String getExamSubdept() {
		return examSubdept;
	}

	public void setExamSubdept(String examSubdept) {
		this.examSubdept = examSubdept;
	}
	
	@Length(min=0, max=8, message="confirm_doct长度必须介于 0 和 8 之间")
	public String getConfirmDoct() {
		return confirmDoct;
	}

	public void setConfirmDoct(String confirmDoct) {
		this.confirmDoct = confirmDoct;
	}
	
	@Length(min=0, max=128, message="study_uid长度必须介于 0 和 128 之间")
	public String getStudyUid() {
		return studyUid;
	}

	public void setStudyUid(String studyUid) {
		this.studyUid = studyUid;
	}
	
	@Length(min=0, max=50, message="photo_status长度必须介于 0 和 50 之间")
	public String getPhotoStatus() {
		return photoStatus;
	}

	public void setPhotoStatus(String photoStatus) {
		this.photoStatus = photoStatus;
	}
	

	public Date getConfirmDateTime() {
		return confirmDateTime;
	}

	public void setConfirmDateTime(Date confirmDateTime) {
		this.confirmDateTime = confirmDateTime;
	}
	
	@Length(min=0, max=10, message="hp_value长度必须介于 0 和 10 之间")
	public String getHpValue() {
		return hpValue;
	}

	public void setHpValue(String hpValue) {
		this.hpValue = hpValue;
	}
	
	@Length(min=0, max=30, message="diag_hostname长度必须介于 0 和 30 之间")
	public String getDiagHostname() {
		return diagHostname;
	}

	public void setDiagHostname(String diagHostname) {
		this.diagHostname = diagHostname;
	}
	
	@Length(min=0, max=10, message="report_lock_status长度必须介于 0 和 10 之间")
	public String getReportLockStatus() {
		return reportLockStatus;
	}

	public void setReportLockStatus(String reportLockStatus) {
		this.reportLockStatus = reportLockStatus;
	}
	
	public Integer getQueueNo() {
		return queueNo;
	}

	public void setQueueNo(Integer queueNo) {
		this.queueNo = queueNo;
	}
	
	@Length(min=0, max=8, message="equipment_no长度必须介于 0 和 8 之间")
	public String getEquipmentNo() {
		return equipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}
	
	@Length(min=0, max=10, message="time_interval长度必须介于 0 和 10 之间")
	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	public Integer getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	
	@Length(min=0, max=16, message="register长度必须介于 0 和 16 之间")
	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}
	
	public Integer getRenIndicator() {
		return renIndicator;
	}

	public void setRenIndicator(Integer renIndicator) {
		this.renIndicator = renIndicator;
	}
	
	@Length(min=0, max=10, message="ren_exam_no长度必须介于 0 和 10 之间")
	public String getRenExamNo() {
		return renExamNo;
	}

	public void setRenExamNo(String renExamNo) {
		this.renExamNo = renExamNo;
	}
	
	@Length(min=0, max=8, message="cnslt_no长度必须介于 0 和 8 之间")
	public String getCnsltNo() {
		return cnsltNo;
	}

	public void setCnsltNo(String cnsltNo) {
		this.cnsltNo = cnsltNo;
	}
	
	@Length(min=0, max=200, message="exam_reason长度必须介于 0 和 200 之间")
	public String getExamReason() {
		return examReason;
	}

	public void setExamReason(String examReason) {
		this.examReason = examReason;
	}
	
	public Integer getPriorityIndicator() {
		return priorityIndicator;
	}

	public void setPriorityIndicator(Integer priorityIndicator) {
		this.priorityIndicator = priorityIndicator;
	}
	
	public Integer getSpecialIndicator() {
		return specialIndicator;
	}

	public void setSpecialIndicator(Integer specialIndicator) {
		this.specialIndicator = specialIndicator;
	}
	
	@Length(min=0, max=10, message="share_exam_no长度必须介于 0 和 10 之间")
	public String getShareExamNo() {
		return shareExamNo;
	}

	public void setShareExamNo(String shareExamNo) {
		this.shareExamNo = shareExamNo;
	}
	
	@Length(min=0, max=10, message="pause_indicator长度必须介于 0 和 10 之间")
	public String getPauseIndicator() {
		return pauseIndicator;
	}

	public void setPauseIndicator(String pauseIndicator) {
		this.pauseIndicator = pauseIndicator;
	}
	
	@Length(min=0, max=6, message="birth_place长度必须介于 0 和 6 之间")
	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	@Length(min=0, max=16, message="phone_number长度必须介于 0 和 16 之间")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Length(min=0, max=6, message="zip_code长度必须介于 0 和 6 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=40, message="mailing_address长度必须介于 0 和 40 之间")
	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
	@Length(min=0, max=50, message="审核者长度必须介于 0 和 50 之间")
	public String getNamePhonetic() {
		return namePhonetic;
	}

	public void setNamePhonetic(String namePhonetic) {
		this.namePhonetic = namePhonetic;
	}
	
	@Length(min=0, max=64, message="操作技师长度必须介于 0 和 64 之间")
	public String getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(String doctorUser) {
		this.doctorUser = doctorUser;
	}
	
	@Length(min=0, max=4, message="rep_indicator长度必须介于 0 和 4 之间")
	public String getRepIndicator() {
		return repIndicator;
	}

	public void setRepIndicator(String repIndicator) {
		this.repIndicator = repIndicator;
	}
	
	@Length(min=0, max=2, message="图像来源长度必须介于 0 和 2 之间")
	public String getPacsFlag() {
		return pacsFlag;
	}

	public void setPacsFlag(String pacsFlag) {
		this.pacsFlag = pacsFlag;
	}
	
	@Length(min=0, max=2000, message="pacs_paths长度必须介于 0 和 2000 之间")
	public String getPacsPaths() {
		return pacsPaths;
	}

	public void setPacsPaths(String pacsPaths) {
		this.pacsPaths = pacsPaths;
	}
	
	@Length(min=0, max=20, message="收据号长度必须介于 0 和 20 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
}