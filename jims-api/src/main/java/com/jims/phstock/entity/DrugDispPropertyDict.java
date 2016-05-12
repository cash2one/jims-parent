package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 摆药类别字典
 * Created by heren on 2016/5/10.
 */
public class DrugDispPropertyDict extends DataEntity<DrugDispPropertyDict> {

    private static final long serialVersionUID = 1L;
    private String dispensingProperty;        // 摆药类别
    private String drugAdministrations;        // 对应给药途径

    public DrugDispPropertyDict() {
        super();
    }

    public DrugDispPropertyDict(String id) {
        super(id);
    }

    @Length(min = 1, max = 10, message = "摆药类别长度必须介于 1 和 10 之间")
    public String getDispensingProperty() {
        return dispensingProperty;
    }

    public void setDispensingProperty(String dispensingProperty) {
        this.dispensingProperty = dispensingProperty;
    }

    @Length(min = 0, max = 800, message = "对应给药途径长度必须介于 0 和 800 之间")
    public String getDrugAdministrations() {
        return drugAdministrations;
    }

    public void setDrugAdministrations(String drugAdministrations) {
        this.drugAdministrations = drugAdministrations;
    }

}
