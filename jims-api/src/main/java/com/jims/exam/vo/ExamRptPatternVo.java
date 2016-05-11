package com.jims.exam.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * 检查项目Vo
 *
 * @author tangxb
 * @version 2016-05-03
 */
@XmlRootElement
public class ExamRptPatternVo<T> implements Serializable {
    private List<T> inserted ;//新增的项目
    private List<T> updated ;//更新的项目
    private String examClass;//类别
    private String examSubClass;//子类别
    private String orgId;//机构id

    public ExamRptPatternVo() {
    }

    public ExamRptPatternVo(List<T> inserted, List<T> updated, String examClass, String examSubClass, String orgId) {
        this.inserted = inserted;
        this.updated = updated;
        this.examClass = examClass;
        this.examSubClass = examSubClass;
        this.orgId = orgId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getExamSubClass() {
        return examSubClass;
    }

    public void setExamSubClass(String examSubClass) {
        this.examSubClass = examSubClass;
    }

    public String getExamClass() {
        return examClass;
    }

    public void setExamClass(String examClass) {
        this.examClass = examClass;
    }

    public List<T> getInserted() {
        return inserted;
    }

    public void setInserted(List<T> inserted) {
        this.inserted = inserted;
    }

    public List<T> getUpdated() {
        return updated;
    }

    public void setUpdated(List<T> updated) {
        this.updated = updated;
    }
};

