package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 医嘱执行缺省时间表Entity
 * @author lgx
 * @version 2016-06-21
 */
public class PerformDefaultSchedule extends DataEntity<PerformDefaultSchedule> {

    private static final long serialVersionUID = 1L;
    private String freqDesc ;        //外键与频次字典表
    private String administration;   // 途径（外键）
    private String defaultSchedule;  //默认执行时间

    private String freqDescName;     // 频次描述
    private String administrationName; // 途径描述

    public PerformDefaultSchedule() {
        super();
    }

    public PerformDefaultSchedule(String id){
        super(id);
    }

    public String getFreqDesc() {
        return freqDesc;
    }

    public void setFreqDesc(String freqDesc) {
        this.freqDesc = freqDesc;
    }

    public String getAdministration() {
        return administration;
    }

    public void setAdministration(String administration) {
        this.administration = administration;
    }

    public String getDefaultSchedule() {
        return defaultSchedule;
    }

    public void setDefaultSchedule(String defaultSchedule) {
        this.defaultSchedule = defaultSchedule;
    }

    public String getFreqDescName() {
        return freqDescName;
    }

    public void setFreqDescName(String freqDescName) {
        this.freqDescName = freqDescName;
    }

    public String getAdministrationName() {
        return administrationName;
    }

    public void setAdministrationName(String administrationName) {
        this.administrationName = administrationName;
    }
}