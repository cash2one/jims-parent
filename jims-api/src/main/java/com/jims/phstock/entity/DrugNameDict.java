/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 药品名称Entity
 * @author zhaoning
 * @version 2016-04-22
 */
public class DrugNameDict extends DataEntity<DrugNameDict> {
	
	private static final long serialVersionUID = 1L;
	private String drugCode;		// 药品代码
	private String drugName;		// 药品名称
	private Integer stdIndicator;		// 正名标志
	private String inputCode;		// 输入码
	private String inputCodeWb;		// 五笔码
	private String chemicalName;		// chemical_name
	
	public DrugNameDict() {
		super();
	}

	public DrugNameDict(String id){
		super(id);
	}

	@Length(min=1, max=20, message="药品代码长度必须介于 1 和 20 之间")
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Length(min=1, max=100, message="药品名称长度必须介于 1 和 100 之间")
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	public Integer getStdIndicator() {
		return stdIndicator;
	}

	public void setStdIndicator(Integer stdIndicator) {
		this.stdIndicator = stdIndicator;
	}
	
	@Length(min=0, max=8, message="输入码长度必须介于 0 和 8 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=8, message="五笔码长度必须介于 0 和 8 之间")
	public String getInputCodeWb() {
		return inputCodeWb;
	}

	public void setInputCodeWb(String inputCodeWb) {
		this.inputCodeWb = inputCodeWb;
	}
	
	@Length(min=0, max=100, message="chemical_name长度必须介于 0 和 100 之间")
	public String getChemicalName() {
		return chemicalName;
	}

	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}
	
}