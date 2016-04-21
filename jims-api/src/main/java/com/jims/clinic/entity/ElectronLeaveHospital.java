/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 出院记录Entity
 * @author zhaoning
 * @version 2016-04-20
 */
public class ElectronLeaveHospital extends DataEntity<ElectronLeaveHospital> {
	
	private static final long serialVersionUID = 1L;
	private String zhuyuanxinxiId;		// 住院信息外键
	private String patientId;		// 病人信息外键
	private Date chuyuanshijian;		// 出院时间
	private String chuyaunzhenduan;		// 出院诊断
	private String zhenliaojieguo;		// 诊疗结果
	private String chuyuanqingkuang;		// 出院情况
	private String chuyuanyizhu;		// 出院医嘱
	private String ruyuanbingqing;		// 入院病情
	
	public ElectronLeaveHospital() {
		super();
	}

	public ElectronLeaveHospital(String id){
		super(id);
	}

	@Length(min=1, max=64, message="住院信息外键长度必须介于 1 和 64 之间")
	public String getZhuyuanxinxiId() {
		return zhuyuanxinxiId;
	}

	public void setZhuyuanxinxiId(String zhuyuanxinxiId) {
		this.zhuyuanxinxiId = zhuyuanxinxiId;
	}
	
	@Length(min=1, max=64, message="病人信息外键长度必须介于 1 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getChuyuanshijian() {
		return chuyuanshijian;
	}

	public void setChuyuanshijian(Date chuyuanshijian) {
		this.chuyuanshijian = chuyuanshijian;
	}
	
	public String getChuyaunzhenduan() {
		return chuyaunzhenduan;
	}

	public void setChuyaunzhenduan(String chuyaunzhenduan) {
		this.chuyaunzhenduan = chuyaunzhenduan;
	}
	
	public String getZhenliaojieguo() {
		return zhenliaojieguo;
	}

	public void setZhenliaojieguo(String zhenliaojieguo) {
		this.zhenliaojieguo = zhenliaojieguo;
	}
	
	public String getChuyuanqingkuang() {
		return chuyuanqingkuang;
	}

	public void setChuyuanqingkuang(String chuyuanqingkuang) {
		this.chuyuanqingkuang = chuyuanqingkuang;
	}
	
	public String getChuyuanyizhu() {
		return chuyuanyizhu;
	}

	public void setChuyuanyizhu(String chuyuanyizhu) {
		this.chuyuanyizhu = chuyuanyizhu;
	}
	
	public String getRuyuanbingqing() {
		return ruyuanbingqing;
	}

	public void setRuyuanbingqing(String ruyuanbingqing) {
		this.ruyuanbingqing = ruyuanbingqing;
	}
	
}