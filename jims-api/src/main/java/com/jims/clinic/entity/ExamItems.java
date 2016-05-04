/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.List;

/**
 * 检查项目记录Entity
 * @author zhaoning
 * @version 2016-04-25
 */
public class ExamItems extends DataEntity<ExamItems> {
	
	private static final long serialVersionUID = 1L;
	private String examNo;		// 申请序号
	private Integer examItemNo;		// 项目序号
	private String examItem;		// 检查项目
	private String examItemCode;		// 项目代码
	private Double costs;		// 费用
	private Integer billingIndicator;		// billing_indicator
	private String rcptNo;		// 收据号
	private String explanation;		// 退费说明
	private String appointsId; //主记录id

	public String getAppointsId() {
		return appointsId;
	}

	public void setAppointsId(String appointsId) {
		this.appointsId = appointsId;
	}

	public ExamItems() {
		super();
	}

	public ExamItems(String id){
		super(id);
	}

	@Length(min=0, max=20, message="申请序号长度必须介于 0 和 20 之间")
	public String getExamNo() {
		return examNo;
	}

	public void setExamNo(String examNo) {
		this.examNo = examNo;
	}
	
	public Integer getExamItemNo() {
		return examItemNo;
	}

	public void setExamItemNo(Integer examItemNo) {
		this.examItemNo = examItemNo;
	}
	
	@Length(min=0, max=200, message="检查项目长度必须介于 0 和 200 之间")
	public String getExamItem() {
		return examItem;
	}

	public void setExamItem(String examItem) {
		this.examItem = examItem;
	}
	
	@Length(min=0, max=40, message="项目代码长度必须介于 0 和 40 之间")
	public String getExamItemCode() {
		return examItemCode;
	}

	public void setExamItemCode(String examItemCode) {
		this.examItemCode = examItemCode;
	}
	
	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}
	
	public Integer getBillingIndicator() {
		return billingIndicator;
	}

	public void setBillingIndicator(Integer billingIndicator) {
		this.billingIndicator = billingIndicator;
	}
	
	@Length(min=0, max=40, message="收据号长度必须介于 0 和 40 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
	@Length(min=0, max=200, message="退费说明长度必须介于 0 和 200 之间")
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
}