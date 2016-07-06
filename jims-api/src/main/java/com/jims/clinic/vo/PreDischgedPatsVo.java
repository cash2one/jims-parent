package com.jims.clinic.vo;

import com.jims.common.persistence.DataEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by qinlongxin on 2016/6/3.
 */
public class PreDischgedPatsVo extends DataEntity<PreDischgedPatsVo> {
    private Date dischargeDateExpcted;//预出院时间
    private String name;//患者姓名
    private String sex;//患者性别
    private String diagnosis;//主要诊断
    private Date admissionDateTime;//入院日期及时间
    private Date createDateTime;//做出预计的时间
    private String patientId;//患者id
    private String orderNo;//医嘱序号
    private String visitId;//住院标识
    private String bedLabel;//床标号
    private String bedNo;//床号
    private String wardCode;//所在病房代码
    private String deptName;//科室名称
    private String deptCode;//科室名称
    private String dischargeDispositionName;//出院方式
    private String doctor;//医生
    private List<PreDischgedPatsVo> list=new ArrayList<PreDischgedPatsVo>();
    private String orgId;//机构id
    private String hospitalId;//住院表id
    private String orderText;
    private String orderCode ;

    public String getOrderText() {
        return orderText;
    }

    public void setOrderText(String orderText) {
        this.orderText = orderText;
    }

    public PreDischgedPatsVo() {

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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getAdmissionDateTime() {
        return admissionDateTime;
    }

    public void setAdmissionDateTime(Date admissionDateTime) {
        this.admissionDateTime = admissionDateTime;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public String getBedLabel() {
        return bedLabel;
    }

    public void setBedLabel(String bedLabel) {
        this.bedLabel = bedLabel;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDischargeDispositionName() {
        return dischargeDispositionName;
    }

    public void setDischargeDispositionName(String dischargeDispositionName) {
        this.dischargeDispositionName = dischargeDispositionName;
    }

    public List<PreDischgedPatsVo> getList() {
        return list;
    }

    public void setList(List<PreDischgedPatsVo> list) {
        this.list = list;
    }
    public Date getDischargeDateExpcted() {
        return dischargeDateExpcted;
    }

    public void setDischargeDateExpcted(Date dischargeDateExpcted) {
        this.dischargeDateExpcted = dischargeDateExpcted;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

}
