package com.jims.phstock.entity;

import java.util.Date;

import com.jims.common.persistence.DataEntity;

/**
 * 药品出库明细记录Entity
 * @author lgx
 * @version 2016-05-23
 */
public class DrugExportDetail extends DataEntity<DrugExportDetail> {
	
	private static final long serialVersionUID = 1L;
	private String documentNo;		// 出库单号
	private Integer itemNo;		// 项目序号
	private String drugCode;		// 药品代码
	private String drugSpec;		// 规格
	private String units;		// 单位
	private String batchNo;		// 批号
	private Date expireDate;		// 有效期
	private String firmId;		// 厂家标识
	private String importDocumentNo;		// 相关入库单号
	private Double purchasePrice;		// 出库价
	private Double retailPrice;		// 零售价
	private String packageSpec;		// 包装规格
	private Double quantity;		// 数量
	private String packageUnits;		// 包装单位
	private Double subPackage1;		// 内含包装1
	private String subPackageUnits1;		// 内含包装1单位
	private String subPackageSpec1;		// 内含包装1规格
	private Double subPackage2;		// 内含包装2
	private String subPackageUnits2;		// 内含包装2单位
	private String subPackageSpec2;		// 内含包装2规格
	private Double tradePrice;		// 批发价
	private Double inventory;		// 出库后库存数
	private String orgId;		// 所属结构

    private String drugStockId;   // 药品库存Id
    private String firmName;
    private String drugName;
    private Double stock;
    public String getDrugStockId() {
        return drugStockId;
    }

    public void setDrugStockId(String drugStockId) {
        this.drugStockId = drugStockId;
    }
	
	public DrugExportDetail() {
		super();
	}

	public DrugExportDetail(String id){
		super(id);
	}


	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}


	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}


	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}


	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}


	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}


	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}


	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}


	public String getImportDocumentNo() {
		return importDocumentNo;
	}

	public void setImportDocumentNo(String importDocumentNo) {
		this.importDocumentNo = importDocumentNo;
	}


	public Double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}


	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}


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


	public String getSubPackageUnits1() {
		return subPackageUnits1;
	}

	public void setSubPackageUnits1(String subPackageUnits1) {
		this.subPackageUnits1 = subPackageUnits1;
	}


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


	public String getSubPackageUnits2() {
		return subPackageUnits2;
	}

	public void setSubPackageUnits2(String subPackageUnits2) {
		this.subPackageUnits2 = subPackageUnits2;
	}


	public String getSubPackageSpec2() {
		return subPackageSpec2;
	}

	public void setSubPackageSpec2(String subPackageSpec2) {
		this.subPackageSpec2 = subPackageSpec2;
	}


	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}


	public Double getInventory() {
		return inventory;
	}

	public void setInventory(Double inventory) {
		this.inventory = inventory;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public Double getStock() {
        return stock;
    }

    public void setStock(Double stock) {
        this.stock = stock;
    }
}