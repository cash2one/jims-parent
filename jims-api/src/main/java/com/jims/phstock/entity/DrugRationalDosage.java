package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 药品用量信息Entity
 * Created by fyg on 2016/7/12.
 */
public class DrugRationalDosage extends DataEntity<DrugRationalDosage> {

    private static final long serialVersionUID = 1L;
    private String drugCode;                //药品代码
    private String drugSpec;                //规格
    private Double dosePerUnit;             //最小单位剂量
    private String doseUnits;               //剂量单位
    private Double maxDosage;               //单次用量
    private Double maxPrescDosage;          //单处方最大开药量
    private Double maxOutpAbidance;         //处方最大用药天数
    private String administrationCode;          //给药途径和方法
    private String freqDesc;                 //执行频率描述
    private Double freqCounter;             //频率次数
    private Double freqInterval;            //频率间隔
    private String freqIntervalUnits;        //频率间隔单位

    private String drugName;                //药品名称

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

    public String getDrugSpec() {
        return drugSpec;
    }

    public void setDrugSpec(String drugSpec) {
        this.drugSpec = drugSpec;
    }

    public String getDoseUnits() {
        return doseUnits;
    }

    public void setDoseUnits(String doseUnits) {
        this.doseUnits = doseUnits;
    }

    public String getAdministrationCode() {
        return administrationCode;
    }

    public void setAdministrationCode(String administrationCode) {
        this.administrationCode = administrationCode;
    }

    public String getFreqDesc() {
        return freqDesc;
    }

    public void setFreqDesc(String freqDesc) {
        this.freqDesc = freqDesc;
    }

    public String getFreqIntervalUnits() {
        return freqIntervalUnits;
    }

    public void setFreqIntervalUnits(String freqIntervalUnits) {
        this.freqIntervalUnits = freqIntervalUnits;
    }

    public Double getDosePerUnit() {
        return dosePerUnit;
    }

    public void setDosePerUnit(Double dosePerUnit) {
        this.dosePerUnit = dosePerUnit;
    }

    public Double getMaxDosage() {
        return maxDosage;
    }

    public void setMaxDosage(Double maxDosage) {
        this.maxDosage = maxDosage;
    }

    public Double getMaxPrescDosage() {
        return maxPrescDosage;
    }

    public void setMaxPrescDosage(Double maxPrescDosage) {
        this.maxPrescDosage = maxPrescDosage;
    }

    public Double getMaxOutpAbidance() {
        return maxOutpAbidance;
    }

    public void setMaxOutpAbidance(Double maxOutpAbidance) {
        this.maxOutpAbidance = maxOutpAbidance;
    }

    public Double getFreqCounter() {
        return freqCounter;
    }

    public void setFreqCounter(Double freqCounter) {
        this.freqCounter = freqCounter;
    }

    public Double getFreqInterval() {
        return freqInterval;
    }

    public void setFreqInterval(Double freqInterval) {
        this.freqInterval = freqInterval;
    }
}
