package com.jims.clinic.entity;


import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import com.jims.exam.entity.Orders;
import com.jims.nurse.entity.BedRec;
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
    private Date dischargeDateExpcted;        // 预计出院日期
    private Date createDateTime;        // 做出预计的时间
    private Orders orders;        // 医嘱序号
    private String dischargeDispositionName;        // 出院方式
    private PatsInHospital patsInHospital; //在院病人记录
    private BedRec bedRec; //床位记录

    public PreDischgedPats() {
        super();
    }

    public PreDischgedPats(String id) {
        super(id);
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

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public PatsInHospital getPatsInHospital() {
        return patsInHospital;
    }

    public void setPatsInHospital(PatsInHospital patsInHospital) {
        this.patsInHospital = patsInHospital;
    }

    public BedRec getBedRec() {
        return bedRec;
    }

    public void setBedRec(BedRec bedRec) {
        this.bedRec = bedRec;
    }

}