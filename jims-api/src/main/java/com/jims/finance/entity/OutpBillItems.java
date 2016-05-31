/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 门诊病人诊疗收费项目明细Entity
 * @author zhaoning
 * @version 2016-05-26
 */
public class OutpBillItems extends DataEntity<OutpBillItems> {
	
	private static final long serialVersionUID = 1L;
	private Date visitDate;		// 就诊日期
	private Integer visitNo;		// 就诊序号
	private String rcptNo;		// 收据号
	private Integer itemNo;		// 序号
	private String itemClass;		// 价表项目分类
	private String classOnRcpt;		// 收据项目分类
	private String itemCode;		// 项目代码
	private String itemName;		// 项目名称
	private String itemSpec;		// 项目规格
	private Double amount;		// 数量
	private String units;		// 单位
	private String performedBy;		// 执行科室
	private Double costs;		// 费用
	private Double charges;		// 应付费用
	private String confirmedOperator;		// confirmed_operator
	private Date confirmedDatetime;		// confirmed_datetime
	private String invoiceNo;		// invoice_no
	private Integer flag;		// flag
	private Integer repetition;		// repetition
	private String classOnReckoning;		// class_on_reckoning
	private String subjCode;		// subj_code
	private Double priceQuotiety;		// price_quotiety
	private Double itemPrice;		// item_price
	private Integer orderNo;		// order_no
	private Integer subOrderNo;		// sub_order_no
	private String printedRcptNo;		// printed_rcpt_no
	private String subItemNo;		// sub_item_no
	private String freqDetail;		// freq_detail
	private String administration;		// administration
	private String patternName;		// pattern_name
	private String frequency;		// frequency
	private Double confirmedAmount;		// confirmed_amount
	private String refundedFlag;		// refunded_flag
	private String refundedOperator;		// refunded_operator
	private Date refundedDatetime;		// refunded_datetime
	private String orderGroup;		// order_group
	private String orderDoctor;		// order_doctor
	private String orderDept;		// order_dept
	private String performedGroup;		// performed_group
	private String performedDoctor;		// performed_doctor
	private String documentNo;		// document_no
	private String appointNo;		// appoint_no
	private String performedByDoctor;		// performed_by_doctor
	private String cwtjCode;		// cwtj_code
	private String wardCode;		// 护理单元
	private String performedBySub;		// 药局子库房
	
	public OutpBillItems() {
		super();
	}

	public OutpBillItems(String id){
		super(id);
	}

	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	public Integer getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	
	@Length(min=0, max=128, message="收据号长度必须介于 0 和 128 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	
	@Length(min=0, max=1, message="价表项目分类长度必须介于 0 和 1 之间")
	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	
	@Length(min=0, max=1, message="收据项目分类长度必须介于 0 和 1 之间")
	public String getClassOnRcpt() {
		return classOnRcpt;
	}

	public void setClassOnRcpt(String classOnRcpt) {
		this.classOnRcpt = classOnRcpt;
	}
	
	@Length(min=0, max=128, message="项目代码长度必须介于 0 和 128 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Length(min=0, max=200, message="项目名称长度必须介于 0 和 200 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=100, message="项目规格长度必须介于 0 和 100 之间")
	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=128, message="单位长度必须介于 0 和 128 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	@Length(min=0, max=128, message="执行科室长度必须介于 0 和 128 之间")
	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}
	
	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}
	
	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}
	
	@Length(min=0, max=128, message="confirmed_operator长度必须介于 0 和 128 之间")
	public String getConfirmedOperator() {
		return confirmedOperator;
	}

	public void setConfirmedOperator(String confirmedOperator) {
		this.confirmedOperator = confirmedOperator;
	}

	public Date getConfirmedDatetime() {
		return confirmedDatetime;
	}

	public void setConfirmedDatetime(Date confirmedDatetime) {
		this.confirmedDatetime = confirmedDatetime;
	}
	
	@Length(min=0, max=128, message="invoice_no长度必须介于 0 和 128 之间")
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	public Integer getRepetition() {
		return repetition;
	}

	public void setRepetition(Integer repetition) {
		this.repetition = repetition;
	}
	
	@Length(min=0, max=128, message="class_on_reckoning长度必须介于 0 和 128 之间")
	public String getClassOnReckoning() {
		return classOnReckoning;
	}

	public void setClassOnReckoning(String classOnReckoning) {
		this.classOnReckoning = classOnReckoning;
	}
	
	@Length(min=0, max=128, message="subj_code长度必须介于 0 和 128 之间")
	public String getSubjCode() {
		return subjCode;
	}

	public void setSubjCode(String subjCode) {
		this.subjCode = subjCode;
	}
	
	public Double getPriceQuotiety() {
		return priceQuotiety;
	}

	public void setPriceQuotiety(Double priceQuotiety) {
		this.priceQuotiety = priceQuotiety;
	}
	
	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public Integer getSubOrderNo() {
		return subOrderNo;
	}

	public void setSubOrderNo(Integer subOrderNo) {
		this.subOrderNo = subOrderNo;
	}
	
	@Length(min=0, max=128, message="printed_rcpt_no长度必须介于 0 和 128 之间")
	public String getPrintedRcptNo() {
		return printedRcptNo;
	}

	public void setPrintedRcptNo(String printedRcptNo) {
		this.printedRcptNo = printedRcptNo;
	}
	
	@Length(min=0, max=128, message="sub_item_no长度必须介于 0 和 128 之间")
	public String getSubItemNo() {
		return subItemNo;
	}

	public void setSubItemNo(String subItemNo) {
		this.subItemNo = subItemNo;
	}
	
	@Length(min=0, max=160, message="freq_detail长度必须介于 0 和 160 之间")
	public String getFreqDetail() {
		return freqDetail;
	}

	public void setFreqDetail(String freqDetail) {
		this.freqDetail = freqDetail;
	}
	
	@Length(min=0, max=200, message="administration长度必须介于 0 和 200 之间")
	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}
	
	@Length(min=0, max=80, message="pattern_name长度必须介于 0 和 80 之间")
	public String getPatternName() {
		return patternName;
	}

	public void setPatternName(String patternName) {
		this.patternName = patternName;
	}
	
	@Length(min=0, max=128, message="frequency长度必须介于 0 和 128 之间")
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public Double getConfirmedAmount() {
		return confirmedAmount;
	}

	public void setConfirmedAmount(Double confirmedAmount) {
		this.confirmedAmount = confirmedAmount;
	}
	
	@Length(min=0, max=8, message="refunded_flag长度必须介于 0 和 8 之间")
	public String getRefundedFlag() {
		return refundedFlag;
	}

	public void setRefundedFlag(String refundedFlag) {
		this.refundedFlag = refundedFlag;
	}
	
	@Length(min=0, max=128, message="refunded_operator长度必须介于 0 和 128 之间")
	public String getRefundedOperator() {
		return refundedOperator;
	}

	public void setRefundedOperator(String refundedOperator) {
		this.refundedOperator = refundedOperator;
	}

	public Date getRefundedDatetime() {
		return refundedDatetime;
	}

	public void setRefundedDatetime(Date refundedDatetime) {
		this.refundedDatetime = refundedDatetime;
	}
	
	@Length(min=0, max=128, message="order_group长度必须介于 0 和 128 之间")
	public String getOrderGroup() {
		return orderGroup;
	}

	public void setOrderGroup(String orderGroup) {
		this.orderGroup = orderGroup;
	}
	
	@Length(min=0, max=40, message="order_doctor长度必须介于 0 和 40 之间")
	public String getOrderDoctor() {
		return orderDoctor;
	}

	public void setOrderDoctor(String orderDoctor) {
		this.orderDoctor = orderDoctor;
	}
	
	@Length(min=0, max=128, message="order_dept长度必须介于 0 和 128 之间")
	public String getOrderDept() {
		return orderDept;
	}

	public void setOrderDept(String orderDept) {
		this.orderDept = orderDept;
	}
	
	@Length(min=0, max=128, message="performed_group长度必须介于 0 和 128 之间")
	public String getPerformedGroup() {
		return performedGroup;
	}

	public void setPerformedGroup(String performedGroup) {
		this.performedGroup = performedGroup;
	}
	
	@Length(min=0, max=128, message="performed_doctor长度必须介于 0 和 128 之间")
	public String getPerformedDoctor() {
		return performedDoctor;
	}

	public void setPerformedDoctor(String performedDoctor) {
		this.performedDoctor = performedDoctor;
	}
	
	@Length(min=0, max=128, message="document_no长度必须介于 0 和 128 之间")
	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}
	
	@Length(min=0, max=128, message="appoint_no长度必须介于 0 和 128 之间")
	public String getAppointNo() {
		return appointNo;
	}

	public void setAppointNo(String appointNo) {
		this.appointNo = appointNo;
	}
	
	@Length(min=0, max=128, message="performed_by_doctor长度必须介于 0 和 128 之间")
	public String getPerformedByDoctor() {
		return performedByDoctor;
	}

	public void setPerformedByDoctor(String performedByDoctor) {
		this.performedByDoctor = performedByDoctor;
	}
	
	@Length(min=0, max=128, message="cwtj_code长度必须介于 0 和 128 之间")
	public String getCwtjCode() {
		return cwtjCode;
	}

	public void setCwtjCode(String cwtjCode) {
		this.cwtjCode = cwtjCode;
	}
	
	@Length(min=0, max=128, message="护理单元长度必须介于 0 和 128 之间")
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	@Length(min=0, max=128, message="药局子库房长度必须介于 0 和 128 之间")
	public String getPerformedBySub() {
		return performedBySub;
	}

	public void setPerformedBySub(String performedBySub) {
		this.performedBySub = performedBySub;
	}
	
}