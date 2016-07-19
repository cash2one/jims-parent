package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 价表项目执行科室Entity
 * Created by fyg on 2016/7/18.
 */
public class PerformDept extends DataEntity<PerformDept> {

    private static final long serialVersionUID = 1L;
    private String itemClass;       //价表项目分类
    private String itemCode;        //价表项目代码
    private String performedBy;     //执行科室
    private String orgId;           //所属组织机构

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

    public String getPerformedBy() {
        return performedBy;
    }

    public void setPerformedBy(String performedBy) {
        this.performedBy = performedBy;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
