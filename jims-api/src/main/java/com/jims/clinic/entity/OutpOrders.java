/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 门诊医嘱记录Entity
 * @author zhaoning
 * @version 2016-04-20
 */
public class OutpOrders extends DataEntity<OutpOrders> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人标识号
	private Date visitDate;		// 就诊日期
	private Integer visitNo;		// 就诊序号
	private String serialNo;		// 流水号
	private String orderedBy;		// 开单科室
	private String doctor;		// 开单医生
	private Date orderDate;		// 开单日期
	private String clinicNo;		// clinic_no
	private String doctorNo;		// doctor_no
	private String nurse;		// 护士登录名
	
	public OutpOrders() {
		super();
	}

	public OutpOrders(String id){
		super(id);
	}

	@Length(min=0, max=128, message="病人标识号长度必须介于 0 和 128 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
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
	
	@Length(min=0, max=10, message="流水号长度必须介于 0 和 10 之间")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=0, max=8, message="开单科室长度必须介于 0 和 8 之间")
	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}
	
	@Length(min=0, max=20, message="开单医生长度必须介于 0 和 20 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	@Length(min=0, max=15, message="clinic_no长度必须介于 0 和 15 之间")
	public String getClinicNo() {
		return clinicNo;
	}

	public void setClinicNo(String clinicNo) {
		this.clinicNo = clinicNo;
	}
	
	@Length(min=0, max=16, message="doctor_no长度必须介于 0 和 16 之间")
	public String getDoctorNo() {
		return doctorNo;
	}

	public void setDoctorNo(String doctorNo) {
		this.doctorNo = doctorNo;
	}
	
	@Length(min=0, max=16, message="护士登录名长度必须介于 0 和 16 之间")
	public String getNurse() {
		return nurse;
	}

	public void setNurse(String nurse) {
		this.nurse = nurse;
	}
	
}