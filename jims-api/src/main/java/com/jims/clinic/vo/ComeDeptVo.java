package com.jims.clinic.vo;

import java.util.Date;

/**
 * 护理端-患者入科Vo
 * Created by CTQ
 * @DATE 2016-06-06 16:46:22
 */
public class ComeDeptVo {
    private String bedRecId;
    private String patientId;
    private String visitId;
    private String bedNo;
    private String bedLabel;
    private String name;
    private String sex;
    private String chargeType;
    private Double prePaymentAmount;
    private Double amount;
    private Date admissionDateTime;
    private Date admWardDateTime;
    private String deptStayed;
    private String doctorUser;
    private String nursingClass;
    private String superDoctorId;
    private String patientCondition;
    private Double bodyHeight;
    private Double bodyWeight;
    private String parentDoctorId;
    private String diagnosis;
    private Date onsetDate;
    private String deptTransferedTo;
    private String action;

    public String getBedRecId() {
        return bedRecId;
    }

    public void setBedRecId(String bedRecId) {
        this.bedRecId = bedRecId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getBedLabel() {
        return bedLabel;
    }

    public void setBedLabel(String bedLabel) {
        this.bedLabel = bedLabel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public Double getPrePaymentAmount() {
        return prePaymentAmount;
    }

    public void setPrePaymentAmount(Double prePaymentAmount) {
        this.prePaymentAmount = prePaymentAmount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getAdmissionDateTime() {
        return admissionDateTime;
    }

    public void setAdmissionDateTime(Date admissionDateTime) {
        this.admissionDateTime = admissionDateTime;
    }

    public Date getAdmWardDateTime() {
        return admWardDateTime;
    }

    public void setAdmWardDateTime(Date admWardDateTime) {
        this.admWardDateTime = admWardDateTime;
    }

    public String getDeptStayed() {
        return deptStayed;
    }

    public void setDeptStayed(String deptStayed) {
        this.deptStayed = deptStayed;
    }

    public String getDoctorUser() {
        return doctorUser;
    }

    public void setDoctorUser(String doctorUser) {
        this.doctorUser = doctorUser;
    }

    public String getNursingClass() {
        return nursingClass;
    }

    public void setNursingClass(String nursingClass) {
        this.nursingClass = nursingClass;
    }

    public String getSuperDoctorId() {
        return superDoctorId;
    }

    public void setSuperDoctorId(String superDoctorId) {
        this.superDoctorId = superDoctorId;
    }

    public Double getBodyHeight() {
        return bodyHeight;
    }

    public void setBodyHeight(Double bodyHeight) {
        this.bodyHeight = bodyHeight;
    }

    public Double getBodyWeight() {
        return bodyWeight;
    }

    public void setBodyWeight(Double bodyWeight) {
        this.bodyWeight = bodyWeight;
    }

    public String getParentDoctorId() {
        return parentDoctorId;
    }

    public void setParentDoctorId(String parentDoctorId) {
        this.parentDoctorId = parentDoctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getOnsetDate() {
        return onsetDate;
    }

    public void setOnsetDate(Date onsetDate) {
        this.onsetDate = onsetDate;
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition;
    }

    public String getDeptTransferedTo() {
        return deptTransferedTo;
    }

    public void setDeptTransferedTo(String deptTransferedTo) {
        this.deptTransferedTo = deptTransferedTo;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
