package com.jims.asepsis.vo;

import java.util.Date;

/**
 * Created by admin on 2016/7/12.
 */
public class ImpExpVo {

    private String id;
    private Date date;
    private String documentNo;
    private String importExportClass;
    private Integer accountIndicator;
    private String acctOperator;
    private Date acctDate;
    private Integer flag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getAccountIndicator() {
        return accountIndicator;
    }

    public void setAccountIndicator(Integer accountIndicator) {
        this.accountIndicator = accountIndicator;
    }

    public String getImportExportClass() {
        return importExportClass;
    }

    public void setImportExportClass(String importExportClass) {
        this.importExportClass = importExportClass;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    public String getAcctOperator() {
        return acctOperator;
    }

    public void setAcctOperator(String acctOperator) {
        this.acctOperator = acctOperator;
    }

    public Date getAcctDate() {
        return acctDate;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public void setAcctDate(Date acctDate) {
        this.acctDate = acctDate;
    }
}
