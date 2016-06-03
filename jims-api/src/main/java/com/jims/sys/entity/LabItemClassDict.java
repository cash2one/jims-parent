/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 检验项目类别字典Entity
 * @author xueyx
 * @version 2016-05-05
 */
public class LabItemClassDict extends DataEntity<LabItemClassDict> {
	
	private static final long serialVersionUID = 1L;
	private Integer serialNo;		// 序号
	private String classCode;		// 类别代码
	private String className;		// 类别名称
	private String deptCode;		// 科室代码
    private String orgId;		// 所属组织结构

	public LabItemClassDict() {
		super();
	}

	public LabItemClassDict(String id){
		super(id);
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=1, max=16, message="类别代码长度必须介于 1 和 16 之间")
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
	
	@Length(min=1, max=16, message="科室代码长度必须介于 1 和 16 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}