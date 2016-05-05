/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 诊疗项目分类字典Entity
 * @author xueyx
 * @version 2016-05-04
 */
public class ClinicItemClassDict extends DataEntity<ClinicItemClassDict> {
	
	private static final long serialVersionUID = 1L;
	private Integer serialNo;		// 序号
	private String classCode;		// 项目类别代码
	private String className;		// 项目类别名称
	private String inputCode;		// 输入码
	
	public ClinicItemClassDict() {
		super();
	}

	public ClinicItemClassDict(String id){
		super(id);
	}

	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=1, max=2, message="项目类别代码长度必须介于 1 和 2 之间")
	public String getClassCode() {
		return classCode;
	}

	public void setClassCode(String classCode) {
		this.classCode = classCode;
	}
	
	@Length(min=0, max=20, message="项目类别名称长度必须介于 0 和 20 之间")
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	@Length(min=0, max=16, message="输入码长度必须介于 0 和 16 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
}