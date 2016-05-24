package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 入库主记录
 * Created by heren on 2016/5/16.
 */
public class DrugImportMaster extends DataEntity<DrugImportMaster> {
    private static final long serialVersionUID = 1L;
    private String documentNo;		// 入库单号
    private String storage;		// 库存管理单位
    private Date importDate;		// 入库日期
    private String supplier;		// 供货方
    private Double accountReceivable;		// 应付款
    private Double accountPayed;		// 已付款
    private Double additionalFee;		// 附加费
    private String importClass;		// 入库类别
    private String subStorage;		// 存放库房
    private Integer accountIndicator;		// 记帐标志
    private String memos;		// 备注
    private String operator;		// 录入者
    private Integer flag;		// 上账标志
    private Date acctDate;		// 上账日期
    private String acctOperator;		// 上帐人
    private String voucherNo;		// 凭证号
    private Date tallyDate;		// 上帐日期
    private String tallyOperator;		// tally_operator
    private String subSupplier;		// sub_supplier
    private String recoveryDocuNo;		// 纠错入库的单据号
    private String orgId;		// 所属结构

    private List<DrugImportDetail> detailList;   // 关联的详细数据
    private String subStorageDeptId;   // 入库子单位ID

    public String getSubStorageDeptId() {
        return subStorageDeptId;
    }

    public void setSubStorageDeptId(String subStorageDeptId) {
        this.subStorageDeptId = subStorageDeptId;
    }

    public List<DrugImportDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DrugImportDetail> detailList) {
        this.detailList = detailList;
    }

    public DrugImportMaster() {
        super();
    }

    public DrugImportMaster(String id){
        super(id);
    }


    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }


    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }


    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }


    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }


    public Double getAccountReceivable() {
        return accountReceivable;
    }

    public void setAccountReceivable(Double accountReceivable) {
        this.accountReceivable = accountReceivable;
    }


    public Double getAccountPayed() {
        return accountPayed;
    }

    public void setAccountPayed(Double accountPayed) {
        this.accountPayed = accountPayed;
    }


    public Double getAdditionalFee() {
        return additionalFee;
    }

    public void setAdditionalFee(Double additionalFee) {
        this.additionalFee = additionalFee;
    }


    public String getImportClass() {
        return importClass;
    }

    public void setImportClass(String importClass) {
        this.importClass = importClass;
    }


    public String getSubStorage() {
        return subStorage;
    }

    public void setSubStorage(String subStorage) {
        this.subStorage = subStorage;
    }


    public Integer getAccountIndicator() {
        return accountIndicator;
    }

    public void setAccountIndicator(Integer accountIndicator) {
        this.accountIndicator = accountIndicator;
    }


    public String getMemos() {
        return memos;
    }

    public void setMemos(String memos) {
        this.memos = memos;
    }


    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }


    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }


    public Date getAcctDate() {
        return acctDate;
    }

    public void setAcctDate(Date acctDate) {
        this.acctDate = acctDate;
    }


    public String getAcctOperator() {
        return acctOperator;
    }

    public void setAcctOperator(String acctOperator) {
        this.acctOperator = acctOperator;
    }


    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }


    public Date getTallyDate() {
        return tallyDate;
    }

    public void setTallyDate(Date tallyDate) {
        this.tallyDate = tallyDate;
    }


    public String getTallyOperator() {
        return tallyOperator;
    }

    public void setTallyOperator(String tallyOperator) {
        this.tallyOperator = tallyOperator;
    }


    public String getSubSupplier() {
        return subSupplier;
    }

    public void setSubSupplier(String subSupplier) {
        this.subSupplier = subSupplier;
    }


    public String getRecoveryDocuNo() {
        return recoveryDocuNo;
    }

    public void setRecoveryDocuNo(String recoveryDocuNo) {
        this.recoveryDocuNo = recoveryDocuNo;
    }


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
