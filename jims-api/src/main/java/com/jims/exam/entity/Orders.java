
package com.jims.exam.entity;

import com.jims.clinic.entity.OrdersCosts;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import com.jims.common.utils.CustomDateDeSerializer;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;



import java.util.Date;
import java.util.List;


/**
 * 住院医嘱Entity
 * @author zhangpeng
 * @version 2016-05-09
 */
public class Orders extends DataEntity<Orders> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人标识号
	private String visitId;		// 病人本次住院标识
	private String orgId;    //组织机构id
	private String clinicId;		//处方、检查检验等申请表id
	private int orderNo;		// 医嘱序号
	private int orderSubNo;		// 医嘱子序号
	private String repeatIndicator;		// 长期医嘱标志
	private String orderClass;		// 医嘱类别
	private String orderText;		// 医嘱正文
	private String orderCode;		// 医嘱代码
	private Double dosage;		// 药品一次使用剂量
	private String dosageUnits;		// 剂量单位
	private String administration;		// 给药途径和方法
	private Integer duration;		// 持续时间
	private Date startDateTime;		// 起始日期及时间
	private Date stopDateTime;		// 停止日期及时间
	private String frequency;		// 执行频率描述
	private Integer freqCounter;		// 频率次数
	private Integer freqInterval;		// 频率间隔
	private String freqIntervalUnit;		// 频率间隔单位
	private String freqDetail;		// 执行时间详细描述
	private String performSchedule;		// 护士执行时间
	private String performResult;		// 执行结果
	private String orderingDept;		// 开医嘱科室
	private String doctor;		// 开医嘱医生
	private String stopDoctor;		// 停医嘱医生
	private String nurse;		// 开医嘱校对护士
	private String stopNurse;		// 停医嘱校对护士
	private Date enterDateTime;		// 开医嘱录入日期及时间
	private Date stopOrderDateTime;		// 停医嘱录入日期及时间
	private String orderStatus;		// 医嘱状态
	private Integer drugBillingAttr;		// 药品计价属性
	private Integer billingAttr;		// 计价属性
	private Date lastPerformDateTime;		// 最后一次执行日期及时间
	private Date time;		// 摆药时，将摆药的截止日期(自动填入)
	private Date lastAcctingDateTime;		// 最后一次计价日期及时间
	private Integer currentPrescNo;		// 对应处方号
	private Long doctorUser;		// 医生代码
	private Date verifyDataTime;		// 校对时间
	private Long orderPrintIndicator;		// 医嘱本打印标志
	private Date processionDateTime;		// 转抄时间
	private Integer processionNurse;		// 转抄护士
	private Date stopProcessionDateTime;		// 停止医嘱转抄时间
	private Integer stopProcessionNurse;		// 停止医嘱转抄护士
	private Date cancelDateTime;		// 作废时间
	private String cancelDoctor;		// 作废医生
	private Integer degree;		// ?????
	private String appNo;		// 检验申请号
	private Integer isAdjust;		// 是否需要手工调整执行单
	private Date conversionDateTime;		// 执行单生成日期
	private String continueOrderNo;		// 续静滴途径名
	private String stopFlag;		// 停止医嘱标志
	private String adaptSymptomIndicate;		// 适应症标志
	private String dutyDoctor;		// 责任医师
	private String durationUnits;//持续时间单位
   private String processingDateTime;//处理日期及时间
	private String processingNurse;//处理护士
	private String stopProcessingDateTime;
	private String expand1;//标本
	private String expand2;//试管
	private String expand3;//科室
	private String expand4;//频次
    private String execOperator;//执行操作护士
	private Date execDateTime;//护士执行时间

	/*护士端查询*/
    private String name;
	private Integer bedNo;
	private String wardCode;

	public String getStopProcessingNurse() {
		return stopProcessingNurse;
	}

	public void setStopProcessingNurse(String stopProcessingNurse) {
		this.stopProcessingNurse = stopProcessingNurse;
	}

	private String expand5;//医嘱属性
	private List<OrdersCosts> ordersCostses;//医嘱收费明细
	private String stopProcessingNurse;

	public Orders() {
		super();
	}

	public Orders(String id){
		super(id);
	}


	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
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

	public String getExecOperator() {
		return execOperator;
	}

	public void setExecOperator(String execOperator) {
		this.execOperator = execOperator;
	}

	public Date getExecDateTime() {
		return execDateTime;
	}

	public void setExecDateTime(Date execDateTime) {
		this.execDateTime = execDateTime;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public String getProcessingDateTime() {
		return processingDateTime;
	}

	public void setProcessingDateTime(String processingDateTime) {
		this.processingDateTime = processingDateTime;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public int getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(int orderSubNo) {
		this.orderSubNo = orderSubNo;
	}

	@Length(min=0, max=1, message="长期医嘱标志长度必须介于 0 和 1 之间")
	public String getRepeatIndicator() {
		return repeatIndicator;
	}

	public void setRepeatIndicator(String repeatIndicator) {
		this.repeatIndicator = repeatIndicator;
	}
	
	@Length(min=0, max=1, message="医嘱类别长度必须介于 0 和 1 之间")
	public String getOrderClass() {
		return orderClass;
	}

	public void setOrderClass(String orderClass) {
		this.orderClass = orderClass;
	}
	
	@Length(min=0, max=200, message="医嘱正文长度必须介于 0 和 200 之间")
	public String getOrderText() {
		return orderText;
	}

	public void setOrderText(String orderText) {
		this.orderText = orderText;
	}
	
	@Length(min=0, max=64, message="医嘱代码长度必须介于 0 和 64 之间")
	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	
	public Double getDosage() {
		return dosage;
	}

	public void setDosage(Double dosage) {
		this.dosage = dosage;
	}
	
	@Length(min=0, max=8, message="剂量单位长度必须介于 0 和 8 之间")
	public String getDosageUnits() {
		return dosageUnits;
	}

	public void setDosageUnits(String dosageUnits) {
		this.dosageUnits = dosageUnits;
	}
	
	@Length(min=0, max=64, message="给药途径和方法长度必须介于 0 和 64 之间")
	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}
	
	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStartDateTime() {
		return startDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStopDateTime() {
		return stopDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setStopDateTime(Date stopDateTime) {
		this.stopDateTime = stopDateTime;
	}
	
	@Length(min=0, max=16, message="执行频率描述长度必须介于 0 和 16 之间")
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public Integer getFreqCounter() {
		return freqCounter;
	}

	public void setFreqCounter(Integer freqCounter) {
		this.freqCounter = freqCounter;
	}
	
	public Integer getFreqInterval() {
		return freqInterval;
	}

	public void setFreqInterval(Integer freqInterval) {
		this.freqInterval = freqInterval;
	}
	
	@Length(min=0, max=4, message="频率间隔单位长度必须介于 0 和 4 之间")
	public String getFreqIntervalUnit() {
		return freqIntervalUnit;
	}

	public void setFreqIntervalUnit(String freqIntervalUnit) {
		this.freqIntervalUnit = freqIntervalUnit;
	}
	
	@Length(min=0, max=80, message="执行时间详细描述长度必须介于 0 和 80 之间")
	public String getFreqDetail() {
		return freqDetail;
	}

	public void setFreqDetail(String freqDetail) {
		this.freqDetail = freqDetail;
	}
	
	@Length(min=0, max=16, message="护士执行时间长度必须介于 0 和 16 之间")
	public String getPerformSchedule() {
		return performSchedule;
	}

	public void setPerformSchedule(String performSchedule) {
		this.performSchedule = performSchedule;
	}
	
	@Length(min=0, max=8, message="执行结果长度必须介于 0 和 8 之间")
	public String getPerformResult() {
		return performResult;
	}

	public void setPerformResult(String performResult) {
		this.performResult = performResult;
	}
	
	@Length(min=0, max=8, message="开医嘱科室长度必须介于 0 和 8 之间")
	public String getOrderingDept() {
		return orderingDept;
	}

	public void setOrderingDept(String orderingDept) {
		this.orderingDept = orderingDept;
	}
	
	@Length(min=0, max=8, message="开医嘱医生长度必须介于 0 和 8 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	@Length(min=0, max=8, message="停医嘱医生长度必须介于 0 和 8 之间")
	public String getStopDoctor() {
		return stopDoctor;
	}

	public void setStopDoctor(String stopDoctor) {
		this.stopDoctor = stopDoctor;
	}
	
	@Length(min=0, max=8, message="开医嘱校对护士长度必须介于 0 和 8 之间")
	public String getNurse() {
		return nurse;
	}

	public void setNurse(String nurse) {
		this.nurse = nurse;
	}
	
	@Length(min=0, max=8, message="停医嘱校对护士长度必须介于 0 和 8 之间")
	public String getStopNurse() {
		return stopNurse;
	}

	public void setStopNurse(String stopNurse) {
		this.stopNurse = stopNurse;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEnterDateTime() {
		return enterDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setEnterDateTime(Date enterDateTime) {
		this.enterDateTime = enterDateTime;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStopOrderDateTime() {
		return stopOrderDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setStopOrderDateTime(Date stopOrderDateTime) {
		this.stopOrderDateTime = stopOrderDateTime;
	}
	
	@Length(min=0, max=1, message="医嘱状态长度必须介于 0 和 1 之间")
	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public Integer getDrugBillingAttr() {
		return drugBillingAttr;
	}

	public void setDrugBillingAttr(Integer drugBillingAttr) {
		this.drugBillingAttr = drugBillingAttr;
	}
	
	public Integer getBillingAttr() {
		return billingAttr;
	}

	public void setBillingAttr(Integer billingAttr) {
		this.billingAttr = billingAttr;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastPerformDateTime() {
		return lastPerformDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setLastPerformDateTime(Date lastPerformDateTime) {
		this.lastPerformDateTime = lastPerformDateTime;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getTime() {
		return time;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setTime(Date time) {
		this.time = time;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastAcctingDateTime() {
		return lastAcctingDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setLastAcctingDateTime(Date lastAcctingDateTime) {
		this.lastAcctingDateTime = lastAcctingDateTime;
	}
	
	public Integer getCurrentPrescNo() {
		return currentPrescNo;
	}

	public void setCurrentPrescNo(Integer currentPrescNo) {
		this.currentPrescNo = currentPrescNo;
	}
	
	public Long getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(Long doctorUser) {
		this.doctorUser = doctorUser;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getVerifyDataTime() {
		return verifyDataTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setVerifyDataTime(Date verifyDataTime) {
		this.verifyDataTime = verifyDataTime;
	}
	
	public Long getOrderPrintIndicator() {
		return orderPrintIndicator;
	}

	public void setOrderPrintIndicator(Long orderPrintIndicator) {
		this.orderPrintIndicator = orderPrintIndicator;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getProcessionDateTime() {
		return processionDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setProcessionDateTime(Date processionDateTime) {
		this.processionDateTime = processionDateTime;
	}
	
	public Integer getProcessionNurse() {
		return processionNurse;
	}

	public void setProcessionNurse(Integer processionNurse) {
		this.processionNurse = processionNurse;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStopProcessionDateTime() {
		return stopProcessionDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setStopProcessionDateTime(Date stopProcessionDateTime) {
		this.stopProcessionDateTime = stopProcessionDateTime;
	}
	
	public Integer getStopProcessionNurse() {
		return stopProcessionNurse;
	}

	public void setStopProcessionNurse(Integer stopProcessionNurse) {
		this.stopProcessionNurse = stopProcessionNurse;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCancelDateTime() {
		return cancelDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setCancelDateTime(Date cancelDateTime) {
		this.cancelDateTime = cancelDateTime;
	}
	
	@Length(min=0, max=8, message="作废医生长度必须介于 0 和 8 之间")
	public String getCancelDoctor() {
		return cancelDoctor;
	}

	public void setCancelDoctor(String cancelDoctor) {
		this.cancelDoctor = cancelDoctor;
	}
	
	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	
	@Length(min=0, max=20, message="检验申请号长度必须介于 0 和 20 之间")
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	
	public Integer getIsAdjust() {
		return isAdjust;
	}

	public void setIsAdjust(Integer isAdjust) {
		this.isAdjust = isAdjust;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getConversionDateTime() {
		return conversionDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setConversionDateTime(Date conversionDateTime) {
		this.conversionDateTime = conversionDateTime;
	}
	
	@Length(min=0, max=20, message="续静滴途径名长度必须介于 0 和 20 之间")
	public String getContinueOrderNo() {
		return continueOrderNo;
	}

	public void setContinueOrderNo(String continueOrderNo) {
		this.continueOrderNo = continueOrderNo;
	}
	
	@Length(min=0, max=21, message="停止医嘱标志长度必须介于 0 和 21 之间")
	public String getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(String stopFlag) {
		this.stopFlag = stopFlag;
	}
	
	@Length(min=0, max=1, message="适应症标志长度必须介于 0 和 1 之间")
	public String getAdaptSymptomIndicate() {
		return adaptSymptomIndicate;
	}

	public void setAdaptSymptomIndicate(String adaptSymptomIndicate) {
		this.adaptSymptomIndicate = adaptSymptomIndicate;
	}
	
	@Length(min=0, max=20, message="责任医师长度必须介于 0 和 20 之间")
	public String getDutyDoctor() {
		return dutyDoctor;
	}

	public void setDutyDoctor(String dutyDoctor) {
		this.dutyDoctor = dutyDoctor;
	}


	public String getDurationUnits() {
		return durationUnits;
	}

	public void setDurationUnits(String durationUnits) {
		this.durationUnits = durationUnits;
	}

	public String getProcessingNurse() {
		return processingNurse;
	}

	public void setProcessingNurse(String processingNurse) {
		this.processingNurse = processingNurse;
	}

	public String getStopProcessingDateTime() {
		return stopProcessingDateTime;
	}

	public void setStopProcessingDateTime(String stopProcessingDateTime) {
		this.stopProcessingDateTime = stopProcessingDateTime;
	}

	public String getExpand1() {
		return expand1;
	}

	public void setExpand1(String expand1) {
		this.expand1 = expand1;
	}

	public String getExpand2() {
		return expand2;
	}

	public void setExpand2(String expand2) {
		this.expand2 = expand2;
	}

	public String getExpand3() {
		return expand3;
	}

	public void setExpand3(String expand3) {
		this.expand3 = expand3;
	}

	public String getExpand4() {
		return expand4;
	}

	public void setExpand4(String expand4) {
		this.expand4 = expand4;
	}

	public String getExpand5() {
		return expand5;
	}

	public void setExpand5(String expand5) {
		this.expand5 = expand5;
	}

	public List<OrdersCosts> getOrdersCostses() {
		return ordersCostses;
	}

	public void setOrdersCostses(List<OrdersCosts> ordersCostses) {
		this.ordersCostses = ordersCostses;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBedNo() {
		return bedNo;
	}

	public void setBedNo(Integer bedNo) {
		this.bedNo = bedNo;
	}


	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
}