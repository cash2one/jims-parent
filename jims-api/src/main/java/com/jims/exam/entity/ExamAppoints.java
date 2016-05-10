/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.exam.entity;

import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 检查预约记录Entity
 * @author zhaoning
 * @version 2016-04-25
 */
public class ExamAppoints extends DataEntity<ExamAppoints> {
	
	private static final long serialVersionUID = 1L;
	private String examNo;		// 申请序号
	private String patientId;		// 病人标识号
	private Integer visitId;		// 住院标识
	private String localIdClass;		// 检查号类别
	private String patientLocalId;		// 检查标识号
	private String name;		// 姓名
	private String namePhonetic;		// 姓名拼音
	private String sex;		// 性别
	private Date dateOfBirth;		// 出生日期
	private String birthPlace;		// 出生地
	private String identity;		// 身份
	private String chargeType;		// 费别
	private String mailingAddress;		// 通信地址
	private String zipCode;		// 邮政编码
	private String phoneNumber;		// 联系电话
	private String examClass;		// 检查类别
	private String examSubClass;		// 检查子类
	private String clinSymp;		// 临床症状
	private String physSign;		// 体征
	private String relevantLabTest;		// 相关化验结果
	private String relevantDiag;		// 其他诊断
	private String clinDiag;		// 临床诊断
	private String examMode;		// 检查方式
	private String examGroup;		// 检查分组
	private String performedBy;		// 执行科室
	private String patientSource;		// 病人来源
	private String facility;		// 外来医疗单位名称
	private Date reqDateTime;		// 申请日期及时间
	private String reqDept;		// 申请科室
	private String reqPhysician;		// 申请医生
	private String reqMemo;		// 申请备注
	private Date scheduledDate;		// 预约日期及时间
	private String notice;		// 注意事项
	private Double costs;		// 费用
	private Double charges;		// 应收费用
	private String doctorUser;		// 申请医生用户名
	private Integer workedIndicator;		// worked_indicator
	private String isread;		// isread
	private Integer emergencyIndicator;		// emergency_indicator
	private Integer billingIndicator;		// billing_indicator
	private Integer cnsltState;		// cnslt_state
	private Integer queueNo;		// queue_no
	private String device;		// device
	private String timeInterval;		// time_interval
	private String equipmentNo;		// equipment_no
	private Integer visitNo;		// visit_no
	private String register;		// register
	private Integer regPrnFlag;		// reg_prn_flag
	private String cnsltNo;		// cnslt_no
	private String examReason;		// exam_reason
	private Integer priorityIndicator;		// priority_indicator
	private Integer specialIndicator;		// special_indicator
	private String shareExamNo;		// share_exam_no
	private String pauseIndicator;		// pause_indicator
	private String dbUser;		// db_user
	private String cnsltName;		// cnslt_name
	private Integer printStatus;		// print_status
	private String wardCode;		// 护理单元
	private String rcptNo;		// 收据号
	private ExamItems examItems;

	//扩展
	private List<OutpOrdersCosts> outpOrdersCostses;

	public ExamItems getExamItems() {
		return examItems;
	}

	public void setExamItems(ExamItems examItems) {
		this.examItems = examItems;
	}

	public List<OutpOrdersCosts> getOutpOrdersCostses() {
		return outpOrdersCostses;
	}

	public void setOutpOrdersCostses(List<OutpOrdersCosts> outpOrdersCostses) {
		this.outpOrdersCostses = outpOrdersCostses;
	}


	public ExamAppoints() {
		super();
	}

	public ExamAppoints(String id){
		super(id);
	}

	@Length(min=0, max=128, message="申请序号长度必须介于 0 和 128 之间")
	public String getExamNo() {
		return examNo;
	}

	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	
	@Length(min=0, max=128, message="病人标识号长度必须介于 0 和 128 之间")
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
	
	@Length(min=0, max=128, message="检查号类别长度必须介于 0 和 128 之间")
	public String getLocalIdClass() {
		return localIdClass;
	}

	public void setLocalIdClass(String localIdClass) {
		this.localIdClass = localIdClass;
	}
	
	@Length(min=0, max=128, message="检查标识号长度必须介于 0 和 128 之间")
	public String getPatientLocalId() {
		return patientLocalId;
	}

	public void setPatientLocalId(String patientLocalId) {
		this.patientLocalId = patientLocalId;
	}
	
	@Length(min=0, max=40, message="姓名长度必须介于 0 和 40 之间")
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
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Length(min=0, max=128, message="出生地长度必须介于 0 和 128 之间")
	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
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
	
	@Length(min=0, max=200, message="通信地址长度必须介于 0 和 200 之间")
	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
	@Length(min=0, max=12, message="邮政编码长度必须介于 0 和 12 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=32, message="联系电话长度必须介于 0 和 32 之间")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Length(min=0, max=128, message="检查类别长度必须介于 0 和 128 之间")
	public String getExamClass() {
		return examClass;
	}

	public void setExamClass(String examClass) {
		this.examClass = examClass;
	}
	
	@Length(min=0, max=128, message="检查子类长度必须介于 0 和 128 之间")
	public String getExamSubClass() {
		return examSubClass;
	}

	public void setExamSubClass(String examSubClass) {
		this.examSubClass = examSubClass;
	}
	
	@Length(min=0, max=4000, message="临床症状长度必须介于 0 和 4000 之间")
	public String getClinSymp() {
		return clinSymp;
	}

	public void setClinSymp(String clinSymp) {
		this.clinSymp = clinSymp;
	}
	
	@Length(min=0, max=2000, message="体征长度必须介于 0 和 2000 之间")
	public String getPhysSign() {
		return physSign;
	}

	public void setPhysSign(String physSign) {
		this.physSign = physSign;
	}
	
	@Length(min=0, max=400, message="相关化验结果长度必须介于 0 和 400 之间")
	public String getRelevantLabTest() {
		return relevantLabTest;
	}

	public void setRelevantLabTest(String relevantLabTest) {
		this.relevantLabTest = relevantLabTest;
	}
	
	@Length(min=0, max=800, message="其他诊断长度必须介于 0 和 800 之间")
	public String getRelevantDiag() {
		return relevantDiag;
	}

	public void setRelevantDiag(String relevantDiag) {
		this.relevantDiag = relevantDiag;
	}
	
	@Length(min=0, max=2000, message="临床诊断长度必须介于 0 和 2000 之间")
	public String getClinDiag() {
		return clinDiag;
	}

	public void setClinDiag(String clinDiag) {
		this.clinDiag = clinDiag;
	}
	
	@Length(min=0, max=128, message="检查方式长度必须介于 0 和 128 之间")
	public String getExamMode() {
		return examMode;
	}

	public void setExamMode(String examMode) {
		this.examMode = examMode;
	}
	
	@Length(min=0, max=128, message="检查分组长度必须介于 0 和 128 之间")
	public String getExamGroup() {
		return examGroup;
	}

	public void setExamGroup(String examGroup) {
		this.examGroup = examGroup;
	}
	
	@Length(min=0, max=128, message="执行科室长度必须介于 0 和 128 之间")
	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}
	
	@Length(min=0, max=128, message="病人来源长度必须介于 0 和 128 之间")
	public String getPatientSource() {
		return patientSource;
	}

	public void setPatientSource(String patientSource) {
		this.patientSource = patientSource;
	}
	
	@Length(min=0, max=40, message="外来医疗单位名称长度必须介于 0 和 40 之间")
	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getReqDateTime() {
		return reqDateTime;
	}

	public void setReqDateTime(Date reqDateTime) {
		this.reqDateTime = reqDateTime;
	}
	
	@Length(min=0, max=128, message="申请科室长度必须介于 0 和 128 之间")
	public String getReqDept() {
		return reqDept;
	}

	public void setReqDept(String reqDept) {
		this.reqDept = reqDept;
	}
	
	@Length(min=0, max=40, message="申请医生长度必须介于 0 和 40 之间")
	public String getReqPhysician() {
		return reqPhysician;
	}

	public void setReqPhysician(String reqPhysician) {
		this.reqPhysician = reqPhysician;
	}
	
	@Length(min=0, max=120, message="申请备注长度必须介于 0 和 120 之间")
	public String getReqMemo() {
		return reqMemo;
	}

	public void setReqMemo(String reqMemo) {
		this.reqMemo = reqMemo;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(Date scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	
	@Length(min=0, max=800, message="注意事项长度必须介于 0 和 800 之间")
	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
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
	
	@Length(min=0, max=128, message="申请医生用户名长度必须介于 0 和 128 之间")
	public String getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(String doctorUser) {
		this.doctorUser = doctorUser;
	}
	
	public Integer getWorkedIndicator() {
		return workedIndicator;
	}

	public void setWorkedIndicator(Integer workedIndicator) {
		this.workedIndicator = workedIndicator;
	}
	
	@Length(min=0, max=1, message="isread长度必须介于 0 和 1 之间")
	public String getIsread() {
		return isread;
	}

	public void setIsread(String isread) {
		this.isread = isread;
	}
	
	public Integer getEmergencyIndicator() {
		return emergencyIndicator;
	}

	public void setEmergencyIndicator(Integer emergencyIndicator) {
		this.emergencyIndicator = emergencyIndicator;
	}
	
	public Integer getBillingIndicator() {
		return billingIndicator;
	}

	public void setBillingIndicator(Integer billingIndicator) {
		this.billingIndicator = billingIndicator;
	}
	
	@NotNull(message="cnslt_state不能为空")
	public Integer getCnsltState() {
		return cnsltState;
	}

	public void setCnsltState(Integer cnsltState) {
		this.cnsltState = cnsltState;
	}
	
	public Integer getQueueNo() {
		return queueNo;
	}

	public void setQueueNo(Integer queueNo) {
		this.queueNo = queueNo;
	}
	
	@Length(min=0, max=80, message="device长度必须介于 0 和 80 之间")
	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
	
	@Length(min=0, max=20, message="time_interval长度必须介于 0 和 20 之间")
	public String getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(String timeInterval) {
		this.timeInterval = timeInterval;
	}
	
	@Length(min=0, max=40, message="equipment_no长度必须介于 0 和 40 之间")
	public String getEquipmentNo() {
		return equipmentNo;
	}

	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}
	
	public Integer getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	
	@Length(min=0, max=100, message="register长度必须介于 0 和 100 之间")
	public String getRegister() {
		return register;
	}

	public void setRegister(String register) {
		this.register = register;
	}
	
	public Integer getRegPrnFlag() {
		return regPrnFlag;
	}

	public void setRegPrnFlag(Integer regPrnFlag) {
		this.regPrnFlag = regPrnFlag;
	}
	
	@Length(min=0, max=20, message="cnslt_no长度必须介于 0 和 20 之间")
	public String getCnsltNo() {
		return cnsltNo;
	}

	public void setCnsltNo(String cnsltNo) {
		this.cnsltNo = cnsltNo;
	}
	
	@Length(min=0, max=400, message="exam_reason长度必须介于 0 和 400 之间")
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
	
	@Length(min=0, max=20, message="share_exam_no长度必须介于 0 和 20 之间")
	public String getShareExamNo() {
		return shareExamNo;
	}

	public void setShareExamNo(String shareExamNo) {
		this.shareExamNo = shareExamNo;
	}
	
	@Length(min=0, max=20, message="pause_indicator长度必须介于 0 和 20 之间")
	public String getPauseIndicator() {
		return pauseIndicator;
	}

	public void setPauseIndicator(String pauseIndicator) {
		this.pauseIndicator = pauseIndicator;
	}
	
	@Length(min=0, max=128, message="db_user长度必须介于 0 和 128 之间")
	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	
	@Length(min=0, max=64, message="cnslt_name长度必须介于 0 和 64 之间")
	public String getCnsltName() {
		return cnsltName;
	}

	public void setCnsltName(String cnsltName) {
		this.cnsltName = cnsltName;
	}
	
	public Integer getPrintStatus() {
		return printStatus;
	}

	public void setPrintStatus(Integer printStatus) {
		this.printStatus = printStatus;
	}
	
	@Length(min=0, max=128, message="护理单元长度必须介于 0 和 128 之间")
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	@Length(min=0, max=40, message="收据号长度必须介于 0 和 40 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
}