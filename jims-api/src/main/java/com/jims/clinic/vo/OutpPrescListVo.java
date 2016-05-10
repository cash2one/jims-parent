package com.jims.clinic.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by CTQ on 2016/5/10.
 * 处方记录明细列表Vo
 */
public class OutpPrescListVo implements Serializable {
    private Date visitDate;		// 就诊日期
    private Integer visitNo;		// 就诊序号
    private String serialNo;		// 流水号
    private Integer prescNo;		// 处方序号
    private Integer itemNo;		// 项目序号
    private String itemClass;		// 项目类别
    private String drugCode;		// 药名编码
    private String drugName;		// 药品名称
    private String drugSpec;		// 药品规格
    private String firmId;		// 厂家标识
    private String units;		// 单位
    private Double amount;		// 数量
    private Double dosage;		// 一次用量
    private String dosageUnits;		// 用量单位
    private String administration;		// 用药途径
    private String frequency;		// 频次
    private Integer providedIndicator;		// 自备标记
    private Double costs;		// 计价金额
    private Double charges;		// 实收费用
    private Integer chargeIndicator;		// 收费标记
    private String dispensary;		// 摆药药局
    private Integer orderNo;		// 医嘱组别
    private Integer subOrderNo;		// 子医嘱组别
    private String freqDetail;		// 执行时间详细描述
    private Integer getdrugFlag;		// 取药标志
    private String prescAttr;		// 处方属性
    private Integer abidance;		// abidance
    private String skinFlag;		// skin_flag
    private Integer prescPsno;		// presc_psno
    private String skinResult;		// skin_result

    private String  id;
    private Integer  parentId;

    private String delIndicator;
    private String toxiProperty;
    private Integer drugIndicator;
    private String officialCatalog;
    private Float doscPerUnit;
    private Float amountPerPackage;
    private Integer freqCounter;
    private Integer freqInterval;
    private String freqIntervalUnit;
    private Integer chkAbidance;
    private Integer virtualPrescno;
    private Integer nwarn;

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Integer getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(Integer visitNo) {
        this.visitNo = visitNo;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getPrescNo() {
        return prescNo;
    }

    public void setPrescNo(Integer prescNo) {
        this.prescNo = prescNo;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
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

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getDosage() {
        return dosage;
    }

    public void setDosage(Double dosage) {
        this.dosage = dosage;
    }

    public String getDosageUnits() {
        return dosageUnits;
    }

    public void setDosageUnits(String dosageUnits) {
        this.dosageUnits = dosageUnits;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Integer getProvidedIndicator() {
        return providedIndicator;
    }

    public void setProvidedIndicator(Integer providedIndicator) {
        this.providedIndicator = providedIndicator;
    }

    public Double getCosts() {
        return costs;
    }

    public void setCosts(Double costs) {
        this.costs = costs;
    }

    public Double getCharges() {
        return charges;
    }

    public void setCharges(Double charges) {
        this.charges = charges;
    }

    public Integer getChargeIndicator() {
        return chargeIndicator;
    }

    public void setChargeIndicator(Integer chargeIndicator) {
        this.chargeIndicator = chargeIndicator;
    }

    public String getDispensary() {
        return dispensary;
    }

    public void setDispensary(String dispensary) {
        this.dispensary = dispensary;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getSubOrderNo() {
        return subOrderNo;
    }

    public void setSubOrderNo(Integer subOrderNo) {
        this.subOrderNo = subOrderNo;
    }

    public String getFreqDetail() {
        return freqDetail;
    }

    public void setFreqDetail(String freqDetail) {
        this.freqDetail = freqDetail;
    }

    public Integer getGetdrugFlag() {
        return getdrugFlag;
    }

    public void setGetdrugFlag(Integer getdrugFlag) {
        this.getdrugFlag = getdrugFlag;
    }

    public String getPrescAttr() {
        return prescAttr;
    }

    public void setPrescAttr(String prescAttr) {
        this.prescAttr = prescAttr;
    }

    public Integer getAbidance() {
        return abidance;
    }

    public void setAbidance(Integer abidance) {
        this.abidance = abidance;
    }

    public String getSkinFlag() {
        return skinFlag;
    }

    public void setSkinFlag(String skinFlag) {
        this.skinFlag = skinFlag;
    }

    public Integer getPrescPsno() {
        return prescPsno;
    }

    public void setPrescPsno(Integer prescPsno) {
        this.prescPsno = prescPsno;
    }

    public String getSkinResult() {
        return skinResult;
    }

    public void setSkinResult(String skinResult) {
        this.skinResult = skinResult;
    }

    public String getDelIndicator() {
        return delIndicator;
    }

    public void setDelIndicator(String delIndicator) {
        this.delIndicator = delIndicator;
    }

    public String getToxiProperty() {
        return toxiProperty;
    }

    public void setToxiProperty(String toxiProperty) {
        this.toxiProperty = toxiProperty;
    }

    public Integer getDrugIndicator() {
        return drugIndicator;
    }

    public void setDrugIndicator(Integer drugIndicator) {
        this.drugIndicator = drugIndicator;
    }

    public String getOfficialCatalog() {
        return officialCatalog;
    }

    public void setOfficialCatalog(String officialCatalog) {
        this.officialCatalog = officialCatalog;
    }

    public Float getDoscPerUnit() {
        return doscPerUnit;
    }

    public void setDoscPerUnit(Float doscPerUnit) {
        this.doscPerUnit = doscPerUnit;
    }

    public Float getAmountPerPackage() {
        return amountPerPackage;
    }

    public void setAmountPerPackage(Float amountPerPackage) {
        this.amountPerPackage = amountPerPackage;
    }

    public Integer getFreqCounter() {
        return freqCounter;
    }

    public void setFreqCounter(Integer freqCounter) {
        this.freqCounter = freqCounter;
    }

    public Integer getFreqInterval() {
        return freqInterval;
    }

    public void setFreqInterval(Integer freqInterval) {
        this.freqInterval = freqInterval;
    }

    public String getFreqIntervalUnit() {
        return freqIntervalUnit;
    }

    public void setFreqIntervalUnit(String freqIntervalUnit) {
        this.freqIntervalUnit = freqIntervalUnit;
    }

    public Integer getChkAbidance() {
        return chkAbidance;
    }

    public void setChkAbidance(Integer chkAbidance) {
        this.chkAbidance = chkAbidance;
    }

    public Integer getVirtualPrescno() {
        return virtualPrescno;
    }

    public void setVirtualPrescno(Integer virtualPrescno) {
        this.virtualPrescno = virtualPrescno;
    }

    public Integer getNwarn() {
        return nwarn;
    }

    public void setNwarn(Integer nwarn) {
        this.nwarn = nwarn;
    }


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
