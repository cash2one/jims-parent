/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 门诊医生收费明细Entity
 * @author zhaoning
 * @version 2016-04-20
 */
public class OutpOrdersCosts extends DataEntity<OutpOrdersCosts> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// ID号
	private Date visitDate;		// 就诊日期
	private Integer visitNo;		// 就诊序号
	private String serialNo;		// 流水号
	private String orderClass;		// 诊疗项目类别
	private Integer orderNo;		// 医嘱号
	private Integer orderSubNo;		// 子医嘱号
	private Integer itemNo;		// 顺序号
	private String itemClass;		// 收费项目类别
	private String itemName;		// 项目名称
	private String itemCode;		// 项目代码
	private String itemSpec;		// 项目规格
	private String units;		// 单位
	private Integer repetition;		// 付数
	private Double amount;		// 数量
	private String orderedByDept;		// 录入科室
	private String orderedByDoctor;		// 录入医生
	private String performedBy;		// 执行诊室
	private String classOnRcpt;		// 收费项目分类
	private Double costs;		// 计价金额
	private Double charges;		// 实收费用
	private String rcptNo;		// 收据号码
	private Integer billDescNo;		// bill_desc_no
	private Integer billItemNo;		// bill_item_no
	private Integer chargeIndicator;		// 收费标记
	private String classOnReckoning;		// 核算项目分类
	private String subjCode;		// 会计科目
	private Double priceQuotiety;		// 收费系数
	private Double itemPrice;		// 单价
	private String clinicNo;		// clinic_no
	private Date billDate;		// 项目收费日期
	private Integer billNo;		// 项目收费编号
	private String wardCode;		// 执行科室
	
	public OutpOrdersCosts() {
		super();
	}

	public OutpOrdersCosts(String id){
		super(id);
	}

	@Length(min=0, max=128, message="ID号长度必须介于 0 和 128 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
	
	@Length(min=1, max=20, message="流水号长度必须介于 1 和 20 之间")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=1, max=4, message="诊疗项目类别长度必须介于 1 和 4 之间")
	public String getOrderClass() {
		return orderClass;
	}

	public void setOrderClass(String orderClass) {
		this.orderClass = orderClass;
	}
	
	@NotNull(message="医嘱号不能为空")
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
	
	@NotNull(message="顺序号不能为空")
	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	
	@Length(min=0, max=10, message="收费项目类别长度必须介于 0 和 10 之间")
	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	
	@Length(min=0, max=800, message="项目名称长度必须介于 0 和 800 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=200, message="项目代码长度必须介于 0 和 200 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Length(min=0, max=100, message="项目规格长度必须介于 0 和 100 之间")
	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}
	
	@Length(min=0, max=128, message="单位长度必须介于 0 和 128 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	public Integer getRepetition() {
		return repetition;
	}

	public void setRepetition(Integer repetition) {
		this.repetition = repetition;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=128, message="录入科室长度必须介于 0 和 128 之间")
	public String getOrderedByDept() {
		return orderedByDept;
	}

	public void setOrderedByDept(String orderedByDept) {
		this.orderedByDept = orderedByDept;
	}
	
	@Length(min=0, max=128, message="录入医生长度必须介于 0 和 128 之间")
	public String getOrderedByDoctor() {
		return orderedByDoctor;
	}

	public void setOrderedByDoctor(String orderedByDoctor) {
		this.orderedByDoctor = orderedByDoctor;
	}
	
	@Length(min=0, max=128, message="执行诊室长度必须介于 0 和 128 之间")
	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}
	
	@Length(min=0, max=128, message="收费项目分类长度必须介于 0 和 128 之间")
	public String getClassOnRcpt() {
		return classOnRcpt;
	}

	public void setClassOnRcpt(String classOnRcpt) {
		this.classOnRcpt = classOnRcpt;
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
	
	@Length(min=0, max=20, message="收据号码长度必须介于 0 和 20 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
	public Integer getBillDescNo() {
		return billDescNo;
	}

	public void setBillDescNo(Integer billDescNo) {
		this.billDescNo = billDescNo;
	}
	
	public Integer getBillItemNo() {
		return billItemNo;
	}

	public void setBillItemNo(Integer billItemNo) {
		this.billItemNo = billItemNo;
	}
	
	public Integer getChargeIndicator() {
		return chargeIndicator;
	}

	public void setChargeIndicator(Integer chargeIndicator) {
		this.chargeIndicator = chargeIndicator;
	}
	
	@Length(min=0, max=128, message="核算项目分类长度必须介于 0 和 128 之间")
	public String getClassOnReckoning() {
		return classOnReckoning;
	}

	public void setClassOnReckoning(String classOnReckoning) {
		this.classOnReckoning = classOnReckoning;
	}
	
	@Length(min=0, max=128, message="会计科目长度必须介于 0 和 128 之间")
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
	
	@Length(min=0, max=40, message="clinic_no长度必须介于 0 和 40 之间")
	public String getClinicNo() {
		return clinicNo;
	}

	public void setClinicNo(String clinicNo) {
		this.clinicNo = clinicNo;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}
	
	public Integer getBillNo() {
		return billNo;
	}

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}
	
	@Length(min=0, max=128, message="执行科室长度必须介于 0 和 128 之间")
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
}