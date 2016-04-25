/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.materiel.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 药品库存Entity
 * @author zhaoning
 * @version 2016-04-22
 */
public class DrugStock extends DataEntity<DrugStock> {
	
	private static final long serialVersionUID = 1L;
	private String storage;		// 库存管理单位
	private String drugCode;		// 药品代码
	private String drugSpec;		// 规格
	private String units;		// 单位
	private String batchNo;		// 批号
	private Date expireDate;		// 有效期
	private String firmId;		// 厂家标识
	private Double purchasePrice;		// 进货价
	private Double discount;		// 折扣
	private String packageSpec;		// 包装规格
	private Double quantity;		// 数量
	private String packageUnits;		// 包装单位
	private Double subPackage1;		// 内含包装1
	private String subPackageUnits1;		// 内含包装1单位
	private String subPackageSpec1;		// 内含包装1规格
	private Double subPackage2;		// 内含包装2
	private String subPackageUnits2;		// 内含包装2单位
	private String subPackageSpec2;		// 内含包装2规格
	private String subStorage;		// 存放库房
	private String location;		// 货位
	private String documentNo;		// 入库单号
	private Integer supplyIndicator;		// 供应标志
	private Integer supplyMz;		// 适用门诊住院
	private String orgId;		// 所属组织结构
	
	public DrugStock() {
		super();
	}

	public DrugStock(String id){
		super(id);
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
	
	@Length(min=1, max=16, message="批号长度必须介于 1 和 16 之间")
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	
	@Length(min=1, max=10, message="厂家标识长度必须介于 1 和 10 之间")
	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	
	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	
	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	@Length(min=1, max=20, message="包装规格长度必须介于 1 和 20 之间")
	public String getPackageSpec() {
		return packageSpec;
	}

	public void setPackageSpec(String packageSpec) {
		this.packageSpec = packageSpec;
	}
	
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	
	@Length(min=0, max=8, message="包装单位长度必须介于 0 和 8 之间")
	public String getPackageUnits() {
		return packageUnits;
	}

	public void setPackageUnits(String packageUnits) {
		this.packageUnits = packageUnits;
	}
	
	public Double getSubPackage1() {
		return subPackage1;
	}

	public void setSubPackage1(Double subPackage1) {
		this.subPackage1 = subPackage1;
	}
	
	@Length(min=0, max=8, message="内含包装1单位长度必须介于 0 和 8 之间")
	public String getSubPackageUnits1() {
		return subPackageUnits1;
	}

	public void setSubPackageUnits1(String subPackageUnits1) {
		this.subPackageUnits1 = subPackageUnits1;
	}
	
	@Length(min=0, max=20, message="内含包装1规格长度必须介于 0 和 20 之间")
	public String getSubPackageSpec1() {
		return subPackageSpec1;
	}

	public void setSubPackageSpec1(String subPackageSpec1) {
		this.subPackageSpec1 = subPackageSpec1;
	}
	
	public Double getSubPackage2() {
		return subPackage2;
	}

	public void setSubPackage2(Double subPackage2) {
		this.subPackage2 = subPackage2;
	}
	
	@Length(min=0, max=8, message="内含包装2单位长度必须介于 0 和 8 之间")
	public String getSubPackageUnits2() {
		return subPackageUnits2;
	}

	public void setSubPackageUnits2(String subPackageUnits2) {
		this.subPackageUnits2 = subPackageUnits2;
	}
	
	@Length(min=0, max=20, message="内含包装2规格长度必须介于 0 和 20 之间")
	public String getSubPackageSpec2() {
		return subPackageSpec2;
	}

	public void setSubPackageSpec2(String subPackageSpec2) {
		this.subPackageSpec2 = subPackageSpec2;
	}
	
	@Length(min=1, max=8, message="存放库房长度必须介于 1 和 8 之间")
	public String getSubStorage() {
		return subStorage;
	}

	public void setSubStorage(String subStorage) {
		this.subStorage = subStorage;
	}
	
	@Length(min=0, max=20, message="货位长度必须介于 0 和 20 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=10, message="入库单号长度必须介于 0 和 10 之间")
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	public Integer getSupplyIndicator() {
		return supplyIndicator;
	}

	public void setSupplyIndicator(Integer supplyIndicator) {
		this.supplyIndicator = supplyIndicator;
	}
	
	public Integer getSupplyMz() {
		return supplyMz;
	}

	public void setSupplyMz(Integer supplyMz) {
		this.supplyMz = supplyMz;
	}
	
	@Length(min=0, max=64, message="所属组织结构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}