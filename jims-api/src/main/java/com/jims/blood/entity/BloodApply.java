/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.blood.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用血申请Entity
 * @author qlx
 * @version 2016-04-28
 */
public class BloodApply extends DataEntity<BloodApply> {
	
	private static final long serialVersionUID = 1L;
	private String applyNum;		// 申请单号
	private String inpNo;		// 住院号
	private String idNo;		// 病人身份证号
	private String deptCode;		// 科室代码
	private String patName;		// 受血者姓名
	private String patSex;		// 性别
	private Date birthday;		// 出生年月
	private String feeType;		// 费别
	private String patSource;		// 病人来源，市区：1；郊县：2，外省市：3；港澳台：4
	private String bloodPaper;		// 献血证类型
	private String bloodInuse;		// 用血方式：血库，自体，互助
	private String bloodDiagnose;		// 诊断及输血适应症
	private String bloodTaboo;		// 输血反应及输血禁忌症
	private Integer hematin;		// 血色素
	private Integer platelet;		// 血小板
	private Integer leucocyte;		// 白血球
	private String patBloodGroup;		// 受血者血型
	private String rh;		// Rh血型
	private Integer bloodSum;		// 输血总量
	private Date applyDate;		// 申请填写时间
	private Date gatherDate;		// 血库收到时间
	private String director;		// 科主任
	private String physician;		// 主治军医
	private String doctor;		// 军医
	private String price;		// 划价标志
	private Double hct;		// hct
	private Double alt;		// alt
	private String hbsag;		// hbsag
	private String hcv;		// hcv
	private String hiv;		// hiv
	private String antiMd;		// 梅毒
	private String shineBlood;		// 辐照血
	private String preBloodType;		// 预输血型
	private String orgId;		// 所属结构
	private List<BloodCapacity> bloodCapacityList=new ArrayList<BloodCapacity>();
	public BloodApply() {
		super();
	}

	public BloodApply(String id){
		super(id);
	}
	public List<BloodCapacity> getBloodCapacityList() {
		return bloodCapacityList;
	}

	public void setBloodCapacityList(List<BloodCapacity> bloodCapacityList) {
		this.bloodCapacityList = bloodCapacityList;
	}
	@Length(min=1, max=6, message="申请单号长度必须介于 1 和 6 之间")
	public String getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}
	
	@Length(min=0, max=10, message="住院号长度必须介于 0 和 10 之间")
	public String getInpNo() {
		return inpNo;
	}

	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}
	
	@Length(min=0, max=10, message="病人身份证号长度必须介于 0 和 10 之间")
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	@Length(min=0, max=8, message="科室代码长度必须介于 0 和 8 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	@Length(min=0, max=20, message="受血者姓名长度必须介于 0 和 20 之间")
	public String getPatName() {
		return patName;
	}

	public void setPatName(String patName) {
		this.patName = patName;
	}
	
	@Length(min=0, max=4, message="性别长度必须介于 0 和 4 之间")
	public String getPatSex() {
		return patSex;
	}

	public void setPatSex(String patSex) {
		this.patSex = patSex;
	}

    @JsonSerialize(using = CustomDateSerializer.class)
	public Date getBirthday() {
		return birthday;
	}
    @JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Length(min=0, max=8, message="费别长度必须介于 0 和 8 之间")
	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	
	@Length(min=0, max=1, message="病人来源，市区：1；郊县：2，外省市：3；港澳台：4长度必须介于 0 和 1 之间")
	public String getPatSource() {
		return patSource;
	}

	public void setPatSource(String patSource) {
		this.patSource = patSource;
	}
	
	@Length(min=0, max=8, message="献血证类型长度必须介于 0 和 8 之间")
	public String getBloodPaper() {
		return bloodPaper;
	}

	public void setBloodPaper(String bloodPaper) {
		this.bloodPaper = bloodPaper;
	}
	
	@Length(min=0, max=4, message="用血方式：血库，自体，互助长度必须介于 0 和 4 之间")
	public String getBloodInuse() {
		return bloodInuse;
	}

	public void setBloodInuse(String bloodInuse) {
		this.bloodInuse = bloodInuse;
	}
	
	@Length(min=0, max=80, message="诊断及输血适应症长度必须介于 0 和 80 之间")
	public String getBloodDiagnose() {
		return bloodDiagnose;
	}

	public void setBloodDiagnose(String bloodDiagnose) {
		this.bloodDiagnose = bloodDiagnose;
	}
	
	@Length(min=0, max=40, message="输血反应及输血禁忌症长度必须介于 0 和 40 之间")
	public String getBloodTaboo() {
		return bloodTaboo;
	}

	public void setBloodTaboo(String bloodTaboo) {
		this.bloodTaboo = bloodTaboo;
	}
	
	public Integer getHematin() {
		return hematin;
	}

	public void setHematin(Integer hematin) {
		this.hematin = hematin;
	}
	
	public Integer getPlatelet() {
		return platelet;
	}

	public void setPlatelet(Integer platelet) {
		this.platelet = platelet;
	}
	
	public Integer getLeucocyte() {
		return leucocyte;
	}

	public void setLeucocyte(Integer leucocyte) {
		this.leucocyte = leucocyte;
	}
	
	@Length(min=0, max=4, message="受血者血型长度必须介于 0 和 4 之间")
	public String getPatBloodGroup() {
		return patBloodGroup;
	}

	public void setPatBloodGroup(String patBloodGroup) {
		this.patBloodGroup = patBloodGroup;
	}
	
	@Length(min=0, max=4, message="Rh血型长度必须介于 0 和 4 之间")
	public String getRh() {
		return rh;
	}

	public void setRh(String rh) {
		this.rh = rh;
	}
	
	public Integer getBloodSum() {
		return bloodSum;
	}

	public void setBloodSum(Integer bloodSum) {
		this.bloodSum = bloodSum;
	}

    @JsonSerialize(using = CustomDateSerializer.class)
	public Date getApplyDate() {
		return applyDate;
	}

    @JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

    @JsonSerialize(using = CustomDateSerializer.class)
	public Date getGatherDate() {
		return gatherDate;
	}

    @JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setGatherDate(Date gatherDate) {
		this.gatherDate = gatherDate;
	}
	
	@Length(min=0, max=20, message="科主任长度必须介于 0 和 20 之间")
	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	@Length(min=0, max=20, message="主治军医长度必须介于 0 和 20 之间")
	public String getPhysician() {
		return physician;
	}

	public void setPhysician(String physician) {
		this.physician = physician;
	}
	
	@Length(min=0, max=20, message="军医长度必须介于 0 和 20 之间")
	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}
	
	@Length(min=0, max=1, message="划价标志长度必须介于 0 和 1 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	public Double getHct() {
		return hct;
	}

	public void setHct(Double hct) {
		this.hct = hct;
	}
	
	public Double getAlt() {
		return alt;
	}

	public void setAlt(Double alt) {
		this.alt = alt;
	}
	
	@Length(min=0, max=2, message="hbsag长度必须介于 0 和 2 之间")
	public String getHbsag() {
		return hbsag;
	}

	public void setHbsag(String hbsag) {
		this.hbsag = hbsag;
	}
	
	@Length(min=0, max=2, message="hcv长度必须介于 0 和 2 之间")
	public String getHcv() {
		return hcv;
	}

	public void setHcv(String hcv) {
		this.hcv = hcv;
	}
	
	@Length(min=0, max=2, message="hiv长度必须介于 0 和 2 之间")
	public String getHiv() {
		return hiv;
	}

	public void setHiv(String hiv) {
		this.hiv = hiv;
	}
	
	@Length(min=0, max=2, message="梅毒长度必须介于 0 和 2 之间")
	public String getAntiMd() {
		return antiMd;
	}

	public void setAntiMd(String antiMd) {
		this.antiMd = antiMd;
	}
	
	@Length(min=0, max=2, message="辐照血长度必须介于 0 和 2 之间")
	public String getShineBlood() {
		return shineBlood;
	}

	public void setShineBlood(String shineBlood) {
		this.shineBlood = shineBlood;
	}
	
	@Length(min=0, max=2, message="预输血型长度必须介于 0 和 2 之间")
	public String getPreBloodType() {
		return preBloodType;
	}

	public void setPreBloodType(String preBloodType) {
		this.preBloodType = preBloodType;
	}
	
	@Length(min=0, max=64, message="所属结构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}