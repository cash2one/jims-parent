/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.orders.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 医嘱计价项目Entity
 * @author pq
 * @version 2016-05-18
 */
public class OrdersCosts extends DataEntity<OrdersCosts> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人标识号
	private String visitId;		// 病人本次住院标识
	private String orderId;		// 医嘱主键Id
	private Integer orderNo;		// 医嘱序号
	private Integer orderSubNo;		// 医嘱子序号
	private Integer itemNo;		// 计价项目序号
	private String itemClass;		// 计价项目类别
	private String itemName;		// 计价项目名称
	private String itemCode;		// 计价项目代码
	private String itemSpec;		// 计价项目规格
	private String units;		// 计价单位
	private Double amount;		// 数量
	private Double totalAmount;		// 累计数量
	private Double costs;		// 本项目累计费用
	private String backbillRule;		// 后台记费规则
	
	public OrdersCosts() {
		super();
	}

	public OrdersCosts(String id){
		super(id);
	}

	@Length(min=1, max=64, message="病人标识号长度必须介于 1 和 64 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@Length(min=1, max=64, message="病人本次住院标识长度必须介于 1 和 64 之间")
	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}
	
	@Length(min=1, max=64, message="医嘱主键Id长度必须介于 1 和 64 之间")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	@NotNull(message="医嘱序号不能为空")
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public Integer getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(Integer orderSubNo) {
		this.orderSubNo = orderSubNo;
	}
	
	@NotNull(message="计价项目序号不能为空")
	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	
	@Length(min=0, max=1, message="计价项目类别长度必须介于 0 和 1 之间")
	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	
	@Length(min=0, max=200, message="计价项目名称长度必须介于 0 和 200 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=20, message="计价项目代码长度必须介于 0 和 20 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Length(min=0, max=64, message="计价项目规格长度必须介于 0 和 64 之间")
	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}
	
	@Length(min=0, max=8, message="计价单位长度必须介于 0 和 8 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}
	
	@Length(min=0, max=64, message="后台记费规则长度必须介于 0 和 64 之间")
	public String getBackbillRule() {
		return backbillRule;
	}

	public void setBackbillRule(String backbillRule) {
		this.backbillRule = backbillRule;
	}
	
}