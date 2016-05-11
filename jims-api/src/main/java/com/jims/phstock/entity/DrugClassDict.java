

package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
*
* 药品类别字典实体类
* Created by 赵铁强 on 2016/5/9.
*/

public class DrugClassDict extends DataEntity<DrugClassDict> {

    private static final long serialVersionUID = 1L;
    private String classCode;		// 类别代码
    private String className;		// 类别名称
    private String parentId;		// 父ID
    private String orgId;		// 所属结构

    public DrugClassDict() {
        super();
    }

    public DrugClassDict(String id){
        super(id);
    }

    @Length(min=1, max=10, message="类别代码长度必须介于 1 和 10 之间")
    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Length(min=0, max=40, message="类别名称长度必须介于 0 和 40 之间")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Length(min=0, max=10, message="类别名称长度必须介于 0 和 10 之间")
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Length(min=0, max=64, message="所属结构长度必须介于 0 和 64 之间")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}