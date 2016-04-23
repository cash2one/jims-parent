/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.materiel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 药品字典Entity
 * @author zhaoning
 * @version 2016-04-22
 */
public class DrugDict extends DataEntity<DrugDict> {
	
	private static final long serialVersionUID = 1L;
	private String drugCode;		// 药品代码
	private String drugName;		// 药品名称
	private String drugSpec;		// 规格
	private String units;		// 单位
	private String drugForm;		// 剂型
	private String toxiProperty;		// 毒理分类
	private Double dosePerUnit;		// 最小单位剂量
	private String doseUnits;		// 剂量单位
	private Integer drugIndicator;		// 药品类别标志[1西药，2中草药，3中成药，5辅料，6试剂，8材料，9其他]
	private String inputCode;		// 输入码
	private String otc;		// otc
	private String limitClass;		// limit_class
	private Integer stopFlag;		// stop_flag
	private Date enteredDatetime;		// 录入日期
	private String preciousFlag;		// 贵重药品等级标示
	
	public DrugDict() {
		super();
	}

	public DrugDict(String id){
		super(id);
	}

	@Length(min=1, max=20, message="药品代码长度必须介于 1 和 20 之间")
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Length(min=0, max=100, message="药品名称长度必须介于 0 和 100 之间")
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	@Length(min=1, max=20, message="规格长度必须介于 1 和 20 之间")
	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	
	@Length(min=0, max=8, message="单位长度必须介于 0 和 8 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	@Length(min=0, max=10, message="剂型长度必须介于 0 和 10 之间")
	public String getDrugForm() {
		return drugForm;
	}

	public void setDrugForm(String drugForm) {
		this.drugForm = drugForm;
	}
	
	@Length(min=0, max=10, message="毒理分类长度必须介于 0 和 10 之间")
	public String getToxiProperty() {
		return toxiProperty;
	}

	public void setToxiProperty(String toxiProperty) {
		this.toxiProperty = toxiProperty;
	}
	
	public Double getDosePerUnit() {
		return dosePerUnit;
	}

	public void setDosePerUnit(Double dosePerUnit) {
		this.dosePerUnit = dosePerUnit;
	}
	
	@Length(min=0, max=8, message="剂量单位长度必须介于 0 和 8 之间")
	public String getDoseUnits() {
		return doseUnits;
	}

	public void setDoseUnits(String doseUnits) {
		this.doseUnits = doseUnits;
	}
	
	@NotNull(message="药品类别标志[1西药，2中草药，3中成药，5辅料，6试剂，8材料，9其他]不能为空")
	public Integer getDrugIndicator() {
		return drugIndicator;
	}

	public void setDrugIndicator(Integer drugIndicator) {
		this.drugIndicator = drugIndicator;
	}
	
	@Length(min=0, max=8, message="输入码长度必须介于 0 和 8 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=10, message="otc长度必须介于 0 和 10 之间")
	public String getOtc() {
		return otc;
	}

	public void setOtc(String otc) {
		this.otc = otc;
	}
	
	@Length(min=0, max=4, message="limit_class长度必须介于 0 和 4 之间")
	public String getLimitClass() {
		return limitClass;
	}

	public void setLimitClass(String limitClass) {
		this.limitClass = limitClass;
	}
	
	public Integer getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(Integer stopFlag) {
		this.stopFlag = stopFlag;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEnteredDatetime() {
		return enteredDatetime;
	}

	public void setEnteredDatetime(Date enteredDatetime) {
		this.enteredDatetime = enteredDatetime;
	}
	
	@Length(min=0, max=4, message="贵重药品等级标示长度必须介于 0 和 4 之间")
	public String getPreciousFlag() {
		return preciousFlag;
	}

	public void setPreciousFlag(String preciousFlag) {
		this.preciousFlag = preciousFlag;
	}
	
}