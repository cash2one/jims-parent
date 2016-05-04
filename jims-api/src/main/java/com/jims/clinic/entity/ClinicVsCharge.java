package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

/**
 * 诊疗项目与价表对照Entity
 * @author lgx
 * @version 2016-04-28
 */
public class ClinicVsCharge extends DataEntity<ClinicVsCharge> {
	
	private static final long serialVersionUID = 1L;
	private String clinicItemClass;		// 临床诊疗项目类别
	private String clinicItemCode;		// 临床诊疗项目代码
	private Integer chargeItemNo;		// 对应价表项目序号
	private String chargeItemClass;		// 对应价表项目分类
	private String chargeItemCode;		// 对应价表项目代码
	private String chargeItemSpec;		// 对应价表项目规格
	private Integer amount;		// 对应价表项目数量
	private String units;		// 对应价表项目单位
	private String backbillRule;		// 划价规则
	private String orgId;		// 所属组织结构
	
	public ClinicVsCharge() {
		super();
	}

	public ClinicVsCharge(String id){
		super(id);
	}

	@Length(min=1, max=1, message="临床诊疗项目类别长度必须介于 1 和 1 之间")
	public String getClinicItemClass() {
		return clinicItemClass;
	}

	public void setClinicItemClass(String clinicItemClass) {
		this.clinicItemClass = clinicItemClass;
	}
	
	@Length(min=1, max=20, message="临床诊疗项目代码长度必须介于 1 和 20 之间")
	public String getClinicItemCode() {
		return clinicItemCode;
	}

	public void setClinicItemCode(String clinicItemCode) {
		this.clinicItemCode = clinicItemCode;
	}
	
	@NotNull(message="对应价表项目序号不能为空")
	public Integer getChargeItemNo() {
		return chargeItemNo;
	}

	public void setChargeItemNo(Integer chargeItemNo) {
		this.chargeItemNo = chargeItemNo;
	}
	
	@Length(min=0, max=1, message="对应价表项目分类长度必须介于 0 和 1 之间")
	public String getChargeItemClass() {
		return chargeItemClass;
	}

	public void setChargeItemClass(String chargeItemClass) {
		this.chargeItemClass = chargeItemClass;
	}
	
	@Length(min=0, max=20, message="对应价表项目代码长度必须介于 0 和 20 之间")
	public String getChargeItemCode() {
		return chargeItemCode;
	}

	public void setChargeItemCode(String chargeItemCode) {
		this.chargeItemCode = chargeItemCode;
	}
	
	@Length(min=0, max=50, message="对应价表项目规格长度必须介于 0 和 50 之间")
	public String getChargeItemSpec() {
		return chargeItemSpec;
	}

	public void setChargeItemSpec(String chargeItemSpec) {
		this.chargeItemSpec = chargeItemSpec;
	}
	
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=8, message="对应价表项目单位长度必须介于 0 和 8 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	@Length(min=0, max=10, message="划价规则长度必须介于 0 和 10 之间")
	public String getBackbillRule() {
		return backbillRule;
	}

	public void setBackbillRule(String backbillRule) {
		this.backbillRule = backbillRule;
	}
	
	@Length(min=0, max=64, message="所属组织结构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}