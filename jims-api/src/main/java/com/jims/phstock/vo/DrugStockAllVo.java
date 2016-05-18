package com.jims.phstock.vo;

import java.io.Serializable;

/**
 *
 * 全院库存量查询
 * Created by ztq on 2016/5/16.
 */
public class DrugStockAllVo implements Serializable {

    private String storage ;//库存单位
    private String drugCode ;//药品代码
    private String drugSpec ;//要批规格
    private String storageName ;//库存单位名称
    private String drugName  ;//药品名称（药品名称+规格+"（厂商简称）"）
    private String packageSpec ;//包装规格
    private String packageUnit ;//包装单位
    private String firmId ;//厂商简称
    private Double quantity ;//当前库存量
    private double retailMoney ;//当前零售价总金额
    private String supplier ;//供应商
    private Double purchasePrice ;//进货价
    private Double purchaseMoney ;//进货总计额
    private String documentNo ;//单据号

    public DrugStockAllVo(String storage, String drugCode, String drugSpec, String storageName, String drugName, String packageSpec, String packageUnit, String firmId, Double quantity, double retailMoney, String supplier, Double purchasePrice, Double purchaseMoney, String documentNo) {
        this.storage = storage;
        this.drugCode = drugCode;
        this.drugSpec = drugSpec;
        this.storageName = storageName;
        this.drugName = drugName;
        this.packageSpec = packageSpec;
        this.packageUnit = packageUnit;
        this.firmId = firmId;
        this.quantity = quantity;
        this.retailMoney = retailMoney;
        this.supplier = supplier;
        this.purchasePrice = purchasePrice;
        this.purchaseMoney = purchaseMoney;
        this.documentNo = documentNo;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
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

    public String getStorageName() {
        return storageName;
    }

    public void setStorageName(String storageName) {
        this.storageName = storageName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getPackageSpec() {
        return packageSpec;
    }

    public void setPackageSpec(String packageSpec) {
        this.packageSpec = packageSpec;
    }

    public String getPackageUnit() {
        return packageUnit;
    }

    public void setPackageUnit(String packageUnit) {
        this.packageUnit = packageUnit;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public double getRetailMoney() {
        return retailMoney;
    }

    public void setRetailMoney(double retailMoney) {
        this.retailMoney = retailMoney;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getPurchaseMoney() {
        return purchaseMoney;
    }

    public void setPurchaseMoney(Double purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DrugStockAllVo{");
        sb.append("storage='").append(storage).append('\'');
        sb.append(", drugCode='").append(drugCode).append('\'');
        sb.append(", drugSpec='").append(drugSpec).append('\'');
        sb.append(", storageName='").append(storageName).append('\'');
        sb.append(", drugName='").append(drugName).append('\'');
        sb.append(", packageSpec='").append(packageSpec).append('\'');
        sb.append(", packageUnit='").append(packageUnit).append('\'');
        sb.append(", firmId='").append(firmId).append('\'');
        sb.append(", quantity=").append(quantity);
        sb.append(", retailMoney=").append(retailMoney);
        sb.append(", supplier='").append(supplier).append('\'');
        sb.append(", purchasePrice=").append(purchasePrice);
        sb.append(", purchaseMoney=").append(purchaseMoney);
        sb.append(", documentNo='").append(documentNo).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
