package com.jims.phstock.vo;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 病人摆药记录表
 * Created by heren on 2016/6/29.
 */
public class PatDrugDisp implements Serializable {

    private String personId ;
    private String idNo ;//身份证号
    private String patientId  ;//就诊号
    private int visitId ;//住院次数
    private Date preDischargeDateTime ;//预出院时间
    private String deptId ;//住院科室
    private String wardId ;//住院护理单元
    private String bedNo ;//床位号
    private String orgId ;//所属组织机构
    private List<OrdersDispInfo> ordersDispInfos  ;//摆药医嘱

    public PatDrugDisp() {
    }

    public PatDrugDisp(String personId, String idNo, String patientId, int visitId, Date preDischargeDateTime, String deptId, String wardId, String bedNo, String orgId, List<OrdersDispInfo> ordersDispInfos) {
        this.personId = personId;
        this.idNo = idNo;
        this.patientId = patientId;
        this.visitId = visitId;
        this.preDischargeDateTime = preDischargeDateTime;
        this.deptId = deptId;
        this.wardId = wardId;
        this.bedNo = bedNo;
        this.orgId = orgId;
        this.ordersDispInfos = ordersDispInfos;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
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

    public Date getPreDischargeDateTime() {
        return preDischargeDateTime;
    }

    public void setPreDischargeDateTime(Date preDischargeDateTime) {
        this.preDischargeDateTime = preDischargeDateTime;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public List<OrdersDispInfo> getOrdersDispInfos() {
        return ordersDispInfos;
    }

    public void setOrdersDispInfos(List<OrdersDispInfo> ordersDispInfos) {
        this.ordersDispInfos = ordersDispInfos;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
