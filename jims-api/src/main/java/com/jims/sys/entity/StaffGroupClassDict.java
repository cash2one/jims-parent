/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 工作组类字典Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class StaffGroupClassDict extends DataEntity<StaffGroupClassDict> {
	
	private static final long serialVersionUID = 1L;
	private String serialNo;		// 序号
	private String groupClass;		// GROUP_CLASS
	private String hosId;		// 医院id
	
	public StaffGroupClassDict() {
		super();
	}

	public StaffGroupClassDict(String id){
		super(id);
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=0, max=16, message="GROUP_CLASS长度必须介于 0 和 16 之间")
	public String getGroupClass() {
		return groupClass;
	}

	public void setGroupClass(String groupClass) {
		this.groupClass = groupClass;
	}

	public String getHosId() {
		return hosId;
	}

	public void setHosId(String hosId) {
		this.hosId = hosId;
	}
}