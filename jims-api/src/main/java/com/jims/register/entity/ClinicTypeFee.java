/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 号类字典Entity
 * @author zhaoning
 * @version 2016-05-16
 */
public class ClinicTypeFee extends DataEntity<ClinicTypeFee> {
	
	private static final long serialVersionUID = 1L;
	private String typeId;		// 号类外键
	private String chargeItem;		// 费用类型：挂号费、诊疗费、其它费；来自字典表
	private String priceItem;		// 价表项目
	private Double price;		// 价格（空，可以用于查询）

	private String itemName;
	
	public ClinicTypeFee() {
		super();
	}

	public ClinicTypeFee(String id){
		super(id);
	}

	@Length(min=0, max=64, message="号类外键长度必须介于 0 和 64 之间")
	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	@Length(min=0, max=64, message="费用类型：挂号费、诊疗费、其它费；来自字典表长度必须介于 0 和 64 之间")
	public String getChargeItem() {
		return chargeItem;
	}

	public void setChargeItem(String chargeItem) {
		this.chargeItem = chargeItem;
	}
	
	@Length(min=0, max=64, message="价表项目长度必须介于 0 和 64 之间")
	public String getPriceItem() {
		return priceItem;
	}

	public void setPriceItem(String priceItem) {
		this.priceItem = priceItem;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}