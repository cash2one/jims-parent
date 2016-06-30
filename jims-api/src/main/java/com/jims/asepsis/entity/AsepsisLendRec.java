package com.jims.asepsis.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
* 借物还物Entity
* @author lgx
* @version 2016-06-27
*/
public class AsepsisLendRec extends DataEntity<AsepsisLendRec> {

    private static final long serialVersionUID = 1L;
    private String documentNo;  // 单据号
    private Double itemNo;  // 序号
    private String toDept;  // 借物科室
    private Date lendDate;  // 借物日期
    private String itemCode;  // 项目代码
    private String itemName;  // 项目名称
    private String itemSpec;  // 规格
    private Double lendAmount;  // 借物数量
    private String units;  // 单位
    private Double returnAmount;  // 已还数量
    private Date returnDate;  // 还物日期
    private String returnMan;  // 还物人
    private String returnFlag;  // 返还标志:1-未还，2-部分还，3-全部还,4-包对换
    private String operator;  // 操作员(同时用于记录对换(回收)的操作员)
    private String lender;  // 借物人
    private String memos;  // 备注
    private Double antiFee;  // 消毒费
    private Double antiFeeSum;  // 消毒费合计
    private Date antiDate;  // 消毒日期
    private String expDocumentNo;  // 借出单号
    private Double nobackFee;  // 辅料费
    private Date reqDate;  // 申请时间
    private String reqOperator;  // 申请人
    private String operator2;  // 用于记录对换(发放)的操作员
    private String orgId;  // 所属机构ID

    private Date lendDateStart;
    private Date lendDateEnd;
    private Integer stock;
    private String toDeptName;

    public AsepsisLendRec() {
        super();
    }

    public AsepsisLendRec(String id) {
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

    public  String getToDept() {
        return this.toDept;
    }

    public void setToDept(String toDept) {
        this.toDept = toDept;
    }

    public  Date getLendDate() {
        return this.lendDate;
    }

    public void setLendDate(Date lendDate) {
        this.lendDate = lendDate;
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

    public  Double getLendAmount() {
        return this.lendAmount;
    }

    public void setLendAmount(Double lendAmount) {
        this.lendAmount = lendAmount;
    }

    public  String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public  Double getReturnAmount() {
        return this.returnAmount;
    }

    public void setReturnAmount(Double returnAmount) {
        this.returnAmount = returnAmount;
    }

    public  Date getReturnDate() {
        return this.returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public  String getReturnFlag() {
        return this.returnFlag;
    }

    public void setReturnFlag(String returnFlag) {
        this.returnFlag = returnFlag;
    }

    public  String getOperator() {
        return this.operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public  String getLender() {
        return this.lender;
    }

    public void setLender(String lender) {
        this.lender = lender;
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

    public  Date getAntiDate() {
        return this.antiDate;
    }

    public void setAntiDate(Date antiDate) {
        this.antiDate = antiDate;
    }

    public  String getExpDocumentNo() {
        return this.expDocumentNo;
    }

    public void setExpDocumentNo(String expDocumentNo) {
        this.expDocumentNo = expDocumentNo;
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

    public  String getOperator2() {
        return this.operator2;
    }

    public void setOperator2(String operator2) {
        this.operator2 = operator2;
    }

    public  String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public Date getLendDateStart() {
        return lendDateStart;
    }

    public void setLendDateStart(Date lendDateStart) {
        this.lendDateStart = lendDateStart;
    }

    public Date getLendDateEnd() {
        return lendDateEnd;
    }

    public void setLendDateEnd(Date lendDateEnd) {
        this.lendDateEnd = lendDateEnd;
    }

    public String getReturnMan() {
        return returnMan;
    }

    public void setReturnMan(String returnMan) {
        this.returnMan = returnMan;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getToDeptName() {
        return toDeptName;
    }

    public void setToDeptName(String toDeptName) {
        this.toDeptName = toDeptName;
    }
}