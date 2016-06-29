package com.jims.clinic.entity;



import com.jims.common.persistence.DataEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * 待发药住院处方明细记录Entity
 * @author CTQ
 * @version 2016-05-16
 */
public class DoctDrugPrescDetail extends DataEntity<DoctDrugPrescDetail>  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 机构ID
	private String patientId;		// 病人ID
	private String visitId;		// 住院ID
	private String prescMasterId;		// 住院处方主记录ID
	private Date prescDate;		// 处方日期
	private Integer prescNo;		// 处方号
	private Integer itemNo;		// 项目序号
	private Integer orderNo;		// 医嘱序号
	private Integer orderSubNo;		// 医嘱子序号
	private String drugCode;		// 药品编码
	private String drugSpec;		// 药品
	private String drugName;		// 药名
	private String firmId;		// 生产厂商
	private String packageSpec;		// 包装规格
	private String packageUnits;		// 包装单位
	private Double quantity;		// 数量
	private Double dosage;		// 剂量
	private String dosageUnits;		// 剂量单位
	private String administration;		// 用法
	private Double costs;		// 费用
	private Double payments;		// 已付费用
	private String memo;		// 备注
	private Integer amountPerPackage;		// 每包装数量
	private String frequency;		// 执行频率描述
	private Double dosageEach;		// 单次剂量
	private String freqDetail;		// 医嘱说明
	private String batchNo;		// batch_no
	private String markSubOrderNo;  //标示是否子医嘱
	public DoctDrugPrescDetail() {
		super();
	}

	public DoctDrugPrescDetail(String id){
		super(id);
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}


	public String getPrescMasterId() {
		return prescMasterId;
	}

	public void setPrescMasterId(String prescMasterId) {
		this.prescMasterId = prescMasterId;
	}


	public Date getPrescDate() {
		return prescDate;
	}

	public void setPrescDate(Date prescDate) {
		this.prescDate = prescDate;
	}


	public Integer getPrescNo() {
		return prescNo;
	}

	public void setPrescNo(Integer prescNo) {
		this.prescNo = prescNo;
	}


	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}


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


	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}


	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}


	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}


	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}


	public String getPackageSpec() {
		return packageSpec;
	}

	public void setPackageSpec(String packageSpec) {
		this.packageSpec = packageSpec;
	}


	public String getPackageUnits() {
		return packageUnits;
	}

	public void setPackageUnits(String packageUnits) {
		this.packageUnits = packageUnits;
	}


	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}


	public Double getDosage() {
		return dosage;
	}

	public void setDosage(Double dosage) {
		this.dosage = dosage;
	}


	public String getDosageUnits() {
		return dosageUnits;
	}

	public void setDosageUnits(String dosageUnits) {
		this.dosageUnits = dosageUnits;
	}


	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}


	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}


	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	public Integer getAmountPerPackage() {
		return amountPerPackage;
	}

	public void setAmountPerPackage(Integer amountPerPackage) {
		this.amountPerPackage = amountPerPackage;
	}


	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}


	public Double getDosageEach() {
		return dosageEach;
	}

	public void setDosageEach(Double dosageEach) {
		this.dosageEach = dosageEach;
	}


	public String getFreqDetail() {
		return freqDetail;
	}

	public void setFreqDetail(String freqDetail) {
		this.freqDetail = freqDetail;
	}


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getMarkSubOrderNo() {
		return markSubOrderNo;
	}

	public void setMarkSubOrderNo(String markSubOrderNo) {
		this.markSubOrderNo = markSubOrderNo;
	}
}