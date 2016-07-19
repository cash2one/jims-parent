package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 药品出库主记录Entity
 * @author lgx
 * @version 2016-05-23
 */
public class DrugExportMaster extends DataEntity<DrugExportMaster> {
	
	private static final long serialVersionUID = 1L;
	private String documentNo;		// 出库单号
	private String storage;		// 库存管理单位
	private Date exportDate;		// 出库日期
	private String receiver;		// 收货方
	private Double accountReceivable;		// 应付款
	private Double accountPayed;		// 已付款
	private Double additionalFee;		// 附加费
	private String exportClass;		// 出库类别
	private String subStorage;		// 存放库房
	private Integer accountIndicator;		// 记帐标志
	private String memos;		// 备注
	private String operator;		// 录入者
	private Integer flag;		// 上账标志
	private Date acctDate;		// 上账日期
	private String acctOperator;		// 上帐人
	private String voucherNo;		// 凭证号
	private Date tallyDate;		// tally_date
	private String tallyOperator;		// tally_operator
	private String subReceiver;		// 收货方子单位
	private String recoveryDocuNo;		// 纠错出库的单据号
	private String orgId;		// 所属结构
    private String importMan;
    private Date importDate;
    private String importDocumentNo;

    private List<DrugExportDetail> detailList;   // 药品出库明细
    private String subStorageDeptId;   // 出库子单位ID
    private String importFlag;    //  出库药品已入库状态 ，不为空时检索未入库数据

    public String getSubStorageDeptId() {
        return subStorageDeptId;
    }

    public void setSubStorageDeptId(String subStorageDeptId) {
        this.subStorageDeptId = subStorageDeptId;
    }

    public List<DrugExportDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<DrugExportDetail> detailList) {
        this.detailList = detailList;
    }

    public DrugExportMaster() {
		super();
	}

	public DrugExportMaster(String id){
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


	public Date getExportDate() {
		return exportDate;
	}

	public void setExportDate(Date exportDate) {
		this.exportDate = exportDate;
	}


	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
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


	public String getExportClass() {
		return exportClass;
	}

	public void setExportClass(String exportClass) {
		this.exportClass = exportClass;
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


	public String getSubReceiver() {
		return subReceiver;
	}

	public void setSubReceiver(String subReceiver) {
		this.subReceiver = subReceiver;
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

    public String getImportMan() {
        return importMan;
    }

    public void setImportMan(String importMan) {
        this.importMan = importMan;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getImportDocumentNo() {
        return importDocumentNo;
    }

    public void setImportDocumentNo(String importDocumentNo) {
        this.importDocumentNo = importDocumentNo;
    }

    public String getImportFlag() {
        return importFlag;
    }

    public void setImportFlag(String importFlag) {
        this.importFlag = importFlag;
    }
}