/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 病案首页类别Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class MrFeeClassDict extends DataEntity<MrFeeClassDict> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalId;		// 医院id
	private String mrFeeClassCode;		// 费用分类代码
	private String mrFeeClassName;		// 费用分类名称
	private String inputCode;		// 输入码
	private String mrFeeClassDesc;		// 费用分类描述
	
	public MrFeeClassDict() {
		super();
	}

	public MrFeeClassDict(String id){
		super(id);
	}

	@Length(min=0, max=64, message="医院id长度必须介于 0 和 64 之间")
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Length(min=0, max=1, message="费用分类代码长度必须介于 0 和 1 之间")
	public String getMrFeeClassCode() {
		return mrFeeClassCode;
	}

	public void setMrFeeClassCode(String mrFeeClassCode) {
		this.mrFeeClassCode = mrFeeClassCode;
	}
	
	@Length(min=0, max=4, message="费用分类名称长度必须介于 0 和 4 之间")
	public String getMrFeeClassName() {
		return mrFeeClassName;
	}

	public void setMrFeeClassName(String mrFeeClassName) {
		this.mrFeeClassName = mrFeeClassName;
	}
	
	@Length(min=0, max=8, message="输入码长度必须介于 0 和 8 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=50, message="费用分类描述长度必须介于 0 和 50 之间")
	public String getMrFeeClassDesc() {
		return mrFeeClassDesc;
	}

	public void setMrFeeClassDesc(String mrFeeClassDesc) {
		this.mrFeeClassDesc = mrFeeClassDesc;
	}
	
}