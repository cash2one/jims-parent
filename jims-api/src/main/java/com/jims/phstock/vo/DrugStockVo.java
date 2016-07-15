package com.jims.phstock.vo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * 全院库存量查询
 * Created by ztq on 2016/5/16.
 */
public class DrugStockVo<T> implements Serializable {
    private List<T> inserted ;//新增的项目
    private List<T> deleted ;//删除的项目
    private List<T> updated ;//更新的项目

    private String orgId;

    public DrugStockVo() {
    }

    public DrugStockVo(List<T> inserted, List<T> deleted, List<T> updated) {
        this.inserted = inserted;
        this.deleted = deleted;
        this.updated = updated;
    }

    public List<T> getInserted() {
        return inserted;
    }

    public void setInserted(List<T> inserted) {
        this.inserted = inserted;
    }

    public List<T> getDeleted() {
        return deleted;
    }

    public void setDeleted(List<T> deleted) {
        this.deleted = deleted;
    }

    public List<T> getUpdated() {
        return updated;
    }

    public void setUpdated(List<T> updated) {
        this.updated = updated;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
