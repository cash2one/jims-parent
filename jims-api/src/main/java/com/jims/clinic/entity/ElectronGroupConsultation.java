/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 会诊记录Entity
 * @author zhaoning
 * @version 2016-04-23
 */
public class ElectronGroupConsultation extends DataEntity<ElectronGroupConsultation> {
	
	private static final long serialVersionUID = 1L;
	private String zhuyuanId;		// 住院号
	private String patientId;		// 患者id
	private String doctorId;		// 医生ID
	private String grouptype;		// 类型：紧急、一般
	private Date shenqingshijian;		// 申请时间
	private String bingqingzhaiyao;		// 病情摘要
	private String huizhenliyou;		// 会诊理由
	private String huizhenyijian;		// 会诊意见
	private String fabuflag;		// 发布标志
	
	public ElectronGroupConsultation() {
		super();
	}

	public ElectronGroupConsultation(String id){
		super(id);
	}

	@Length(min=0, max=64, message="住院号长度必须介于 0 和 64 之间")
	public String getZhuyuanId() {
		return zhuyuanId;
	}

	public void setZhuyuanId(String zhuyuanId) {
		this.zhuyuanId = zhuyuanId;
	}
	
	@Length(min=0, max=64, message="患者id长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=0, max=64, message="医生ID长度必须介于 0 和 64 之间")
	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	
	@Length(min=0, max=1, message="类型：紧急、一般长度必须介于 0 和 1 之间")
	public String getGrouptype() {
		return grouptype;
	}

	public void setGrouptype(String grouptype) {
		this.grouptype = grouptype;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShenqingshijian() {
		return shenqingshijian;
	}

	public void setShenqingshijian(Date shenqingshijian) {
		this.shenqingshijian = shenqingshijian;
	}
	
	public String getBingqingzhaiyao() {
		return bingqingzhaiyao;
	}

	public void setBingqingzhaiyao(String bingqingzhaiyao) {
		this.bingqingzhaiyao = bingqingzhaiyao;
	}
	
	public String getHuizhenliyou() {
		return huizhenliyou;
	}

	public void setHuizhenliyou(String huizhenliyou) {
		this.huizhenliyou = huizhenliyou;
	}
	
	public String getHuizhenyijian() {
		return huizhenyijian;
	}

	public void setHuizhenyijian(String huizhenyijian) {
		this.huizhenyijian = huizhenyijian;
	}
	
	@Length(min=0, max=10, message="发布标志长度必须介于 0 和 10 之间")
	public String getFabuflag() {
		return fabuflag;
	}

	public void setFabuflag(String fabuflag) {
		this.fabuflag = fabuflag;
	}
	
}