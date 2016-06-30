package com.jims.phstock.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 病人基本信息VO
 * Created by heren on 2016/6/29.
 */
public class PatientBaseVo implements Serializable {

    private String patientId ;
    private int visitId ;
    private String orgId ;
    private String deptCode ;
    private String wardCode ;
    private String name ;
    private String idNo ;
    private Date preDischargeDate ;

    public PatientBaseVo() {
    }

    public PatientBaseVo(String patientId, int visitId, String orgId, String deptCode, String wardCode, String name, String idNo, Date preDischargeDate) {
        this.patientId = patientId;
        this.visitId = visitId;
        this.orgId = orgId;
        this.deptCode = deptCode;
        this.wardCode = wardCode;
        this.name = name;
        this.idNo = idNo;
        this.preDischargeDate = preDischargeDate;
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

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public Date getPreDischargeDate() {
        return preDischargeDate;
    }

    public void setPreDischargeDate(Date preDischargeDate) {
        this.preDischargeDate = preDischargeDate;
    }
}
