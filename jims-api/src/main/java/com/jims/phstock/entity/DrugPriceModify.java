package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import javax.validation.constraints.NotNull;


/**
 * 调价记录表Entity
 * @author txb
 * @version 2016-05-18
 */
public class DrugPriceModify extends DataEntity<DrugPriceModify> {
	
	private static final long serialVersionUID = 1L;
	private String drugCode;		// 药品代码
	private String drugSpec;		// 包装规格
	private String units;		// 包装单位
	private String firmId;		// 厂家标识
	private String minSpec;		// 最小规格
	private String minUnits;		// 最小单位
	private Double originalTradePrice;		// 原市场批发价
	private Double currentTradePrice;		// 现市场批发价
	private Double originalRetailPrice;		// 原市场零售价
	private Double currentRetailPrice;		// 现市场零售价
	private Date noticeEfficientDate;		// 调价通知生效日期
	private Date actualEfficientDate;		// 调价实际生效日期
	private String modifyCredential;		// 调价依据
	private String operator;		// operator
	private String confirmOperator;		// 确认人
	private String orgId;		// 组织机构
	
	public DrugPriceModify() {
		super();
	}

	public DrugPriceModify(String id){
		super(id);
	}

	@Length(min=1, max=20, message="药品代码长度必须介于 1 和 20 之间")
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Length(min=1, max=20, message="包装规格长度必须介于 1 和 20 之间")
	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	
	@Length(min=0, max=8, message="包装单位长度必须介于 0 和 8 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	@Length(min=1, max=64, message="厂家标识长度必须介于 1 和 64 之间")
	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	
	@Length(min=0, max=20, message="最小规格长度必须介于 0 和 20 之间")
	public String getMinSpec() {
		return minSpec;
	}

	public void setMinSpec(String minSpec) {
		this.minSpec = minSpec;
	}
	
	@Length(min=0, max=8, message="最小单位长度必须介于 0 和 8 之间")
	public String getMinUnits() {
		return minUnits;
	}

	public void setMinUnits(String minUnits) {
		this.minUnits = minUnits;
	}
	
	public Double getOriginalTradePrice() {
		return originalTradePrice;
	}

	public void setOriginalTradePrice(Double originalTradePrice) {
		this.originalTradePrice = originalTradePrice;
	}
	
	public Double getCurrentTradePrice() {
		return currentTradePrice;
	}

	public void setCurrentTradePrice(Double currentTradePrice) {
		this.currentTradePrice = currentTradePrice;
	}
	
	public Double getOriginalRetailPrice() {
		return originalRetailPrice;
	}

	public void setOriginalRetailPrice(Double originalRetailPrice) {
		this.originalRetailPrice = originalRetailPrice;
	}
	
	public Double getCurrentRetailPrice() {
		return currentRetailPrice;
	}

	public void setCurrentRetailPrice(Double currentRetailPrice) {
		this.currentRetailPrice = currentRetailPrice;
	}
	

	@NotNull(message="调价通知生效日期不能为空")
	public Date getNoticeEfficientDate() {
		return noticeEfficientDate;
	}

	public void setNoticeEfficientDate(Date noticeEfficientDate) {
		this.noticeEfficientDate = noticeEfficientDate;
	}
	

	public Date getActualEfficientDate() {
		return actualEfficientDate;
	}

	public void setActualEfficientDate(Date actualEfficientDate) {
		this.actualEfficientDate = actualEfficientDate;
	}
	
	@Length(min=0, max=50, message="调价依据长度必须介于 0 和 50 之间")
	public String getModifyCredential() {
		return modifyCredential;
	}

	public void setModifyCredential(String modifyCredential) {
		this.modifyCredential = modifyCredential;
	}
	
	@Length(min=0, max=64, message="operator长度必须介于 0 和 64 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Length(min=0, max=64, message="确认人长度必须介于 0 和 64 之间")
	public String getConfirmOperator() {
		return confirmOperator;
	}

	public void setConfirmOperator(String confirmOperator) {
		this.confirmOperator = confirmOperator;
	}
	
	@Length(min=0, max=64, message="组织机构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}