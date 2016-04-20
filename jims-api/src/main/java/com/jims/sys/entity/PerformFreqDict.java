/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 医嘱执行频率字典Entity
 * @author zhangpeng
 * @version 2016-04-18
 */
public class PerformFreqDict extends DataEntity<PerformFreqDict> {
	
	private static final long serialVersionUID = 1L;
	private String hospitalId;		// 医院id
	private String freqDesc;		// 执行频率描述
	private String freqCounter;		// 频率次数
	private String freqInterval;		// 频率间隔
	private String freqIntervalUnits;		// 频率间隔单位
	
	public PerformFreqDict() {
		super();
	}

	public PerformFreqDict(String id){
		super(id);
	}

	@Length(min=0, max=64, message="医院id长度必须介于 0 和 64 之间")
	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}
	
	@Length(min=0, max=16, message="执行频率描述长度必须介于 0 和 16 之间")
	public String getFreqDesc() {
		return freqDesc;
	}

	public void setFreqDesc(String freqDesc) {
		this.freqDesc = freqDesc;
	}
	
	@Length(min=0, max=2, message="频率次数长度必须介于 0 和 2 之间")
	public String getFreqCounter() {
		return freqCounter;
	}

	public void setFreqCounter(String freqCounter) {
		this.freqCounter = freqCounter;
	}
	
	@Length(min=0, max=2, message="频率间隔长度必须介于 0 和 2 之间")
	public String getFreqInterval() {
		return freqInterval;
	}

	public void setFreqInterval(String freqInterval) {
		this.freqInterval = freqInterval;
	}
	
	@Length(min=0, max=4, message="频率间隔单位长度必须介于 0 和 4 之间")
	public String getFreqIntervalUnits() {
		return freqIntervalUnits;
	}

	public void setFreqIntervalUnits(String freqIntervalUnits) {
		this.freqIntervalUnits = freqIntervalUnits;
	}
	
}