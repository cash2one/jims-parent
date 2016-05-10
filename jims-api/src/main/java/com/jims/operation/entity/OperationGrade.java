/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.operation.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * cEntity
 * @author qlx
 * @version 2016-04-26
 */
public class OperationGrade extends DataEntity<OperationGrade> {
	
	private static final long serialVersionUID = 1L;
	private Operatioin operatioin;		// operation_id
	private String shoushuxuNo;		// 拟施手术编号
	private String shoushudengji;		// 手术等级编号
	private String beiyong3;		// beiyong3
	private String beiyong4;		// beiyong4
	private String remark;		// remark
	
	public OperationGrade() {
		super();
	}

	public OperationGrade(String id){
		super(id);
	}



	@Length(min=1, max=64, message="operation_id长度必须介于 1 和 64 之间")
	public Operatioin getOperatioin() {
		return operatioin;
	}

	public void setOperatioin(Operatioin operatioin) {
		this.operatioin = operatioin;
	}
	@Length(min=0, max=64, message="拟施手术编号长度必须介于 0 和 64 之间")
	public String getShoushuxuNo() {
		return shoushuxuNo;
	}

	public void setShoushuxuNo(String shoushuxuNo) {
		this.shoushuxuNo = shoushuxuNo;
	}
	
	@Length(min=0, max=64, message="手术等级编号长度必须介于 0 和 64 之间")
	public String getShoushudengji() {
		return shoushudengji;
	}

	public void setShoushudengji(String shoushudengji) {
		this.shoushudengji = shoushudengji;
	}
	
	@Length(min=0, max=200, message="beiyong3长度必须介于 0 和 200 之间")
	public String getBeiyong3() {
		return beiyong3;
	}

	public void setBeiyong3(String beiyong3) {
		this.beiyong3 = beiyong3;
	}
	
	@Length(min=0, max=200, message="beiyong4长度必须介于 0 和 200 之间")
	public String getBeiyong4() {
		return beiyong4;
	}

	public void setBeiyong4(String beiyong4) {
		this.beiyong4 = beiyong4;
	}
	
	@Length(min=0, max=450, message="remark长度必须介于 0 和 450 之间")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}