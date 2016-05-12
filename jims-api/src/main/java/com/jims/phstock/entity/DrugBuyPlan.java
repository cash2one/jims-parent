package com.jims.phstock.entity;

import java.util.Date;

import com.jims.common.persistence.DataEntity;

/**
 * 药品采购计划Entity
 * @author lgx
 * @version 2016-05-11
 */
public class DrugBuyPlan extends DataEntity<DrugBuyPlan> {
	
	private static final long serialVersionUID = 1L;
	private String buyId;		// 采购单据号,当前日期201605110001
	private Integer buyNo;		// 采购单序号
	private String drugCode;		// 药品代码
	private String drugName;		// 药品名称
	private String drugSpec;		// 规格
	private String units;		// 单位
	private String drugForm;		// 剂型
	private String toxiProperty;		// 毒理分类
	private Double dosePerUnit;		// 最小单位剂量
	private String doseUnits;		// 剂量单位
	private Integer drugIndicator;		// 药品类别标志
	private String inputCode;		// 输入码
	private Double wantNumber;		// 计划数量
	private String storer;		// 仓管员
	private Double stockNumber;		// 采购数量
	private String stockSupplier;		// 采购供应商
	private String buyer;		// 采购员
	private Double checkNumber;		// 审核数量
	private String checkSupplier;		// 审核供应商
	private String checker;		// 审核员
	private Integer flag;		// 执行标志
	private String packSpec;		// 包装规格
	private String packUnit;		// 包装单位
	private String firmId;		// 厂商
	private Double purchasePrice;		// 进货价
	private String storage;		// 库存管理单位
	private Double stockquantityRef;		// stockquantity_ref
	private Double exportquantityRef;		// exportquantity_ref
	private Double executedNumber;		// 执行数量
	private String importDocument;		// 入库单号
	private Date executedDate;		// 执行日期
	private String orgId;		// 所属结构

    private String count;  // 总金额
	
	public DrugBuyPlan() {
		super();
	}

	public DrugBuyPlan(String id){
		super(id);
	}


	public String getBuyId() {
		return buyId;
	}

	public void setBuyId(String buyId) {
		this.buyId = buyId;
	}


	public Integer getBuyNo() {
		return buyNo;
	}

	public void setBuyNo(Integer buyNo) {
		this.buyNo = buyNo;
	}


	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}


	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
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


	public String getDrugForm() {
		return drugForm;
	}

	public void setDrugForm(String drugForm) {
		this.drugForm = drugForm;
	}


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


	public String getDoseUnits() {
		return doseUnits;
	}

	public void setDoseUnits(String doseUnits) {
		this.doseUnits = doseUnits;
	}


	public Integer getDrugIndicator() {
		return drugIndicator;
	}

	public void setDrugIndicator(Integer drugIndicator) {
		this.drugIndicator = drugIndicator;
	}


	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}


	public Double getWantNumber() {
		return wantNumber;
	}

	public void setWantNumber(Double wantNumber) {
		this.wantNumber = wantNumber;
	}


	public String getStorer() {
		return storer;
	}

	public void setStorer(String storer) {
		this.storer = storer;
	}


	public Double getStockNumber() {
		return stockNumber;
	}

	public void setStockNumber(Double stockNumber) {
		this.stockNumber = stockNumber;
	}


	public String getStockSupplier() {
		return stockSupplier;
	}

	public void setStockSupplier(String stockSupplier) {
		this.stockSupplier = stockSupplier;
	}


	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}


	public Double getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(Double checkNumber) {
		this.checkNumber = checkNumber;
	}


	public String getCheckSupplier() {
		return checkSupplier;
	}

	public void setCheckSupplier(String checkSupplier) {
		this.checkSupplier = checkSupplier;
	}


	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}


	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}


	public String getPackSpec() {
		return packSpec;
	}

	public void setPackSpec(String packSpec) {
		this.packSpec = packSpec;
	}


	public String getPackUnit() {
		return packUnit;
	}

	public void setPackUnit(String packUnit) {
		this.packUnit = packUnit;
	}


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


	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}


	public Double getStockquantityRef() {
		return stockquantityRef;
	}

	public void setStockquantityRef(Double stockquantityRef) {
		this.stockquantityRef = stockquantityRef;
	}


	public Double getExportquantityRef() {
		return exportquantityRef;
	}

	public void setExportquantityRef(Double exportquantityRef) {
		this.exportquantityRef = exportquantityRef;
	}


	public Double getExecutedNumber() {
		return executedNumber;
	}

	public void setExecutedNumber(Double executedNumber) {
		this.executedNumber = executedNumber;
	}


	public String getImportDocument() {
		return importDocument;
	}

	public void setImportDocument(String importDocument) {
		this.importDocument = importDocument;
	}


	public Date getExecutedDate() {
		return executedDate;
	}

	public void setExecutedDate(Date executedDate) {
		this.executedDate = executedDate;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}