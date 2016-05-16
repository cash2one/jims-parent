package com.jims.phstock.vo;

import java.io.Serializable;

/**
 * Created by heren on 2016/5/16.
 */
public class DrugWorkCount implements Serializable {

    private String subStroage ;//库存单位
    private Integer codeNum ;//品种数
    private Integer workTimes ;//频次
    private Double amount ;//金额
    private Integer type ;//1，当前库存；2，入库；3出库

    public DrugWorkCount(String subStroage, Integer codeNum, Integer workTimes, Double amount, Integer type) {
        this.subStroage = subStroage;
        this.codeNum = codeNum;
        this.workTimes = workTimes;
        this.amount = amount;
        this.type = type;
    }

    public String getSubStroage() {
        return subStroage;
    }

    public void setSubStroage(String subStroage) {
        this.subStroage = subStroage;
    }

    public Integer getCodeNum() {
        return codeNum;
    }

    public void setCodeNum(Integer codeNum) {
        this.codeNum = codeNum;
    }

    public Integer getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(Integer workTimes) {
        this.workTimes = workTimes;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
