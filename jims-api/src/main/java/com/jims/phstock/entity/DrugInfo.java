package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 药品药理信息维护
 * Created by heren on 2016/5/16.
 */
public class DrugInfo extends DataEntity<DrugInfo> {
    private static final long serialVersionUID = 1L;
    private String drugCode;        // 药品代码
    private String drugName;        // 药品名称
    private String drugEName;        // 英文名称
    private String action;        // 药理作用
    private String indication;        // 适应症
    private String dosage;        // 用法用量
    private String form;        // 制剂
    private String pharmacokinetics ;        // 药代动力学
    private String adverseReaction;        //不良反应
    private String  attention;        // 注意事项
    private String  contraindication;        // 禁忌
    private String  skintest;
    private Integer  skinTime;

    private String drugForm;

    public String getDrugForm() {
        return drugForm;
    }

    public void setDrugForm(String drugForm) {
        this.drugForm = drugForm;
    }

    public DrugInfo() {
        super();
    }

    public DrugInfo(String id) {
        super(id);
    }

    @Length(min = 1, max = 20, message = "药品代码长度必须介于 1 和 20 之间")
    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        this.drugCode = drugCode;
    }

    @Length(min = 0, max = 100, message = "药品名称长度必须介于 0 和 100 之间")
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getIndication() {
        return indication;
    }

    public void setIndication(String indication) {
        this.indication = indication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }


    public String getPharmacokinetics() {
        return pharmacokinetics;
    }

    public void setPharmacokinetics(String pharmacokinetics) {
        this.pharmacokinetics = pharmacokinetics;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getAdverseReaction() {
        return adverseReaction;
    }

    public void setAdverseReaction(String adverseReaction) {
        this.adverseReaction = adverseReaction;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getContraindication() {
        return contraindication;
    }

    public void setContraindication(String contraindication) {
        this.contraindication = contraindication;
    }

    public String getSkintest() {
        return skintest;
    }

    public void setSkintest(String skintest) {
        this.skintest = skintest;
    }

    public Integer getSkinTime() {
        return skinTime;
    }

    public void setSkinTime(Integer skinTime) {
        this.skinTime = skinTime;
    }

    public String getDrugEName() {
        return drugEName;
    }

    public void setDrugEName(String drugEName) {
        this.drugEName = drugEName;
    }
}
