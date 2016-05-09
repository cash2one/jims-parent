/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">EMR</a> All rights reserved.
 */
package com.jims.clinic.entity;



import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.Dict;



import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 诊断
 * @author pq
 * @version 2015-04-26
 * */
public class EmrDiagnosis extends DataEntity<EmrDiagnosis> implements Serializable{

	private static final long serialVersionUID = 1L;


	private String parentId;		// 父级id

	private String diagnosisId;		// 诊断id
    private String type; //诊断类型 1：初步诊断
    private String parentIds; // 诊断父级
	private Integer itemNo;		// 诊断序号
    private List<EmrDiagnosis> children;
    private String icdMingcheng  ;  //icd10中文名称

    private String description;   //诊断描述
    private String basis; // 诊断依据
    private boolean isno;  //判断查询全部集合
    private String patientId;//病人标识
    private Integer treatDays;//治疗天数
    private String treatResult;//治疗结果
    private String operTreatIndicator;//手术治疗标志
    private String pathologyNo;//病理号
    private String diagnosisDoc;//诊断医生
    private String inOrOutFlag;//是否住院标识
    private Date diagnosisDate;//诊断日期
    private String state;
    private String diagnosisParent;//诊断主表


	public EmrDiagnosis() {
		super();
        isno=false;
	}

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public EmrDiagnosis(String id){
		super(id);
	}



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(String diagnosisId) {
        this.diagnosisId = diagnosisId;
    }


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Integer getItemNo() {
        return itemNo;
    }

    public void setItemNo(Integer itemNo) {
        this.itemNo = itemNo;
    }




    public String getIcdMingcheng() {
        return icdMingcheng;
    }

    public void setIcdMingcheng(String icdMingcheng) {
        this.icdMingcheng = icdMingcheng;
    }

    public boolean isIsno() {
        return isno;
    }

    public void setIsno(boolean isno) {
        this.isno = isno;
    }




    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Integer getTreatDays() {
        return treatDays;
    }

    public void setTreatDays(Integer treatDays) {
        this.treatDays = treatDays;
    }

    public String getTreatResult() {
        return treatResult;
    }

    public void setTreatResult(String treatResult) {
        this.treatResult = treatResult;
    }

    public String getOperTreatIndicator() {
        return operTreatIndicator;
    }

    public void setOperTreatIndicator(String operTreatIndicator) {
        this.operTreatIndicator = operTreatIndicator;
    }

    public String getPathologyNo() {
        return pathologyNo;
    }

    public void setPathologyNo(String pathologyNo) {
        this.pathologyNo = pathologyNo;
    }

    public String getDiagnosisDoc() {
        return diagnosisDoc;
    }

    public void setDiagnosisDoc(String diagnosisDoc) {
        this.diagnosisDoc = diagnosisDoc;
    }

    public String getInOrOutFlag() {
        return inOrOutFlag;
    }

    public void setInOrOutFlag(String inOrOutFlag) {
        this.inOrOutFlag = inOrOutFlag;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

/*    public String get_parentId() {
        return parentId;
    }

    public void set_parentId(String _parentId) {
        this.parentId = _parentId;
    }*/


    public List<EmrDiagnosis> getChildren() {
        return children;
    }

    public void setChildren(List<EmrDiagnosis> children) {
        this.children = children;
    }


    public String getDiagnosisParent() {
        return diagnosisParent;
    }

    public void setDiagnosisParent(String diagnosisParent) {
        this.diagnosisParent = diagnosisParent;
    }
}