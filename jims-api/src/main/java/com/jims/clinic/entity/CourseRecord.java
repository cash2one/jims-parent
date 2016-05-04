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
 * 病程主记录Entity
 * @author zhaoning
 * @version 2016-04-20
 */
public class CourseRecord extends DataEntity<CourseRecord> {
	
	private static final long serialVersionUID = 1L;
	private String zhuyuanId;		// 住院ID
	private String patientId;		// 病人ID
	private Date luruShijian;		// 录入时间
	private String canyuDoctor;		// 参与医生
	private String type;		// 病程类型
	private String zhuyuanDept;		// 住院科别
	private String zhuyuanBingq;		// 住院病区
	private String bingCh;		// 病床号
	private Date zhuyuanChangetime;		// zhuyuan_changetime
	private Date luruDatetime;		// 记录病程录入时间
	
	public CourseRecord() {
		super();
	}

	public CourseRecord(String id){
		super(id);
	}

	@Length(min=0, max=64, message="住院ID长度必须介于 0 和 64 之间")
	public String getZhuyuanId() {
		return zhuyuanId;
	}

	public void setZhuyuanId(String zhuyuanId) {
		this.zhuyuanId = zhuyuanId;
	}
	
	@Length(min=0, max=64, message="病人ID长度必须介于 0 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

    @JsonSerialize(using = CustomDateSerializer.class)
	public Date getLuruShijian() {
		return luruShijian;
	}
    @JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setLuruShijian(Date luruShijian) {
		this.luruShijian = luruShijian;
	}
	
	@Length(min=0, max=200, message="参与医生长度必须介于 0 和 200 之间")
	public String getCanyuDoctor() {
		return canyuDoctor;
	}

	public void setCanyuDoctor(String canyuDoctor) {
		this.canyuDoctor = canyuDoctor;
	}
	
	@Length(min=1, max=200, message="病程类型长度必须介于 1 和 200 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=100, message="住院科别长度必须介于 0 和 100 之间")
	public String getZhuyuanDept() {
		return zhuyuanDept;
	}

	public void setZhuyuanDept(String zhuyuanDept) {
		this.zhuyuanDept = zhuyuanDept;
	}
	
	@Length(min=0, max=200, message="住院病区长度必须介于 0 和 200 之间")
	public String getZhuyuanBingq() {
		return zhuyuanBingq;
	}

	public void setZhuyuanBingq(String zhuyuanBingq) {
		this.zhuyuanBingq = zhuyuanBingq;
	}
	
	@Length(min=0, max=100, message="病床号长度必须介于 0 和 100 之间")
	public String getBingCh() {
		return bingCh;
	}

	public void setBingCh(String bingCh) {
		this.bingCh = bingCh;
	}

    @JsonSerialize(using = CustomDateSerializer.class)
	public Date getZhuyuanChangetime() {
		return zhuyuanChangetime;
	}
    @JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setZhuyuanChangetime(Date zhuyuanChangetime) {
		this.zhuyuanChangetime = zhuyuanChangetime;
	}

    @JsonSerialize(using = CustomDateSerializer.class)
	public Date getLuruDatetime() {
		return luruDatetime;
	}
    @JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setLuruDatetime(Date luruDatetime) {
		this.luruDatetime = luruDatetime;
	}
	
}