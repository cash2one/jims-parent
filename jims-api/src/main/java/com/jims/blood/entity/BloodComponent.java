/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.blood.entity;


import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 血液成分字典表Entity
 * @author zhangpeng
 * @version 2016-05-13
 */
public class BloodComponent extends DataEntity<BloodComponent> {
	
	private static final long serialVersionUID = 1L;
	private String bloodType;		// 血液成分
	private String bloodTypeName;		// 血液成分名称
	private String bloodMatch;		// 是否配血
	private Integer usefulLife;		// 保存天数
	private String temperature;		// 保存温度
	private String unit;		// 计量单位
	private Date stopDate;		// 停止日期
	private Integer sortNum;		// 排序序号
	
	public BloodComponent() {
		super();
	}

	public BloodComponent(String id){
		super(id);
	}

	@Length(min=1, max=10, message="血液成分长度必须介于 1 和 10 之间")
	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	
	@Length(min=0, max=40, message="血液成分名称长度必须介于 0 和 40 之间")
	public String getBloodTypeName() {
		return bloodTypeName;
	}

	public void setBloodTypeName(String bloodTypeName) {
		this.bloodTypeName = bloodTypeName;
	}
	
	@Length(min=0, max=1, message="是否配血长度必须介于 0 和 1 之间")
	public String getBloodMatch() {
		return bloodMatch;
	}

	public void setBloodMatch(String bloodMatch) {
		this.bloodMatch = bloodMatch;
	}
	
	public Integer getUsefulLife() {
		return usefulLife;
	}

	public void setUsefulLife(Integer usefulLife) {
		this.usefulLife = usefulLife;
	}
	
	@Length(min=0, max=24, message="保存温度长度必须介于 0 和 24 之间")
	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	
	@Length(min=0, max=4, message="计量单位长度必须介于 0 和 4 之间")
	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	
	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}
	
}