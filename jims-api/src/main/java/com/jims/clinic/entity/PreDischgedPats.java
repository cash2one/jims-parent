package com.jims.clinic.entity;


import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import com.jims.orders.entity.Orders;
import com.jims.nurse.entity.BedRec;
import com.jims.patient.entity.PatMasterIndex;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 出院通知单Entity
 *
 * @author qinlongxin
 * @version 2016-06-02
 */
public class PreDischgedPats extends DataEntity<PreDischgedPats> {

    private static final long serialVersionUID = 1L;
    private String patientId;        // 病人标识号
    private String orderNo;        // 医嘱表主键
    private Date dischargeDateExpcted;        // 预计出院日期
    private Date createDateTime;        // 做出预计的时间
    private String dischargeDispositionName;        // 出院方式
    private String hospitalId;
    private String orgId;//机构id
    private String ordersId;
    private String visitId;
    public PreDischgedPats() {
        super();
    }

    public PreDischgedPats(String id) {
        super(id);
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(String hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getDischargeDateExpcted() {
        return dischargeDateExpcted;
    }

    public String getVisitId() {
        return visitId;
    }

    public void setVisitId(String visitId) {
        this.visitId = visitId;
    }

    public void setDischargeDateExpcted(Date dischargeDateExpcted) {
        this.dischargeDateExpcted = dischargeDateExpcted;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public String getDischargeDispositionName() {
        return dischargeDispositionName;
    }

    public void setDischargeDispositionName(String dischargeDispositionName) {
        this.dischargeDispositionName = dischargeDispositionName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(String ordersId) {
        this.ordersId = ordersId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrdersNo(String ordersId) {
        this.orderNo = ordersId;
    }



}