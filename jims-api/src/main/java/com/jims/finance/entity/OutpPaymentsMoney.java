/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 门诊病人支付方式记录Entity
 * @author zhaoning
 * @version 2016-05-26
 */
public class OutpPaymentsMoney extends DataEntity<OutpPaymentsMoney> {
	
	private static final long serialVersionUID = 1L;
	private String rcptNo;		// 收据号
	private Integer paymentNo;		// 支付序号
	private String moneyType;		// 金额类型
	private Double paymentAmount;		// 数额
	private Double refundedAmount;		// 退数额
	private String prepayNo;		// prepay_no
	private Double xiaoji;//小计
	
	public OutpPaymentsMoney() {
		super();
	}

	public OutpPaymentsMoney(String id){
		super(id);
	}

	@Length(min=0, max=128, message="收据号长度必须介于 0 和 128 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
	public Integer getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(Integer paymentNo) {
		this.paymentNo = paymentNo;
	}
	
	@Length(min=0, max=16, message="金额类型长度必须介于 0 和 16 之间")
	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}
	
	public Double getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Double paymentAmount) {
		this.paymentAmount = paymentAmount;
	}
	
	public Double getRefundedAmount() {
		return refundedAmount;
	}

	public void setRefundedAmount(Double refundedAmount) {
		this.refundedAmount = refundedAmount;
	}
	
	@Length(min=0, max=128, message="prepay_no长度必须介于 0 和 128 之间")
	public String getPrepayNo() {
		return prepayNo;
	}

	public void setPrepayNo(String prepayNo) {
		this.prepayNo = prepayNo;
	}

	public Double getXiaoji() {
		return xiaoji;
	}

	public void setXiaoji(Double xiaoji) {
		this.xiaoji = xiaoji;
	}
}