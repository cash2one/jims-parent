/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 门诊手术等级表Entity
 * @author qlx
 * @version 2016-05-06
 */
public class DocOperationGrade extends DataEntity<DocOperationGrade> {
	
	private static final long serialVersionUID = 1L;
	private String operationId;		// 手术申请表外键
	private String shoushuxuNo;		// 手术申请编号
	private String shoushudengji;		// 手术申请等级
	private String beiyong3;		// 备用
	private String beiyong4;		// 备用
	private String remark;		// 备注
	
	public DocOperationGrade() {
		super();
	}

	public DocOperationGrade(String id){
		super(id);
	}

	@Length(min=1, max=64, message="手术申请表外键长度必须介于 1 和 64 之间")
	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}
	
	@Length(min=0, max=64, message="手术申请编号长度必须介于 0 和 64 之间")
	public String getShoushuxuNo() {
		return shoushuxuNo;
	}

	public void setShoushuxuNo(String shoushuxuNo) {
		this.shoushuxuNo = shoushuxuNo;
	}
	
	@Length(min=0, max=64, message="手术申请等级长度必须介于 0 和 64 之间")
	public String getShoushudengji() {
		return shoushudengji;
	}

	public void setShoushudengji(String shoushudengji) {
		this.shoushudengji = shoushudengji;
	}
	
	@Length(min=0, max=200, message="备用长度必须介于 0 和 200 之间")
	public String getBeiyong3() {
		return beiyong3;
	}

	public void setBeiyong3(String beiyong3) {
		this.beiyong3 = beiyong3;
	}
	
	@Length(min=0, max=200, message="备用长度必须介于 0 和 200 之间")
	public String getBeiyong4() {
		return beiyong4;
	}

	public void setBeiyong4(String beiyong4) {
		this.beiyong4 = beiyong4;
	}
	
	@Length(min=0, max=450, message="备注长度必须介于 0 和 450 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}