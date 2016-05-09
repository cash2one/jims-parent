/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;



/**
 * ExamRptPatternEntity
 * @author zhangpeng
 * @version 2016-04-27
 */
public class ExamRptPattern extends DataEntity<ExamRptPattern> {
	
	private static final long serialVersionUID = 1L;
	private String examClass;		// 检查类别
	private String examSubClass;		// 检查子类
	private String descItem;		// 描述项目
	private String descName;		// 描述名称
	private String description;		// 描述
	private String descriptionCode;		// 描述代码
	private String inputCode;		// 描述名称输入码
    private String orgId;//机构id
    private String doctorTeshu;		// 指定医生，指定项目

	public ExamRptPattern() {
		super();
	}

	public ExamRptPattern(String id){
		super(id);
	}

	@Length(min=0, max=20, message="检查类别长度必须介于 0 和 20 之间")
	public String getExamClass() {
		return examClass;
	}

	public void setExamClass(String examClass) {
		this.examClass = examClass;
	}
	
	@Length(min=0, max=20, message="检查子类长度必须介于 0 和 20 之间")
	public String getExamSubClass() {
		return examSubClass;
	}

	public void setExamSubClass(String examSubClass) {
		this.examSubClass = examSubClass;
	}
	
	@Length(min=0, max=40, message="描述项目长度必须介于 0 和 40 之间")
	public String getDescItem() {
		return descItem;
	}

	public void setDescItem(String descItem) {
		this.descItem = descItem;
	}
	
	@Length(min=0, max=40, message="描述名称长度必须介于 0 和 40 之间")
	public String getDescName() {
		return descName;
	}

	public void setDescName(String descName) {
		this.descName = descName;
	}
	
	@Length(min=0, max=1600, message="描述长度必须介于 0 和 1600 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=40, message="描述代码长度必须介于 0 和 40 之间")
	public String getDescriptionCode() {
		return descriptionCode;
	}

	public void setDescriptionCode(String descriptionCode) {
		this.descriptionCode = descriptionCode;
	}
	
	@Length(min=0, max=20, message="描述名称输入码长度必须介于 0 和 20 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getDoctorTeshu() {
        return doctorTeshu;
    }

    public void setDoctorTeshu(String doctorTeshu) {
        this.doctorTeshu = doctorTeshu;
    }
}