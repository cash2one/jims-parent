/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 申请用血量Entity
 * @author qlx
 * @version 2016-04-28
 */
public class BloodCapacity extends DataEntity<BloodCapacity> {

	private static final long serialVersionUID = 1L;
	private String applyNum;		// 申请单号
	private String matchSubNum;		// 申请单子号
	private String fastSlow;		// 用血安排,1:急诊，2：计划，3：备血
	private Date transDate;		// 预定输血时间
	private Double transCapacity;		// 输血量
	private String bloodType;		// 申请成份血
	private String operator;		// 操作者
	private String unit;		// 血液单位
	private String orgId;		// 所属结构
	
	public BloodCapacity() {
		super();
	}

	public BloodCapacity(String id){
		super(id);
	}

	@Length(min=1, max=6, message="申请单号长度必须介于 1 和 6 之间")
	public String getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(String applyNum) {
		this.applyNum = applyNum;
	}
	
	@Length(min=1, max=1, message="申请单子号长度必须介于 1 和 1 之间")
	public String getMatchSubNum() {
		return matchSubNum;
	}

	public void setMatchSubNum(String matchSubNum) {
		this.matchSubNum = matchSubNum;
	}
	
	@Length(min=0, max=1, message="用血安排,1:急诊，2：计划，3：备血长度必须介于 0 和 1 之间")
	public String getFastSlow() {
		return fastSlow;
	}

	public void setFastSlow(String fastSlow) {
		this.fastSlow = fastSlow;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTransDate() {
		return transDate;
	}

	public void setTransDate(Date transDate) {
		this.transDate = transDate;
	}
	
	public Double getTransCapacity() {
		return transCapacity;
	}

	public void setTransCapacity(Double transCapacity) {
		this.transCapacity = transCapacity;
	}
	
	@Length(min=0, max=10, message="申请成份血长度必须介于 0 和 10 之间")
	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
	@Length(min=0, max=20, message="操作者长度必须介于 0 和 20 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Length(min=0, max=10, message="血液单位长度必须介于 0 和 10 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Length(min=0, max=64, message="所属结构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}