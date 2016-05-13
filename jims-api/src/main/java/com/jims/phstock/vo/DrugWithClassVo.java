package com.jims.phstock.vo;

import com.jims.phstock.entity.DrugDict;

import java.io.Serializable;
import java.util.List;

/**
 * 显示药品及药品类别
 * Created by heren on 2016/5/13.
 */
public class DrugWithClassVo implements Serializable {


    private String classCode ;//类别代码
    private String className ;//类别名称
    private List<DrugWithClassVo> children ;
    private List<DrugDict> drugDicts ;

    public DrugWithClassVo() {
    }

    public DrugWithClassVo(String classCode, String className, List<DrugWithClassVo> children, List<DrugDict> drugDicts) {
        this.classCode = classCode;
        this.className = className;
        this.children = children;
        this.drugDicts = drugDicts;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<DrugWithClassVo> getChildren() {
        return children;
    }

    public void setChildren(List<DrugWithClassVo> children) {
        this.children = children;
    }

    public List<DrugDict> getDrugDicts() {
        return drugDicts;
    }

    public void setDrugDicts(List<DrugDict> drugDicts) {
        this.drugDicts = drugDicts;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("DrugWithClassVo{");
        sb.append("classCode='").append(classCode).append('\'');
        sb.append(", className='").append(className).append('\'');
        sb.append(", children=").append(children);
        sb.append(", drugDicts=").append(drugDicts);
        sb.append('}');
        return sb.toString();
    }
}
