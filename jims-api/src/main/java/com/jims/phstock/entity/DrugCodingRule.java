package com.jims.phstock.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 药品编码生成规则实体
 * Created by ztq on 2016/5/10.
 */
public class DrugCodingRule extends DataEntity<DrugCodingRule> {
    private static final long serialVersionUID = 1L;
    private Integer codeLevel;        // 编码级
    private Integer levelWidth;        // 级长
    private String className;        // 级名

    public DrugCodingRule() {
        super();
    }

    public DrugCodingRule(String id) {
        super(id);
    }

    public Integer getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(Integer codeLevel) {
        this.codeLevel = codeLevel;
    }

    public Integer getLevelWidth() {
        return levelWidth;
    }

    public void setLevelWidth(Integer levelWidth) {
        this.levelWidth = levelWidth;
    }

    @Length(min = 0, max = 8, message = "级名长度必须介于 0 和 8 之间")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
