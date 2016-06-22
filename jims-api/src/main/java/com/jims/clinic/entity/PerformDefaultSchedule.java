package com.jims.clinic.entity;


import com.jims.common.persistence.DataEntity;

/**
 * 医嘱执行缺省时间Entity
 * @author pq
 * @version 2016-06-22
 */
public class PerformDefaultSchedule extends DataEntity<PerformDefaultSchedule> {
	
	private static final long serialVersionUID = 1L;
	private String freqDesc;		// 执行频率描述
	private String administration;		// 给药途径和方法
	private String defaultSchedule;		// 缺省的执行时间表
	
	public PerformDefaultSchedule() {
		super();
	}

	public PerformDefaultSchedule(String id){
		super(id);
	}


	public String getFreqDesc() {
		return freqDesc;
	}

	public void setFreqDesc(String freqDesc) {
		this.freqDesc = freqDesc;
	}


	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}


	public String getDefaultSchedule() {
		return defaultSchedule;
	}

	public void setDefaultSchedule(String defaultSchedule) {
		this.defaultSchedule = defaultSchedule;
	}

}