package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 入库类别字典表
 * Created by ztq on 2016/5/10.
 */
public class DrugImportClassDict extends DataEntity<DrugImportClassDict> {

    private static final long serialVersionUID = 1L;
    private String importClass;        // 入库分类
    private String statisticClass;        // 所属类别
    private String storageType;        // 库存类型
    private String fromLevel;        // 来源等级
    private String accountFlag;        // 0，不记账，1记账

    public DrugImportClassDict() {
        super();
    }

    public DrugImportClassDict(String id) {
        super(id);
    }

    @Length(min = 0, max = 8, message = "入库分类长度必须介于 0 和 8 之间")
    public String getImportClass() {
        return importClass;
    }

    public void setImportClass(String importClass) {
        this.importClass = importClass;
    }

    @Length(min = 0, max = 16, message = "所属类别长度必须介于 0 和 16 之间")
    public String getStatisticClass() {
        return statisticClass;
    }

    public void setStatisticClass(String statisticClass) {
        this.statisticClass = statisticClass;
    }

    @Length(min = 0, max = 8, message = "库存类型，全部、药局、药库长度必须介于 0 和 8 之间")
    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    @Length(min = 0, max = 2, message = "0，不记账，1记账长度必须介于 0 和 2 之间")
    public String getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(String accountFlag) {
        this.accountFlag = accountFlag;
    }

    public String getFromLevel() {
        return fromLevel;
    }

    public void setFromLevel(String fromLevel) {
        this.fromLevel = fromLevel;
    }
}
