package com.jims.asepsis.entity;

import com.jims.common.persistence.DataEntity;


/**
* 包内物品管理Entity
* @author yangruidong
* @version 2016-06-27
*/
public class AsepsisDetailDict extends DataEntity<AsepsisDetailDict> {

    private static final long serialVersionUID = 1L;
    private String asepsisCode;  // 包代码
    private String itemCode;  // 明细代码
    private String itemName;  // 明细名称
    private String itemSpec;  // 明细规格
    private Double amount;  // 数量
    private String units;  // 单位
    private Double itemPrice;  // 单价
    private String storage;  // 一级库房
    private String subStorage;  // 二级库房
    private String memos;  // 备注
    private String firmId;  // 厂家
    private String orgId;  // 所属机构ID

    public AsepsisDetailDict() {
        super();
    }

    public AsepsisDetailDict(String id) {
        super(id);
    }

    public  String getAsepsisCode() {
        return this.asepsisCode;
    }

    public void setAsepsisCode(String asepsisCode) {
        this.asepsisCode = asepsisCode;
    }

    public  String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public  String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public  String getItemSpec() {
        return this.itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public  Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public  String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public  Double getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public  String getStorage() {
        return this.storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public  String getSubStorage() {
        return this.subStorage;
    }

    public void setSubStorage(String subStorage) {
        this.subStorage = subStorage;
    }

    public  String getMemos() {
        return this.memos;
    }

    public void setMemos(String memos) {
        this.memos = memos;
    }

    public  String getFirmId() {
        return this.firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public  String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


}