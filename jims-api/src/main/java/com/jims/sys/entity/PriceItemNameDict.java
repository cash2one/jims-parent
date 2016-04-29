/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;



/**
 * 价表项目名称Entity
 * @author 罗海昆
 * @version 2016-04-26
 */
public class PriceItemNameDict extends DataEntity<PriceItemNameDict> {
	
	private static final long serialVersionUID = 1L;
	private String itemClass;		// 项目分类
	private String itemName;		// 项目名称
	private String itemCode;		// 项目代码
	private Integer stdIndicator;		// 正名标志
	private String inputCode;		// 输入码
	private Integer stopFlag;		// stop_flag
	private String inputCodeWb;		// input_code_wb
	private String performedByMz;		// 门诊医护分开,默认护理单元
	private Integer wardFlag;		// 是否护理(住院)
	private Integer teshuMzFlag;		// 门诊特殊项目
	private String memo;		// memo
	private String jckflag;		// jckflag
	
	public PriceItemNameDict() {
		super();
	}

	public PriceItemNameDict(String id){
		super(id);
	}

	@Length(min=1, max=1, message="项目分类长度必须介于 1 和 1 之间")
	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	
	@Length(min=1, max=100, message="项目名称长度必须介于 1 和 100 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=1, max=20, message="项目代码长度必须介于 1 和 20 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public Integer getStdIndicator() {
		return stdIndicator;
	}

	public void setStdIndicator(Integer stdIndicator) {
		this.stdIndicator = stdIndicator;
	}
	
	@Length(min=0, max=8, message="输入码长度必须介于 0 和 8 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	public Integer getStopFlag() {
		return stopFlag;
	}

	public void setStopFlag(Integer stopFlag) {
		this.stopFlag = stopFlag;
	}
	
	@Length(min=0, max=8, message="input_code_wb长度必须介于 0 和 8 之间")
	public String getInputCodeWb() {
		return inputCodeWb;
	}

	public void setInputCodeWb(String inputCodeWb) {
		this.inputCodeWb = inputCodeWb;
	}
	
	@Length(min=0, max=20, message="门诊医护分开,默认护理单元长度必须介于 0 和 20 之间")
	public String getPerformedByMz() {
		return performedByMz;
	}

	public void setPerformedByMz(String performedByMz) {
		this.performedByMz = performedByMz;
	}
	
	public Integer getWardFlag() {
		return wardFlag;
	}

	public void setWardFlag(Integer wardFlag) {
		this.wardFlag = wardFlag;
	}
	
	public Integer getTeshuMzFlag() {
		return teshuMzFlag;
	}

	public void setTeshuMzFlag(Integer teshuMzFlag) {
		this.teshuMzFlag = teshuMzFlag;
	}
	
	@Length(min=0, max=80, message="memo长度必须介于 0 和 80 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@Length(min=0, max=2, message="jckflag长度必须介于 0 和 2 之间")
	public String getJckflag() {
		return jckflag;
	}

	public void setJckflag(String jckflag) {
		this.jckflag = jckflag;
	}
	
}