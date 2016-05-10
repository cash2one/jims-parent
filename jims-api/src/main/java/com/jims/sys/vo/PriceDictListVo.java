package com.jims.sys.vo;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/26.
 */
public class PriceDictListVo implements Serializable {
    private String itemClass;        // 项目类别
    private String itemCode;        // 项目代码
    private String itemName;        // 项目名称
    private String itemSpec;        // 项目规格
    private String units;        // 计价单位
    private Double price;        // 基本价格
    private Double preferPrice;        // 优惠价格
    private Double foreignerPrice;        // 外宾价格
    private String performedBy;        // 执行科室
    private Integer feeTypeMask;        // 是否自费
    private String classOnInpRcpt;        // 住院收据分类
    private String classOnOutpRcpt;        // 门诊收据分类
    private String classOnReckoning;        // 核算科目
    private String subjCode;        // 会计科目
    private String classOnMr;        // 病案首页分类
    private String memo;        // 备注信息
    private String startDate;        // 起用日期
    private String inputCode; //拼音码
    private String MaterialCode; //物价码

    public String getMaterialCode() {
        return MaterialCode;
    }

    public void setMaterialCode(String materialCode) {
        MaterialCode = materialCode;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPreferPrice() {
        return preferPrice;
    }

    public void setPreferPrice(Double preferPrice) {
        this.preferPrice = preferPrice;
    }

    public Double getForeignerPrice() {
        return foreignerPrice;
    }

    public void setForeignerPrice(Double foreignerPrice) {
        this.foreignerPrice = foreignerPrice;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public Integer getFeeTypeMask() {
        return feeTypeMask;
    }

    public void setFeeTypeMask(Integer feeTypeMask) {
        this.feeTypeMask = feeTypeMask;
    }

    public String getClassOnInpRcpt() {
        return classOnInpRcpt;
    }

    public void setClassOnInpRcpt(String classOnInpRcpt) {
        this.classOnInpRcpt = classOnInpRcpt;
    }

    public String getClassOnOutpRcpt() {
        return classOnOutpRcpt;
    }

    public void setClassOnOutpRcpt(String classOnOutpRcpt) {
        this.classOnOutpRcpt = classOnOutpRcpt;
    }

    public String getClassOnReckoning() {
        return classOnReckoning;
    }

    public void setClassOnReckoning(String classOnReckoning) {
        this.classOnReckoning = classOnReckoning;
    }

    public String getSubjCode() {
        return subjCode;
    }

    public void setSubjCode(String subjCode) {
        this.subjCode = subjCode;
    }

    public String getClassOnMr() {
        return classOnMr;
    }

    public void setClassOnMr(String classOnMr) {
        this.classOnMr = classOnMr;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }
}
