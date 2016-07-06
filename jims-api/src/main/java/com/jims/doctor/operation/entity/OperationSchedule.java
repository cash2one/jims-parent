
package com.jims.doctor.operation.entity;


import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateDeSerializer;
import com.jims.common.utils.CustomDateSerializer;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 手术安排Entity
 * @author pq
 * @version 2016-05-12
 */
public class OperationSchedule extends DataEntity<OperationSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人标识号
	private String visitId;		// 病人本次住院标识
	private String clinicId;		// 就诊ID
	private String orgId;		// 组织机构
	private Integer scheduleId;		// 手术安排标识
	private String deptStayed;		// 病人所在科室
	private Integer bedNo;		// 病人所在床号
	private Date scheduledDateTime;		// 手术日期及时间
	private String operatingRoom;		// 手术室
	private String operatingRoomNo;		// 手术间
	private Integer sequence;		// 台次
	private String diagBeforeOperation;		// 术前主要诊断
	private String patientCondition;		// 病情说明
	private String operationScale;		// 手术等级
	private Integer isolationIndicator;		// 隔离标志
	private String operatingDept;		// 手术科室
	private String surgeon;		// 手术者
	private String firstAssistant;		// 第一手术助手
	private String secondAssistant;		// 第二手术助手
	private String thirdAssistant;		// 第三手术助手
	private String fourthAssistant;		// 第四手术助手
	private String anesthesiaMethod;		// 麻醉方法
	private String anesthesiaDoctor;		// 麻醉医师
	private String anesthesiaAssistant;		// 麻醉助手
	private String bloodTranDoctor;		// 输血者
	private String firstOperationNurse;		// 第一台上护士
	private String secondOperationNurse;		// 第二台上护士
	private String firstSupplyNurse;		// 第一供应护士
	private String secondSupplyNurse;		// 第二供应护士
	private String notesOnOperation;		// 备注
	private String enteredBy;		// 申请日期及时间
	private Date reqDateTime;		// 手术室确认标志
	private Integer ackIndicator;		// 录入者
	private String doctorUser;		// doctor_user
	private Integer emergencyIndicator;		// emergency_indicator
	private String state;		// state
	private String opsBodyPart;		// 手术部位
	private String provideWay;		// 供血方式
	private Integer operStatus;		// 手术状态
	private String inOrOut;		// 是否住院
	private List<ScheduledOperationName> scheduledOperationNameList;
	
	public OperationSchedule() {
		super();
	}

	public OperationSchedule(String id){
		super(id);
	}

	@Length(min=0, max=64, message="病人标识号长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=64, message="病人本次住院标识长度必须介于 0 和 64 之间")
	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	
	@Length(min=0, max=64, message="就诊ID长度必须介于 0 和 64 之间")
	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
	
	@Length(min=0, max=64, message="组织机构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	@Length(min=0, max=16, message="病人所在科室长度必须介于 0 和 16 之间")
	public String getDeptStayed() {
		return deptStayed;
	}

	public void setDeptStayed(String deptStayed) {
		this.deptStayed = deptStayed;
	}
	
	public Integer getBedNo() {
		return bedNo;
	}

	public void setBedNo(Integer bedNo) {
		this.bedNo = bedNo;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getScheduledDateTime() {
		return scheduledDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setScheduledDateTime(Date scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}
	
	@Length(min=0, max=16, message="手术室长度必须介于 0 和 16 之间")
	public String getOperatingRoom() {
		return operatingRoom;
	}

	public void setOperatingRoom(String operatingRoom) {
		this.operatingRoom = operatingRoom;
	}

	public String getOperatingRoomNo() {
		return operatingRoomNo;
	}

	public void setOperatingRoomNo(String operatingRoomNo) {
		this.operatingRoomNo = operatingRoomNo;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
	@Length(min=0, max=160, message="术前主要诊断长度必须介于 0 和 160 之间")
	public String getDiagBeforeOperation() {
		return diagBeforeOperation;
	}

	public void setDiagBeforeOperation(String diagBeforeOperation) {
		this.diagBeforeOperation = diagBeforeOperation;
	}
	
	@Length(min=0, max=80, message="病情说明长度必须介于 0 和 80 之间")
	public String getPatientCondition() {
		return patientCondition;
	}

	public void setPatientCondition(String patientCondition) {
		this.patientCondition = patientCondition;
	}
	
	@Length(min=0, max=4, message="手术等级长度必须介于 0 和 4 之间")
	public String getOperationScale() {
		return operationScale;
	}

	public void setOperationScale(String operationScale) {
		this.operationScale = operationScale;
	}
	
	public Integer getIsolationIndicator() {
		return isolationIndicator;
	}

	public void setIsolationIndicator(Integer isolationIndicator) {
		this.isolationIndicator = isolationIndicator;
	}
	
	@Length(min=0, max=64, message="手术科室长度必须介于 0 和 64 之间")
	public String getOperatingDept() {
		return operatingDept;
	}

	public void setOperatingDept(String operatingDept) {
		this.operatingDept = operatingDept;
	}
	
	@Length(min=0, max=64, message="手术者长度必须介于 0 和 64 之间")
	public String getSurgeon() {
		return surgeon;
	}

	public void setSurgeon(String surgeon) {
		this.surgeon = surgeon;
	}
	
	@Length(min=0, max=64, message="第一手术助手长度必须介于 0 和 64 之间")
	public String getFirstAssistant() {
		return firstAssistant;
	}

	public void setFirstAssistant(String firstAssistant) {
		this.firstAssistant = firstAssistant;
	}
	
	@Length(min=0, max=64, message="第二手术助手长度必须介于 0 和 64 之间")
	public String getSecondAssistant() {
		return secondAssistant;
	}

	public void setSecondAssistant(String secondAssistant) {
		this.secondAssistant = secondAssistant;
	}
	
	@Length(min=0, max=64, message="第三手术助手长度必须介于 0 和 64 之间")
	public String getThirdAssistant() {
		return thirdAssistant;
	}

	public void setThirdAssistant(String thirdAssistant) {
		this.thirdAssistant = thirdAssistant;
	}
	
	@Length(min=0, max=64, message="第四手术助手长度必须介于 0 和 64 之间")
	public String getFourthAssistant() {
		return fourthAssistant;
	}

	public void setFourthAssistant(String fourthAssistant) {
		this.fourthAssistant = fourthAssistant;
	}
	
	@Length(min=0, max=32, message="麻醉方法长度必须介于 0 和 32 之间")
	public String getAnesthesiaMethod() {
		return anesthesiaMethod;
	}

	public void setAnesthesiaMethod(String anesthesiaMethod) {
		this.anesthesiaMethod = anesthesiaMethod;
	}
	
	@Length(min=0, max=64, message="麻醉医师长度必须介于 0 和 64 之间")
	public String getAnesthesiaDoctor() {
		return anesthesiaDoctor;
	}

	public void setAnesthesiaDoctor(String anesthesiaDoctor) {
		this.anesthesiaDoctor = anesthesiaDoctor;
	}
	
	@Length(min=0, max=64, message="麻醉助手长度必须介于 0 和 64 之间")
	public String getAnesthesiaAssistant() {
		return anesthesiaAssistant;
	}

	public void setAnesthesiaAssistant(String anesthesiaAssistant) {
		this.anesthesiaAssistant = anesthesiaAssistant;
	}
	
	@Length(min=0, max=64, message="输血者长度必须介于 0 和 64 之间")
	public String getBloodTranDoctor() {
		return bloodTranDoctor;
	}

	public void setBloodTranDoctor(String bloodTranDoctor) {
		this.bloodTranDoctor = bloodTranDoctor;
	}
	
	@Length(min=0, max=64, message="第一台上护士长度必须介于 0 和 64 之间")
	public String getFirstOperationNurse() {
		return firstOperationNurse;
	}

	public void setFirstOperationNurse(String firstOperationNurse) {
		this.firstOperationNurse = firstOperationNurse;
	}
	
	@Length(min=0, max=64, message="第二台上护士长度必须介于 0 和 64 之间")
	public String getSecondOperationNurse() {
		return secondOperationNurse;
	}

	public void setSecondOperationNurse(String secondOperationNurse) {
		this.secondOperationNurse = secondOperationNurse;
	}
	
	@Length(min=0, max=64, message="第一供应护士长度必须介于 0 和 64 之间")
	public String getFirstSupplyNurse() {
		return firstSupplyNurse;
	}

	public void setFirstSupplyNurse(String firstSupplyNurse) {
		this.firstSupplyNurse = firstSupplyNurse;
	}
	
	@Length(min=0, max=64, message="第二供应护士长度必须介于 0 和 64 之间")
	public String getSecondSupplyNurse() {
		return secondSupplyNurse;
	}

	public void setSecondSupplyNurse(String secondSupplyNurse) {
		this.secondSupplyNurse = secondSupplyNurse;
	}
	
	@Length(min=0, max=1000, message="备注长度必须介于 0 和 1000 之间")
	public String getNotesOnOperation() {
		return notesOnOperation;
	}

	public void setNotesOnOperation(String notesOnOperation) {
		this.notesOnOperation = notesOnOperation;
	}
	
	@Length(min=0, max=40, message="申请日期及时间长度必须介于 0 和 40 之间")
	public String getEnteredBy() {
		return enteredBy;
	}

	public void setEnteredBy(String enteredBy) {
		this.enteredBy = enteredBy;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getReqDateTime() {
		return reqDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setReqDateTime(Date reqDateTime) {
		this.reqDateTime = reqDateTime;
	}
	
	public Integer getAckIndicator() {
		return ackIndicator;
	}

	public void setAckIndicator(Integer ackIndicator) {
		this.ackIndicator = ackIndicator;
	}
	
	@Length(min=0, max=64, message="doctor_user长度必须介于 0 和 64 之间")
	public String getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(String doctorUser) {
		this.doctorUser = doctorUser;
	}
	
	public Integer getEmergencyIndicator() {
		return emergencyIndicator;
	}

	public void setEmergencyIndicator(Integer emergencyIndicator) {
		this.emergencyIndicator = emergencyIndicator;
	}
	
	@Length(min=0, max=2, message="state长度必须介于 0 和 2 之间")
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	@Length(min=0, max=256, message="手术部位长度必须介于 0 和 256 之间")
	public String getOpsBodyPart() {
		return opsBodyPart;
	}

	public void setOpsBodyPart(String opsBodyPart) {
		this.opsBodyPart = opsBodyPart;
	}
	
	@Length(min=0, max=40, message="供血方式长度必须介于 0 和 40 之间")
	public String getProvideWay() {
		return provideWay;
	}

	public void setProvideWay(String provideWay) {
		this.provideWay = provideWay;
	}
	
	public Integer getOperStatus() {
		return operStatus;
	}

	public void setOperStatus(Integer operStatus) {
		this.operStatus = operStatus;
	}
	
	@Length(min=0, max=4, message="是否住院长度必须介于 0 和 4 之间")
	public String getInOrOut() {
		return inOrOut;
	}

	public void setInOrOut(String inOrOut) {
		this.inOrOut = inOrOut;
	}

	public List<ScheduledOperationName> getScheduledOperationNameList() {
		return scheduledOperationNameList;
	}

	public void setScheduledOperationNameList(List<ScheduledOperationName> scheduledOperationNameList) {
		this.scheduledOperationNameList = scheduledOperationNameList;
	}
}