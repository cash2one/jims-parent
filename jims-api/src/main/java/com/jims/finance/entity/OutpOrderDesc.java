/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 开单记录Entity
 * @author zhaoning
 * @version 2016-05-26
 */
public class OutpOrderDesc extends DataEntity<OutpOrderDesc> {
	
	private static final long serialVersionUID = 1L;
	private Date visitDate;		// 就诊时间
	private Integer visitNo;		// 就诊序号
	private String patientId;		// 病人基本信息
	private Integer prescIndicator;		// 药品处方标志
	private String orderedByDept;		// 开单科室
	private String orderedByDoctor;		// 开单医生
	private String rcptNo;		// 收据号
	private Integer visitId;		// 就诊ID
	private String printedRcptNo;		// 打印数据号
	private String prescAttr;		// 处方属性
	private String clinicNo;		// 门诊号
	private String orderGroup;		// 医生核算组代码
	
	public OutpOrderDesc() {
		super();
	}

	public OutpOrderDesc(String id){
		super(id);
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
	
	@Length(min=0, max=128, message="病人基本信息长度必须介于 0 和 128 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	public Integer getPrescIndicator() {
		return prescIndicator;
	}

	public void setPrescIndicator(Integer prescIndicator) {
		this.prescIndicator = prescIndicator;
	}
	
	@Length(min=0, max=128, message="开单科室长度必须介于 0 和 128 之间")
	public String getOrderedByDept() {
		return orderedByDept;
	}

	public void setOrderedByDept(String orderedByDept) {
		this.orderedByDept = orderedByDept;
	}
	
	@Length(min=0, max=40, message="开单医生长度必须介于 0 和 40 之间")
	public String getOrderedByDoctor() {
		return orderedByDoctor;
	}

	public void setOrderedByDoctor(String orderedByDoctor) {
		this.orderedByDoctor = orderedByDoctor;
	}
	
	@Length(min=0, max=40, message="收据号长度必须介于 0 和 40 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	
	@Length(min=0, max=40, message="打印数据号长度必须介于 0 和 40 之间")
	public String getPrintedRcptNo() {
		return printedRcptNo;
	}

	public void setPrintedRcptNo(String printedRcptNo) {
		this.printedRcptNo = printedRcptNo;
	}
	
	@Length(min=0, max=128, message="处方属性长度必须介于 0 和 128 之间")
	public String getPrescAttr() {
		return prescAttr;
	}

	public void setPrescAttr(String prescAttr) {
		this.prescAttr = prescAttr;
	}
	
	@Length(min=0, max=30, message="门诊号长度必须介于 0 和 30 之间")
	public String getClinicNo() {
		return clinicNo;
	}

	public void setClinicNo(String clinicNo) {
		this.clinicNo = clinicNo;
	}
	
	@Length(min=0, max=128, message="医生核算组代码长度必须介于 0 和 128 之间")
	public String getOrderGroup() {
		return orderGroup;
	}

	public void setOrderGroup(String orderGroup) {
		this.orderGroup = orderGroup;
	}
	
}