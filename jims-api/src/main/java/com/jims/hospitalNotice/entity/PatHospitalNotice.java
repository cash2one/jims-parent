package com.jims.hospitalNotice.entity;
import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateDeSerializer;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 住院通知单Entity
 * @author qinlongxin
 * @version 2016-06-01
 */
public class PatHospitalNotice extends DataEntity<PatHospitalNotice> {
	
	private static final long serialVersionUID = 1L;
	private String clinicId;		//就诊记录ID
	private String orgId;		// 机构ID
	private String patientId;		// 病人ID
	private String name;		// 姓名
	private String sex;		// 性别
	private String age;		// 年龄
	private Date dateOfBirth;		// 出生日期
	private String maritalStatus;		// 婚姻
	private String occupation;		// 职业
	private String nation;		// 民族
	private String idNo;		// 身份证号
	private Long children;		// 儿童
	private String birthPlace;		// 出生地
	private String serviceAgency;		// 工作单位
	private String serviceAgencyPhone;		// 电话
	private String insuranceAera;		// 现住址
	private String insuranceAeraPhone;		// 电话
	private String nextOfKin;		// 联系人
	private String relationship;		// 关系
	private String nextOfNation;		// 联系人民族
	private String nextOfIdNo;		// 联系人身份证
	private String nextOfKinAddr;		// 联系人地址
	private Date admissionDateTime;		// 入院日期
	private String prepaidFee;		// 预交住院费
	private String patAdmCondition;		// 住院情况
	private String diagnosisDesc;		// 门诊诊断
	private String deptAdmissionTo;		// 入院科室
	private Long bedNo;		// 床号
	private String strictSegregation;		// 严格隔离
	private String commonSegregation;		// 普通隔离
	private String notSegregation;		// 不隔离
	private String notes;		// 注意事项
	private Long noticeId;		// 通知单次数
	private Long visitId;		// 住院标识
	private String operator;		// 录入人
	private Date enterDate;		// 录入时间
	private Date onsetDate;		// 发病日期
	private Long parityNo;		// 胎次
	private String chargeType;		// 费别
	private String deptAdmissionFrom;		// 申请科室
	
	public PatHospitalNotice() {
		super();
	}

	public PatHospitalNotice(String id){
		super(id);
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}


	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}


	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}


	public Long getChildren() {
		return children;
	}

	public void setChildren(Long children) {
		this.children = children;
	}


	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}


	public String getServiceAgency() {
		return serviceAgency;
	}

	public void setServiceAgency(String serviceAgency) {
		this.serviceAgency = serviceAgency;
	}


	public String getServiceAgencyPhone() {
		return serviceAgencyPhone;
	}

	public void setServiceAgencyPhone(String serviceAgencyPhone) {
		this.serviceAgencyPhone = serviceAgencyPhone;
	}


	public String getInsuranceAera() {
		return insuranceAera;
	}

	public void setInsuranceAera(String insuranceAera) {
		this.insuranceAera = insuranceAera;
	}


	public String getInsuranceAeraPhone() {
		return insuranceAeraPhone;
	}

	public void setInsuranceAeraPhone(String insuranceAeraPhone) {
		this.insuranceAeraPhone = insuranceAeraPhone;
	}


	public String getNextOfKin() {
		return nextOfKin;
	}

	public void setNextOfKin(String nextOfKin) {
		this.nextOfKin = nextOfKin;
	}


	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}


	public String getNextOfNation() {
		return nextOfNation;
	}

	public void setNextOfNation(String nextOfNation) {
		this.nextOfNation = nextOfNation;
	}


	public String getNextOfIdNo() {
		return nextOfIdNo;
	}

	public void setNextOfIdNo(String nextOfIdNo) {
		this.nextOfIdNo = nextOfIdNo;
	}


	public String getNextOfKinAddr() {
		return nextOfKinAddr;
	}

	public void setNextOfKinAddr(String nextOfKinAddr) {
		this.nextOfKinAddr = nextOfKinAddr;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getAdmissionDateTime() {
		return admissionDateTime;
	}

	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setAdmissionDateTime(Date admissionDateTime) {
		this.admissionDateTime = admissionDateTime;
	}


	public String getPrepaidFee() {
		return prepaidFee;
	}

	public void setPrepaidFee(String prepaidFee) {
		this.prepaidFee = prepaidFee;
	}


	public String getPatAdmCondition() {
		return patAdmCondition;
	}

	public void setPatAdmCondition(String patAdmCondition) {
		this.patAdmCondition = patAdmCondition;
	}


	public String getDiagnosisDesc() {
		return diagnosisDesc;
	}

	public void setDiagnosisDesc(String diagnosisDesc) {
		this.diagnosisDesc = diagnosisDesc;
	}


	public String getDeptAdmissionTo() {
		return deptAdmissionTo;
	}

	public void setDeptAdmissionTo(String deptAdmissionTo) {
		this.deptAdmissionTo = deptAdmissionTo;
	}


	public Long getBedNo() {
		return bedNo;
	}

	public void setBedNo(Long bedNo) {
		this.bedNo = bedNo;
	}


	public String getStrictSegregation() {
		return strictSegregation;
	}

	public void setStrictSegregation(String strictSegregation) {
		this.strictSegregation = strictSegregation;
	}


	public String getCommonSegregation() {
		return commonSegregation;
	}

	public void setCommonSegregation(String commonSegregation) {
		this.commonSegregation = commonSegregation;
	}


	public String getNotSegregation() {
		return notSegregation;
	}

	public void setNotSegregation(String notSegregation) {
		this.notSegregation = notSegregation;
	}


	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}


	public Long getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(Long noticeId) {
		this.noticeId = noticeId;
	}


	public Long getVisitId() {
		return visitId;
	}

	public void setVisitId(Long visitId) {
		this.visitId = visitId;
	}


	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEnterDate() {
		return enterDate;
	}

	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getOnsetDate() {
		return onsetDate;
	}

	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setOnsetDate(Date onsetDate) {
		this.onsetDate = onsetDate;
	}


	public Long getParityNo() {
		return parityNo;
	}

	public void setParityNo(Long parityNo) {
		this.parityNo = parityNo;
	}


	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}


	public String getDeptAdmissionFrom() {
		return deptAdmissionFrom;
	}

	public void setDeptAdmissionFrom(String deptAdmissionFrom) {
		this.deptAdmissionFrom = deptAdmissionFrom;
	}
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }
}