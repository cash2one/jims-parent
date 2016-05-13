package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 出库类型字典
 * Created by ztq on 2016/5/10.
 */
public class DrugExportClassDict extends DataEntity<DrugExportClassDict> {

    private static final long serialVersionUID = 1L;
    private String exportClass;        // 出库分类
    private String statisticClass;        // statistic_class
    private String storageType;        // storage_type
    private Long accountFlag;        // 保存时是否记帐

    public DrugExportClassDict() {
        super();
    }

    public DrugExportClassDict(String id) {
        super(id);
    }

    @Length(min = 1, max = 8, message = "出库分类长度必须介于 1 和 8 之间")
    public String getExportClass() {
        return exportClass;
    }

    public void setExportClass(String exportClass) {
        this.exportClass = exportClass;
    }

    @Length(min = 0, max = 16, message = "statistic_class长度必须介于 0 和 16 之间")
    public String getStatisticClass() {
        return statisticClass;
    }

    public void setStatisticClass(String statisticClass) {
        this.statisticClass = statisticClass;
    }

    @Length(min = 0, max = 8, message = "storage_type长度必须介于 0 和 8 之间")
    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public Long getAccountFlag() {
        return accountFlag;
    }

    public void setAccountFlag(Long accountFlag) {
        this.accountFlag = accountFlag;
    }

}