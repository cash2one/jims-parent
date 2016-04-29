/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">EMR</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.Dict;


import java.util.ArrayList;
import java.util.List;

/**
 * 诊断
 * @author pq
 * @version 2015-04-26
 * */
public class EmrDiagnosis extends DataEntity<EmrDiagnosis> {

	private static final long serialVersionUID = 1L;
	private String parentId;		// 父级id
	private String diagnosisId;		// 诊断id
    private String type; //诊断类型 1：初步诊断
    private String parentIds; // 诊断父级
	private Integer itemNo;		// 诊断序号
	private List<EmrDiagnosis> emrDiagnosisList;
    private String icdMingcheng  ;  //icd10中文名称
    private Dict dict;
    private String description;   //诊断描述
    private String basis; // 诊断依据
    private List list=new ArrayList();   //父类ID集合
    private boolean isno;  //判断查询全部集合
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

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
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
    @JsonProperty("_parentId")
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



    public List<EmrDiagnosis> getEmrDiagnosisList() {
        return emrDiagnosisList;
    }

    public void setEmrDiagnosisList(List<EmrDiagnosis> emrDiagnosisList) {
        this.emrDiagnosisList = emrDiagnosisList;
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

    public Dict getDict() {
        return dict;
    }

    public void setDict(Dict dict) {
        this.dict = dict;
    }



    @JsonIgnore
    public static void sortList(List<EmrDiagnosis> list, List<EmrDiagnosis> sourcelist, String parentId, boolean cascade){
        for (int i=0; i<sourcelist.size(); i++){
            EmrDiagnosis e = sourcelist.get(i);
            if (e.getParentIds()!=null && e.getParentIds()!=null
                    && e.getParentIds().equals(parentId)){
                list.add(e);
                if (cascade){
                    // 判断是否还有子节点, 有则继续获取子节点
                    for (int j=0; j<sourcelist.size(); j++){
                        EmrDiagnosis child = sourcelist.get(j);
                        if (child.getParentIds()!=null && child.getParentIds()!=null
                                && child.getParentIds().equals(e.getId())){
                            sortList(list, sourcelist, e.getId(), true);
                            break;
                        }
                    }
                }
            }
        }
    }

    @JsonIgnore
    public static String getParentIdsId(){
        return "0";
    }
}