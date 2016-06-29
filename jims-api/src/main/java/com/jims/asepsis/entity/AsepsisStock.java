package com.jims.asepsis.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
* 包库存初始化Entity
* @author yangruidong
* @version 2016-06-27
*/
public class AsepsisStock extends DataEntity<AsepsisStock> {

    private static final long serialVersionUID = 1L;
    private String documentNo;  // 供应室还物加库存时该单号为消毒批号，其他科室送物时该单号为送物单号
    private String fromDept;  // 包所属科室
    private String itemCode;  // 代码
    private String itemName;  // 名称
    private String itemSpec;  // 规格
    private Double amount;  // 库存数
    private String units;  // 单位
    private String memos;  // 备注
    private Date antiDate;  // 消毒日期
    private String operator;  // 操作员
    private Date alterDate;  // 修改日期
    private String antiBatchNo;  // 消毒批号
    private Double itemNo;  // 序号
    private String orgId;  // 所属机构ID

    public AsepsisStock() {
        super();
    }

    public AsepsisStock(String id) {
        super(id);
    }

    public  String getDocumentNo() {
        return this.documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public  String getFromDept() {
        return this.fromDept;
    }

    public void setFromDept(String fromDept) {
        this.fromDept = fromDept;
    }

    public  String getItemCode() {
        return this.itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public  String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public  String getItemSpec() {
        return this.itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public  Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public  String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public  String getMemos() {
        return this.memos;
    }

    public void setMemos(String memos) {
        this.memos = memos;
    }

    public  Date getAntiDate() {
        return this.antiDate;
    }

    public void setAntiDate(Date antiDate) {
        this.antiDate = antiDate;
    }

    public  String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public  Date getAlterDate() {
        return this.alterDate;
    }

    public void setAlterDate(Date alterDate) {
        this.alterDate = alterDate;
    }

    public  String getAntiBatchNo() {
        return this.antiBatchNo;
    }

    public void setAntiBatchNo(String antiBatchNo) {
        this.antiBatchNo = antiBatchNo;
    }

    public  Double getItemNo() {
        return this.itemNo;
    }

    public void setItemNo(Double itemNo) {
        this.itemNo = itemNo;
    }

    public  String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


}