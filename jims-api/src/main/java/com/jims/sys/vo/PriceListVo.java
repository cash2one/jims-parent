package com.jims.sys.vo;

import com.jims.common.persistence.DataEntity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wei on 2016/5/6.
 */
@XmlRootElement
public class PriceListVo extends DataEntity<PriceListVo>  implements Serializable  {


    private static final long serialVersionUID = 1L;
    private String label;//药品类型
    private String itemClass;		// 项目类别
    private String itemCode;		// 项目代码
    private String itemName;		// 项目名称
    private String itemSpec;		// 规格
    private String units;		// 单位
    private Double price;		// 基本价格
    private Double preferPrice;		// 优惠价格
    private Double foreignerPrice;		// 外宾价格
    private String performedBy;		// 执行科室
    private Integer feeTypeMask;		// 是否自费
    private String classOnInpRcpt;		// 住院收据分类
    private String classOnOutpRcpt;		// 门诊收据分类
    private String classOnReckoning;		// 核算科目
    private String subjCode;		// 会计科目
    private String classOnMr;		// 病案首页分类
    private String memo;		// 备注信息
    private Date startDate;		// 启用日期
    private Date stopDate;		// 停止日期
    private String operator;		// 维护者
    private Date enterDate;		// 输入日期
    private Double highPrice;		// 最高价格
    private String materialCode;		// 物价码
    private Double score1;		// score_1
    private Double score2;		// score_2
    private String priceNameCode;		// price_name_code
    private String controlFlag;		// control_flag
    private String inputCode;		// input_code
    private String inputCodeWb;		// input_code_wb
    private String stdCode1;		// std_code_1
    private String changedMemo;		// 价格变更原因包括调价和停用等都可以录入保存原因
    private String classOnInsurMr;		// class_on_insur_mr
    private String cwtjCode;		// cwtj_code
    private String xmWy;		// 未央项目对照
    private String lbWy;		// 未央类别对照
    private String mzsjWy;		// 门诊收据对照
    private String zysjWy;		// 住院收据对照
    private String groupFlag;		// group_flag
    private String stopOperator;		// stop_operator
    private String orgId; //组织机构
    public PriceListVo() {
    }

    public PriceListVo(String label, String itemClass, String itemCode, String itemName, String itemSpec, String units, Double price, Double preferPrice, Double foreignerPrice, String performedBy, Integer feeTypeMask, String classOnInpRcpt, String classOnOutpRcpt, String classOnReckoning, String subjCode, String classOnMr, String memo, Date startDate, Date stopDate, String operator, Date enterDate, Double highPrice, String materialCode, Double score1, Double score2, String priceNameCode, String controlFlag, String inputCode, String inputCodeWb, String stdCode1, String changedMemo, String classOnInsurMr, String cwtjCode, String xmWy, String lbWy, String mzsjWy, String zysjWy, String groupFlag, String stopOperator, String orgId) {
        this.label = label;
        this.itemClass = itemClass;
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemSpec = itemSpec;
        this.units = units;
        this.price = price;
        this.preferPrice = preferPrice;
        this.foreignerPrice = foreignerPrice;
        this.performedBy = performedBy;
        this.feeTypeMask = feeTypeMask;
        this.classOnInpRcpt = classOnInpRcpt;
        this.classOnOutpRcpt = classOnOutpRcpt;
        this.classOnReckoning = classOnReckoning;
        this.subjCode = subjCode;
        this.classOnMr = classOnMr;
        this.memo = memo;
        this.startDate = startDate;
        this.stopDate = stopDate;
        this.operator = operator;
        this.enterDate = enterDate;
        this.highPrice = highPrice;
        this.materialCode = materialCode;
        this.score1 = score1;
        this.score2 = score2;
        this.priceNameCode = priceNameCode;
        this.controlFlag = controlFlag;
        this.inputCode = inputCode;
        this.inputCodeWb = inputCodeWb;
        this.stdCode1 = stdCode1;
        this.changedMemo = changedMemo;
        this.classOnInsurMr = classOnInsurMr;
        this.cwtjCode = cwtjCode;
        this.xmWy = xmWy;
        this.lbWy = lbWy;
        this.mzsjWy = mzsjWy;
        this.zysjWy = zysjWy;
        this.groupFlag = groupFlag;
        this.stopOperator = stopOperator;
        this.orgId = orgId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getItemClass() {
        return itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSpec() {
        return itemSpec;
    }

    public void setItemSpec(String itemSpec) {
        this.itemSpec = itemSpec;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPreferPrice() {
        return preferPrice;
    }

    public void setPreferPrice(Double preferPrice) {
        this.preferPrice = preferPrice;
    }

    public Double getForeignerPrice() {
        return foreignerPrice;
    }

    public void setForeignerPrice(Double foreignerPrice) {
        this.foreignerPrice = foreignerPrice;
    }

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public Integer getFeeTypeMask() {
        return feeTypeMask;
    }

    public void setFeeTypeMask(Integer feeTypeMask) {
        this.feeTypeMask = feeTypeMask;
    }

    public String getClassOnInpRcpt() {
        return classOnInpRcpt;
    }

    public void setClassOnInpRcpt(String classOnInpRcpt) {
        this.classOnInpRcpt = classOnInpRcpt;
    }

    public String getClassOnOutpRcpt() {
        return classOnOutpRcpt;
    }

    public void setClassOnOutpRcpt(String classOnOutpRcpt) {
        this.classOnOutpRcpt = classOnOutpRcpt;
    }

    public String getClassOnReckoning() {
        return classOnReckoning;
    }

    public void setClassOnReckoning(String classOnReckoning) {
        this.classOnReckoning = classOnReckoning;
    }

    public String getSubjCode() {
        return subjCode;
    }

    public void setSubjCode(String subjCode) {
        this.subjCode = subjCode;
    }

    public String getClassOnMr() {
        return classOnMr;
    }

    public void setClassOnMr(String classOnMr) {
        this.classOnMr = classOnMr;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

    public void setStopDate(Date stopDate) {
        this.stopDate = stopDate;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Double getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Double highPrice) {
        this.highPrice = highPrice;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public Double getScore1() {
        return score1;
    }

    public void setScore1(Double score1) {
        this.score1 = score1;
    }

    public Double getScore2() {
        return score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }

    public String getPriceNameCode() {
        return priceNameCode;
    }

    public void setPriceNameCode(String priceNameCode) {
        this.priceNameCode = priceNameCode;
    }

    public String getControlFlag() {
        return controlFlag;
    }

    public void setControlFlag(String controlFlag) {
        this.controlFlag = controlFlag;
    }

    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public String getInputCodeWb() {
        return inputCodeWb;
    }

    public void setInputCodeWb(String inputCodeWb) {
        this.inputCodeWb = inputCodeWb;
    }

    public String getStdCode1() {
        return stdCode1;
    }

    public void setStdCode1(String stdCode1) {
        this.stdCode1 = stdCode1;
    }

    public String getChangedMemo() {
        return changedMemo;
    }

    public void setChangedMemo(String changedMemo) {
        this.changedMemo = changedMemo;
    }

    public String getClassOnInsurMr() {
        return classOnInsurMr;
    }

    public void setClassOnInsurMr(String classOnInsurMr) {
        this.classOnInsurMr = classOnInsurMr;
    }

    public String getCwtjCode() {
        return cwtjCode;
    }

    public void setCwtjCode(String cwtjCode) {
        this.cwtjCode = cwtjCode;
    }

    public String getXmWy() {
        return xmWy;
    }

    public void setXmWy(String xmWy) {
        this.xmWy = xmWy;
    }

    public String getLbWy() {
        return lbWy;
    }

    public void setLbWy(String lbWy) {
        this.lbWy = lbWy;
    }

    public String getMzsjWy() {
        return mzsjWy;
    }

    public void setMzsjWy(String mzsjWy) {
        this.mzsjWy = mzsjWy;
    }

    public String getZysjWy() {
        return zysjWy;
    }

    public void setZysjWy(String zysjWy) {
        this.zysjWy = zysjWy;
    }

    public String getGroupFlag() {
        return groupFlag;
    }

    public void setGroupFlag(String groupFlag) {
        this.groupFlag = groupFlag;
    }

    public String getStopOperator() {
        return stopOperator;
    }

    public void setStopOperator(String stopOperator) {
        this.stopOperator = stopOperator;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
