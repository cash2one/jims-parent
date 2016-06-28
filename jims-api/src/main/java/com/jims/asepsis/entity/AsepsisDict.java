package com.jims.asepsis.entity;

import com.jims.common.persistence.DataEntity;


/**
* 包名称维护Entity
* @author yangruidong
* @version 2016-06-27
*/
public class AsepsisDict extends DataEntity<AsepsisDict> {

    private static final long serialVersionUID = 1L;
    private String itemClass;  // 包类别
    private String asepsisCode;  // 包代码
    private String asepsisName;  // 包名称
    private String asepsisSpec;  // 规格
    private String units;  // 单位
    private Double antiPrice;  // 消毒费
    private Double nobackPrice;  // 辅料费
    private String belongDept;  // 所属科室
    private String memos;  // 备注
    private String inputCode;  // 输入码
    private Double validDays;  // 有效期
    private Double cleanPrice;  // 清洗费用
    private Double packPrice;  // 打包费用
    private Double asepPrice;  // 灭菌费用
    private Double itemRollbackFlag;  // 返还标志(0不回收，1回收)
    private String flag;  // 已经使用标记（0停止，1使用）
    private String orgId;  // 所属机构ID

    private String q; // 作模糊查询

    public AsepsisDict() {
        super();
    }

    public AsepsisDict(String id) {
        super(id);
    }

    public  String getItemClass() {
        return this.itemClass;
    }

    public void setItemClass(String itemClass) {
        this.itemClass = itemClass;
    }

    public  String getAsepsisCode() {
        return this.asepsisCode;
    }

    public void setAsepsisCode(String asepsisCode) {
        this.asepsisCode = asepsisCode;
    }

    public  String getAsepsisName() {
        return this.asepsisName;
    }

    public void setAsepsisName(String asepsisName) {
        this.asepsisName = asepsisName;
    }

    public  String getAsepsisSpec() {
        return this.asepsisSpec;
    }

    public void setAsepsisSpec(String asepsisSpec) {
        this.asepsisSpec = asepsisSpec;
    }

    public  String getUnits() {
        return this.units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public  Double getAntiPrice() {
        return this.antiPrice;
    }

    public void setAntiPrice(Double antiPrice) {
        this.antiPrice = antiPrice;
    }

    public  Double getNobackPrice() {
        return this.nobackPrice;
    }

    public void setNobackPrice(Double nobackPrice) {
        this.nobackPrice = nobackPrice;
    }

    public  String getBelongDept() {
        return this.belongDept;
    }

    public void setBelongDept(String belongDept) {
        this.belongDept = belongDept;
    }

    public  String getMemos() {
        return this.memos;
    }

    public void setMemos(String memos) {
        this.memos = memos;
    }

    public  String getInputCode() {
        return this.inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    public  Double getValidDays() {
        return this.validDays;
    }

    public void setValidDays(Double validDays) {
        this.validDays = validDays;
    }

    public  Double getCleanPrice() {
        return this.cleanPrice;
    }

    public void setCleanPrice(Double cleanPrice) {
        this.cleanPrice = cleanPrice;
    }

    public  Double getPackPrice() {
        return this.packPrice;
    }

    public void setPackPrice(Double packPrice) {
        this.packPrice = packPrice;
    }

    public  Double getAsepPrice() {
        return this.asepPrice;
    }

    public void setAsepPrice(Double asepPrice) {
        this.asepPrice = asepPrice;
    }

    public  Double getItemRollbackFlag() {
        return this.itemRollbackFlag;
    }

    public void setItemRollbackFlag(Double itemRollbackFlag) {
        this.itemRollbackFlag = itemRollbackFlag;
    }

    public  String getFlag() {
        return this.flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public  String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }
}