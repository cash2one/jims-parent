package com.jims.asepsis.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
* 送物领物Entity
* @author lgx
* @version 2016-06-27
*/
public class AsepsisSendRec extends DataEntity<AsepsisSendRec> {

    private static final long serialVersionUID = 1L;
    private String documentNo;  // 单据号
    private Double itemNo;  // 序号
    private String fromDept;  // 送物科室
    private Date sendDate;  // 送物日期
    private String itemCode;  // 代码
    private String itemName;  // 项目名称
    private String itemSpec;  // 规格
    private Double sendAmount;  // 送物数量
    private Double getAmount;  // 已领数量
    private Date getDate;  // 领物日期
    private String getFlag;  // 领物标记,0-新开申请,1-申请确认(未领取),2-申请确认已消毒， 3-部分领取, 4-全部取完
    private String units;  // 单位
    private String operator;  // 操作员
    private String sender;  // 送物人
    private String memos;  // 备注
    private Double antiFee;  // 消毒费
    private Double antiFeeSum;  // 消毒费合计
    private Double nobackFee;  // 辅料费
    private Date reqDate;  // 申请时间
    private String reqOperator;  // 申请人
    private String orgId;  // 所属机构ID

    private String stock;

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public AsepsisSendRec() {
        super();
    }

    public AsepsisSendRec(String id) {
        super(id);
    }

    public  String getDocumentNo() {
        return this.documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public  Double getItemNo() {
        return this.itemNo;
    }

    public void setItemNo(Double itemNo) {
        this.itemNo = itemNo;
    }

    public  String getFromDept() {
        return this.fromDept;
    }

    public void setFromDept(String fromDept) {
        this.fromDept = fromDept;
    }

    public  Date getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
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

    public  Double getSendAmount() {
        return this.sendAmount;
    }

    public void setSendAmount(Double sendAmount) {
        this.sendAmount = sendAmount;
    }

    public  Double getGetAmount() {
        return this.getAmount;
    }

    public void setGetAmount(Double getAmount) {
        this.getAmount = getAmount;
    }

    public  Date getGetDate() {
        return this.getDate;
    }

    public void setGetDate(Date getDate) {
        this.getDate = getDate;
    }

    public  String getGetFlag() {
        return this.getFlag;
    }

    public void setGetFlag(String getFlag) {
        this.getFlag = getFlag;
    }

    public  String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public  String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public  String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public  String getMemos() {
        return this.memos;
    }

    public void setMemos(String memos) {
        this.memos = memos;
    }

    public  Double getAntiFee() {
        return this.antiFee;
    }

    public void setAntiFee(Double antiFee) {
        this.antiFee = antiFee;
    }

    public  Double getAntiFeeSum() {
        return this.antiFeeSum;
    }

    public void setAntiFeeSum(Double antiFeeSum) {
        this.antiFeeSum = antiFeeSum;
    }

    public  Double getNobackFee() {
        return this.nobackFee;
    }

    public void setNobackFee(Double nobackFee) {
        this.nobackFee = nobackFee;
    }

    public  Date getReqDate() {
        return this.reqDate;
    }

    public void setReqDate(Date reqDate) {
        this.reqDate = reqDate;
    }

    public  String getReqOperator() {
        return this.reqOperator;
    }

    public void setReqOperator(String reqOperator) {
        this.reqOperator = reqOperator;
    }

    public  String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


}