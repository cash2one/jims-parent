/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;


import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateDeSerializer;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 在院病人记录Entity
 * @author pq
 * @version 2016-05-12
 */
public class PatsInHospital extends DataEntity<PatsInHospital> {
	
	private static final long serialVersionUID = 1L;
	private String hosid;		// 医院ID
	private Integer visitId;		// 病人本次住院标识
	private String wardCode;		// 所在病房代码
	private String deptCode;		// 所在科室代码
	private Integer bedNo;		// 床号
	private Date admissionDateTime;		// 入院日期及时间
	private Date admWardDateTime;		// 入科日期及时间
	private String diagnosis;		// 主要诊断
	private String patientCondition;		// 病情状态
	private String nursingClass;		// 护理等级
	private String doctorInCharge;		// 经治医生
	private Date operatingDate;		// 手术日期
	private Date billingDateTime;		// 计价截止日期及时间
	private Double prepayments;		// 预交金余额
	private Double totalCosts;		// 累计未结费用
	private Double totalCharges;		// 优惠后未结费用
	private String guarantor;		// 经济担保人
	private String guarantorOrg;		// 经济担保人所在单位
	private String guarantorPhoneNum;		// 经济担保人联系电话
	private Date billCheckedDateTime;		// 上次划价检查日期
	private Integer settledIndicator;		// 出院结算标记
	private Integer lendBedNo;		// 借床床位号
	private String bedDeptCode;		// 床位科室代码
	private String bedWardCode;		// 床位护理单元
	private String deptCodeLend;		// 借床科室
	private Integer lendIndicator;		// 借床标志
	private Integer isNewborn;		// 是否新生儿
	private String orgId;		// 组织机构ID
	private String clinicId;		// 就诊记录ID
	private String patientId;		// 病人主表ID
	
	public PatsInHospital() {
		super();
	}

	public PatsInHospital(String id){
		super(id);
	}

	@Length(min=0, max=128, message="医院ID长度必须介于 0 和 128 之间")
	public String getHosid() {
		return hosid;
	}

	public void setHosid(String hosid) {
		this.hosid = hosid;
	}
	
	public Integer getVisitId() {
		return visitId;
	}

	public void setVisitId(Integer visitId) {
		this.visitId = visitId;
	}
	
	@Length(min=0, max=20, message="所在病房代码长度必须介于 0 和 20 之间")
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	@Length(min=0, max=20, message="所在科室代码长度必须介于 0 和 20 之间")
	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	
	public Integer getBedNo() {
		return bedNo;
	}

	public void setBedNo(Integer bedNo) {
		this.bedNo = bedNo;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAdmissionDateTime() {
		return admissionDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setAdmissionDateTime(Date admissionDateTime) {
		this.admissionDateTime = admissionDateTime;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAdmWardDateTime() {
		return admWardDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setAdmWardDateTime(Date admWardDateTime) {
		this.admWardDateTime = admWardDateTime;
	}
	
	@Length(min=0, max=200, message="主要诊断长度必须介于 0 和 200 之间")
	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	
	@Length(min=0, max=128, message="病情状态长度必须介于 0 和 128 之间")
	public String getPatientCondition() {
		return patientCondition;
	}

	public void setPatientCondition(String patientCondition) {
		this.patientCondition = patientCondition;
	}
	
	@Length(min=0, max=128, message="护理等级长度必须介于 0 和 128 之间")
	public String getNursingClass() {
		return nursingClass;
	}

	public void setNursingClass(String nursingClass) {
		this.nursingClass = nursingClass;
	}
	
	@Length(min=0, max=40, message="经治医生长度必须介于 0 和 40 之间")
	public String getDoctorInCharge() {
		return doctorInCharge;
	}

	public void setDoctorInCharge(String doctorInCharge) {
		this.doctorInCharge = doctorInCharge;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getOperatingDate() {
		return operatingDate;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setOperatingDate(Date operatingDate) {
		this.operatingDate = operatingDate;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getBillingDateTime() {
		return billingDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setBillingDateTime(Date billingDateTime) {
		this.billingDateTime = billingDateTime;
	}
	
	public Double getPrepayments() {
		return prepayments;
	}

	public void setPrepayments(Double prepayments) {
		this.prepayments = prepayments;
	}
	
	public Double getTotalCosts() {
		return totalCosts;
	}

	public void setTotalCosts(Double totalCosts) {
		this.totalCosts = totalCosts;
	}
	
	public Double getTotalCharges() {
		return totalCharges;
	}

	public void setTotalCharges(Double totalCharges) {
		this.totalCharges = totalCharges;
	}
	
	@Length(min=0, max=16, message="经济担保人长度必须介于 0 和 16 之间")
	public String getGuarantor() {
		return guarantor;
	}

	public void setGuarantor(String guarantor) {
		this.guarantor = guarantor;
	}
	
	@Length(min=0, max=80, message="经济担保人所在单位长度必须介于 0 和 80 之间")
	public String getGuarantorOrg() {
		return guarantorOrg;
	}

	public void setGuarantorOrg(String guarantorOrg) {
		this.guarantorOrg = guarantorOrg;
	}
	
	@Length(min=0, max=32, message="经济担保人联系电话长度必须介于 0 和 32 之间")
	public String getGuarantorPhoneNum() {
		return guarantorPhoneNum;
	}

	public void setGuarantorPhoneNum(String guarantorPhoneNum) {
		this.guarantorPhoneNum = guarantorPhoneNum;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getBillCheckedDateTime() {
		return billCheckedDateTime;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setBillCheckedDateTime(Date billCheckedDateTime) {
		this.billCheckedDateTime = billCheckedDateTime;
	}
	
	public Integer getSettledIndicator() {
		return settledIndicator;
	}

	public void setSettledIndicator(Integer settledIndicator) {
		this.settledIndicator = settledIndicator;
	}
	
	public Integer getLendBedNo() {
		return lendBedNo;
	}

	public void setLendBedNo(Integer lendBedNo) {
		this.lendBedNo = lendBedNo;
	}
	
	@Length(min=0, max=20, message="床位科室代码长度必须介于 0 和 20 之间")
	public String getBedDeptCode() {
		return bedDeptCode;
	}

	public void setBedDeptCode(String bedDeptCode) {
		this.bedDeptCode = bedDeptCode;
	}
	
	@Length(min=0, max=20, message="床位护理单元长度必须介于 0 和 20 之间")
	public String getBedWardCode() {
		return bedWardCode;
	}

	public void setBedWardCode(String bedWardCode) {
		this.bedWardCode = bedWardCode;
	}
	
	@Length(min=0, max=20, message="借床科室长度必须介于 0 和 20 之间")
	public String getDeptCodeLend() {
		return deptCodeLend;
	}

	public void setDeptCodeLend(String deptCodeLend) {
		this.deptCodeLend = deptCodeLend;
	}
	
	public Integer getLendIndicator() {
		return lendIndicator;
	}

	public void setLendIndicator(Integer lendIndicator) {
		this.lendIndicator = lendIndicator;
	}
	
	public Integer getIsNewborn() {
		return isNewborn;
	}

	public void setIsNewborn(Integer isNewborn) {
		this.isNewborn = isNewborn;
	}
	
	@Length(min=0, max=6, message="组织机构ID长度必须介于 0 和 6 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
	@Length(min=0, max=6, message="就诊记录ID长度必须介于 0 和 6 之间")
	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
	
	@Length(min=0, max=6, message="病人主表ID长度必须介于 0 和 6 之间")
	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
}