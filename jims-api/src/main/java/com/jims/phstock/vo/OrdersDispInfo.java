package com.jims.phstock.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 摆药医嘱信息表
 * Created by heren on 2016/6/29.
 */
public class OrdersDispInfo implements Serializable {

    private String patientId;//病人ID
    private int visitId;//住院次数
    private int orderNo;//医嘱号
    private int orderSubNo;//子医嘱号
    private String drugCode;//药品名称
    private String drugSpec;//药品规格
    private String drugUnit;//药品单位
    private String firmId;//供应商ID
    private double amount;//摆药数量
    private String dispTime;//历次摆药的时间以“;"隔开
    private String orgId;
    private Date lastPerformDateTime;//最后一次执行时间
    private double dosage;//最小用量
    private String dosageUnits;//最小用量单位
    private String administration;//使用途径
    private Date startDateTime;//医嘱开始时间
    private Date stopDateTime;//医嘱结束时间
    private String repeatIndicator;//长期，临时医嘱
    private String orderText;//医嘱内容
    private String frequency;//频次描述
    private double freqCounter;//频率次数
    private double freqInterval;//频率间隔
    private String freqIntervalUnit;//频率间隔单位
    private String freqDetail;//执行时间详细描述
    private String performSchedule;//执行时间详细描述
    private String orderStatus;//医嘱状态
    private List<Date> performDates;//具体执行时间


    public OrdersDispInfo() {

    }

    public OrdersDispInfo(String patientId, int visitId, int orderNo, int orderSubNo, String drugCode, String drugSpec, String drugUnit, String firmId, double amount, String dispTime, String orgId, Date lastPerformDateTime, double dosage, String dosageUnits, String administration, Date startDateTime, Date stopDateTime, String repeatIndicator, String orderText, String frequency, double freqCounter, double freqInterval, String freqIntervalUnit, String freqDetail, String performSchedule, String orderStatus, List<Date> performDates) {
        this.patientId = patientId;
        this.visitId = visitId;
        this.orderNo = orderNo;
        this.orderSubNo = orderSubNo;
        this.drugCode = drugCode;
        this.drugSpec = drugSpec;
        this.drugUnit = drugUnit;
        this.firmId = firmId;
        this.amount = amount;
        this.dispTime = dispTime;
        this.orgId = orgId;
        this.lastPerformDateTime = lastPerformDateTime;
        this.dosage = dosage;
        this.dosageUnits = dosageUnits;
        this.administration = administration;
        this.startDateTime = startDateTime;
        this.stopDateTime = stopDateTime;
        this.repeatIndicator = repeatIndicator;
        this.orderText = orderText;
        this.frequency = frequency;
        this.freqCounter = freqCounter;
        this.freqInterval = freqInterval;
        this.freqIntervalUnit = freqIntervalUnit;
        this.freqDetail = freqDetail;
        this.performSchedule = performSchedule;
        this.orderStatus = orderStatus;
        this.performDates = performDates;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
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

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getStopDateTime() {
        return stopDateTime;
    }

    public void setStopDateTime(Date stopDateTime) {
        this.stopDateTime = stopDateTime;
    }

    public String getRepeatIndicator() {
        return repeatIndicator;
    }

    public void setRepeatIndicator(String repeatIndicator) {
        this.repeatIndicator = repeatIndicator;
    }

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public double getFreqCounter() {
        return freqCounter;
    }

    public void setFreqCounter(double freqCounter) {
        this.freqCounter = freqCounter;
    }

    public double getFreqInterval() {
        return freqInterval;
    }

    public void setFreqInterval(double freqInterval) {
        this.freqInterval = freqInterval;
    }

    public String getFreqIntervalUnit() {
        return freqIntervalUnit;
    }

    public void setFreqIntervalUnit(String freqIntervalUnit) {
        this.freqIntervalUnit = freqIntervalUnit;
    }

    public String getFreqDetail() {
        return freqDetail;
    }

    public void setFreqDetail(String freqDetail) {
        this.freqDetail = freqDetail;
    }

    public String getPerformSchedule() {
        return performSchedule;
    }

    public void setPerformSchedule(String performSchedule) {
        this.performSchedule = performSchedule;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public int getOrderSubNo() {
        return orderSubNo;
    }

    public void setOrderSubNo(int orderSubNo) {
        this.orderSubNo = orderSubNo;
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

    public String getDrugUnit() {
        return drugUnit;
    }

    public void setDrugUnit(String drugUnit) {
        this.drugUnit = drugUnit;
    }

    public String getFirmId() {
        return firmId;
    }

    public void setFirmId(String firmId) {
        this.firmId = firmId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDispTime() {
        return dispTime;
    }

    public void setDispTime(String dispTime) {
        this.dispTime = dispTime;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getLastPerformDateTime() {
        return lastPerformDateTime;
    }

    public void setLastPerformDateTime(Date lastPerformDateTime) {
        this.lastPerformDateTime = lastPerformDateTime;
    }

    public List<Date> getPerformDates() {
        return performDates;
    }

    public void setPerformDates(List<Date> performDates) {
        this.performDates = performDates;
    }
}
