/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;


/**
 * ExamSubclassDictEntity
 * @author zhangpeng
 * @version 2016-04-27
 */
public class ExamSubclassDict extends DataEntity<ExamSubclassDict> {
	
	private static final long serialVersionUID = 1L;
	private Long serialNo;		// 序号
	private String examClassName;		// 检查类别名称
	private String examSubclassName;		// 检查子类名称
	private String inputCode;		// 输入码
	private String craeteBy;		// 创建人
	private Date craeteDate;		// 创建时间
	
	public ExamSubclassDict() {
		super();
	}

	public ExamSubclassDict(String id){
		super(id);
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=0, max=20, message="检查类别名称长度必须介于 0 和 20 之间")
	public String getExamClassName() {
		return examClassName;
	}

	public void setExamClassName(String examClassName) {
		this.examClassName = examClassName;
	}
	
	@Length(min=0, max=20, message="检查子类名称长度必须介于 0 和 20 之间")
	public String getExamSubclassName() {
		return examSubclassName;
	}

	public void setExamSubclassName(String examSubclassName) {
		this.examSubclassName = examSubclassName;
	}
	
	@Length(min=0, max=20, message="输入码长度必须介于 0 和 20 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=128, message="创建人长度必须介于 0 和 128 之间")
	public String getCraeteBy() {
		return craeteBy;
	}

	public void setCraeteBy(String craeteBy) {
		this.craeteBy = craeteBy;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getCraeteDate() {
		return craeteDate;
	}

	public void setCraeteDate(Date craeteDate) {
		this.craeteDate = craeteDate;
	}
	
}