package com.jims.phstock.vo;

import java.io.Serializable;

/**
 * 药品盘点封装Vo
 *
 * @author tangxb
 * @version 2016-05-23
 */
public class DrugInventoryCheckVo implements Serializable{
    private String id;
    private String storage;//库房
    private String drugName;//药品名称
    private String drugForm;//药品剂型
    private String drugCode;//药品代码
    private String packageSpec;//药品包装规格
    private String packageUnits;//包装单位
    private String firmId;//厂商
    private String batchNo;//批号
    private String drugSpec;//最小规格
    private String units;//最小单位
    private String subStorage;//子库房
    private String quantity;//账面数
    private String actualQuantity;//实盘数
    private String traderPrice;//批发价格
    private String retailPrice;//零售价
    private String purchasePrice;//进货价
    private String checkYearMonth;//盘点年月
    private String recStatus;//状态0，为暂存；1，终存
    private String location;//货位
    private String changeFlag;//更改库存标志
    private String orgId;//组织机构

    private String profitAndLoss;//盈亏数
    private String quantityAmount;
    private String actualAmount;
    private String profitAndLossAmount;


    public String getQuantityAmount() {
        return quantityAmount;
    }

    public void setQuantityAmount(String quantityAmount) {
        this.quantityAmount = quantityAmount;
    }

    public String getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(String actualAmount) {
        this.actualAmount = actualAmount;
    }

    public String getProfitAndLossAmount() {
        return profitAndLossAmount;
    }

    public void setProfitAndLossAmount(String profitAndLossAmount) {
        this.profitAndLossAmount = profitAndLossAmount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfitAndLoss() {
        return profitAndLoss;
    }

    public void setProfitAndLoss(String profitAndLoss) {
        this.profitAndLoss = profitAndLoss;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(String drugForm) {
        this.drugForm = drugForm;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    public String getPackageSpec() {
        return packageSpec;
    }

    public void setPackageSpec(String packageSpec) {
        this.packageSpec = packageSpec;
    }

    public String getPackageUnits() {
        return packageUnits;
    }

    public void setPackageUnits(String packageUnits) {
        this.packageUnits = packageUnits;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
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

    public String getSubStorage() {
        return subStorage;
    }

    public void setSubStorage(String subStorage) {
        this.subStorage = subStorage;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getActualQuantity() {
        return actualQuantity;
    }

    public void setActualQuantity(String actualQuantity) {
        this.actualQuantity = actualQuantity;
    }

    public String getTraderPrice() {
        return traderPrice;
    }

    public void setTraderPrice(String traderPrice) {
        this.traderPrice = traderPrice;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getCheckYearMonth() {
        return checkYearMonth;
    }

    public void setCheckYearMonth(String checkYearMonth) {
        this.checkYearMonth = checkYearMonth;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getChangeFlag() {
        return changeFlag;
    }

    public void setChangeFlag(String changeFlag) {
        this.changeFlag = changeFlag;
    }
}
