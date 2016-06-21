package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;

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

    private String chargeItemName;  // 对应价表项目名称

    public String getChargeItemName() {
        return chargeItemName;
    }

    public void setChargeItemName(String chargeItemName) {
        this.chargeItemName = chargeItemName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String orgId;		// 所属组织结构

    public String getStopDate() {
        return stopDate;
    }

    public void setStopDate(String stopDate) {
        this.stopDate = stopDate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    private String priceType; // 类别
    private String price;
    private String stopDate;
    private String count;

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public ClinicVsCharge() {
		super();
	}

	public ClinicVsCharge(String id){
		super(id);
	}

	public String getClinicItemClass() {
		return clinicItemClass;
	}

	public void setClinicItemClass(String clinicItemClass) {
		this.clinicItemClass = clinicItemClass;
	}

	public String getClinicItemCode() {
		return clinicItemCode;
	}

	public void setClinicItemCode(String clinicItemCode) {
		this.clinicItemCode = clinicItemCode;
	}

	public Integer getChargeItemNo() {
		return chargeItemNo;
	}

	public void setChargeItemNo(Integer chargeItemNo) {
		this.chargeItemNo = chargeItemNo;
	}

	public String getChargeItemClass() {
		return chargeItemClass;
	}

	public void setChargeItemClass(String chargeItemClass) {
		this.chargeItemClass = chargeItemClass;
	}

	public String getChargeItemCode() {
		return chargeItemCode;
	}

	public void setChargeItemCode(String chargeItemCode) {
		this.chargeItemCode = chargeItemCode;
	}

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

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getBackbillRule() {
		return backbillRule;
	}

	public void setBackbillRule(String backbillRule) {
		this.backbillRule = backbillRule;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}