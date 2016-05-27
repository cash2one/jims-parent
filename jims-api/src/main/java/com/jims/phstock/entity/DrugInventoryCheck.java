package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 盘点记录
 * @author txb
 * @version 2016-05-23
 */
public class DrugInventoryCheck extends DataEntity<DrugInventoryCheck> {
	
	private static final long serialVersionUID = 1L;
	private Date checkYearMonth;		// 盘点年月
	private String storage;		// 库存管理单位
	private String drugCode;		// 药品代码
	private String drugSpec;		// 包装规格
	private String units;		// 单位
	private String firmId;		// 厂家标识
	private String batchNo;		// 批号
	private String minSpec;		// 最小单位规格
	private String minUnits;		// 最小单位
	private String subStorage;		// 存放库房
	private Double accountQuantity;		// 帐面数量
	private Double actualQuantity;		// 实际数量
	private Double tradePrice;		// 市场批发价
	private Double retailPrice;		// 市场零售价
	private Integer recStatus;		// 状态0，为暂存；1，终存
	private Integer changeFlag;		// 更改库存标志
    private String orgId;//组织机构id

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public DrugInventoryCheck() {
		super();
	}

	public DrugInventoryCheck(String id){
		super(id);
	}

	@NotNull(message="盘点年月不能为空")
	public Date getCheckYearMonth() {
		return checkYearMonth;
	}

	public void setCheckYearMonth(Date checkYearMonth) {
		this.checkYearMonth = checkYearMonth;
	}
	
	@Length(min=1, max=8, message="库存管理单位长度必须介于 1 和 8 之间")
	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
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
	
	@Length(min=0, max=8, message="单位长度必须介于 0 和 8 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	@Length(min=1, max=10, message="厂家标识长度必须介于 1 和 10 之间")
	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	
	@Length(min=1, max=16, message="批号长度必须介于 1 和 16 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@Length(min=1, max=20, message="最小单位规格长度必须介于 1 和 20 之间")
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
	
	@Length(min=0, max=10, message="存放库房长度必须介于 0 和 10 之间")
	public String getSubStorage() {
		return subStorage;
	}

	public void setSubStorage(String subStorage) {
		this.subStorage = subStorage;
	}
	
	public Double getAccountQuantity() {
		return accountQuantity;
	}

	public void setAccountQuantity(Double accountQuantity) {
		this.accountQuantity = accountQuantity;
	}
	
	public Double getActualQuantity() {
		return actualQuantity;
	}

	public void setActualQuantity(Double actualQuantity) {
		this.actualQuantity = actualQuantity;
	}
	
	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}
	
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public Integer getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(Integer recStatus) {
		this.recStatus = recStatus;
	}
	
	public Integer getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(Integer changeFlag) {
		this.changeFlag = changeFlag;
	}
	
}