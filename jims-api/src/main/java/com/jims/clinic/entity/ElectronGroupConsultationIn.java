/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 参与会诊信息Entity
 * @author zhaoning
 * @version 2016-04-23
 */
public class ElectronGroupConsultationIn extends DataEntity<ElectronGroupConsultationIn> {
	
	private static final long serialVersionUID = 1L;
	private String doctorId;		// 医生ID
	private String huizhenId;		// 参与会诊ID
	private String huizhenyijian;		// 会诊意见
	private String qianmingstype;		// 签名类型
	private Date qianmingshijian;		// 签名时间
	private String qianmingId;		// 医生签名

	private ElectronGroupConsultation electronGroupConsultation;
	private List<ElectronGroupConsultation> list;
	
	public ElectronGroupConsultationIn() {
		super();
	}

	public ElectronGroupConsultationIn(String id){
		super(id);
	}

	@Length(min=0, max=64, message="医生ID长度必须介于 0 和 64 之间")
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	@Length(min=0, max=64, message="参与会诊ID长度必须介于 0 和 64 之间")
	public String getHuizhenId() {
		return huizhenId;
	}

	public void setHuizhenId(String huizhenId) {
		this.huizhenId = huizhenId;
	}
	
	public String getHuizhenyijian() {
		return huizhenyijian;
	}

	public void setHuizhenyijian(String huizhenyijian) {
		this.huizhenyijian = huizhenyijian;
	}
	
	@Length(min=0, max=1, message="签名类型长度必须介于 0 和 1 之间")
	public String getQianmingstype() {
		return qianmingstype;
	}

	public void setQianmingstype(String qianmingstype) {
		this.qianmingstype = qianmingstype;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getQianmingshijian() {
		return qianmingshijian;
	}

	public void setQianmingshijian(Date qianmingshijian) {
		this.qianmingshijian = qianmingshijian;
	}
	
	@Length(min=0, max=64, message="医生签名长度必须介于 0 和 64 之间")
	public String getQianmingId() {
		return qianmingId;
	}

	public void setQianmingId(String qianmingId) {
		this.qianmingId = qianmingId;
	}

	public ElectronGroupConsultation getElectronGroupConsultation() {
		return electronGroupConsultation;
	}

	public void setElectronGroupConsultation(ElectronGroupConsultation electronGroupConsultation) {
		this.electronGroupConsultation = electronGroupConsultation;
	}

	public List<ElectronGroupConsultation> getList() {
		return list;
	}

	public void setList(List<ElectronGroupConsultation> list) {
		this.list = list;
	}
}