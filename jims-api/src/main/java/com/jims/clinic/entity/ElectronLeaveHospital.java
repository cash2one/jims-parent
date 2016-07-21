
package com.jims.clinic.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
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
	private Integer visitId;
	private String patientId;		// 病人信息外键
	private Date chuyuanshijian;		// 出院时间
	private String chuyaunzhenduan;		// 出院诊断
	private String zhenliaojieguo;		// 诊疗结果
	private String chuyuanqingkuang;		// 出院情况
	private String chuyuanyizhu;		// 出院医嘱
	private String ruyuanbingqing;		// 入院病情
	private String orgId;
	
	public ElectronLeaveHospital() {
		super();
	}

	public ElectronLeaveHospital(String id){
		super(id);
	}


	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}

	@Length(min=1, max=64, message="病人信息外键长度必须介于 1 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}