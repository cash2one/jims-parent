package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 药品供应商生产商实体类
 * Created by ztq on 2016/5/10.
 */
public class DrugSupplierCatalog extends DataEntity<DrugSupplierCatalog> {

    private static final long serialVersionUID = 1L;
    private String supplierId;        // 供应商别名
    private String supplier;        // 厂商
    private String supplierClass;        // 厂商类别
    private String inputCode;        // 输入码
    private String memo;        // 备注
    private String trademark;        // 注册商标
    private String inputCodeWb;        // input_code_wb
    private Integer foreignx;        // 是否国外
    private String supplierCode;        // 厂商代码
    private Integer usedFlag;        // 是否使用1停用0使用
    private String orgId;        // 所属组织结构

    public DrugSupplierCatalog() {
        super();
    }

    public DrugSupplierCatalog(String id) {
        super(id);
    }

    @Length(min = 1, max = 10, message = "供应商别名长度必须介于 1 和 10 之间")
    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    @Length(min = 0, max = 40, message = "厂商长度必须介于 0 和 40 之间")
    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    @Length(min = 0, max = 8, message = "厂商类别长度必须介于 0 和 8 之间")
    public String getSupplierClass() {
        return supplierClass;
    }

    public void setSupplierClass(String supplierClass) {
        this.supplierClass = supplierClass;
    }

    @Length(min = 0, max = 8, message = "输入码长度必须介于 0 和 8 之间")
    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }

    @Length(min = 0, max = 100, message = "备注长度必须介于 0 和 100 之间")
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Length(min = 0, max = 20, message = "注册商标长度必须介于 0 和 20 之间")
    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    @Length(min = 0, max = 8, message = "input_code_wb长度必须介于 0 和 8 之间")
    public String getInputCodeWb() {
        return inputCodeWb;
    }

    public void setInputCodeWb(String inputCodeWb) {
        this.inputCodeWb = inputCodeWb;
    }

    public Integer getForeignx() {
        return foreignx;
    }

    public void setForeignx(Integer foreignx) {
        this.foreignx = foreignx;
    }

    @Length(min = 0, max = 10, message = "厂商代码长度必须介于 0 和 10 之间")
    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public Integer getUsedFlag() {
        return usedFlag;
    }

    public void setUsedFlag(Integer usedFlag) {
        this.usedFlag = usedFlag;
    }

    @Length(min = 0, max = 64, message = "所属组织结构长度必须介于 0 和 64 之间")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


}
