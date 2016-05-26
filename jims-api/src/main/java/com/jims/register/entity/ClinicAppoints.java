package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.common.utils.CustomDateDeSerializer;
import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.util.Date;

/**
 * 预约挂号Entity
 * @author zhangyao
 * @version 2016-05-20
 */
public class ClinicAppoints extends DataEntity<ClinicAppoints> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 医院ID
	private Date visitDateAppted;		// 就诊日期
	private String clinicLabel;		// 号别
	private String patientId;		// 病人标识号
	private String visitTimeAppted;		// 预约就诊时间
	private Date apptMadeDate;		// 何日预约
	private String modeCode;		// 预约模式
	private String cardName;		// 卡名
	private String cardNo;		// 卡号
	private Integer serialNo;		// 流水号
	private String name;		// 姓名
	private String sex;		// 性别
	private Long age;		// 年龄
	private String idNo;		// 身份证号
	private String identity;		// 身份
	private String chargeType;		// 费别
	private String insuranceNo;		// 保险号码
	private String insuranceType;		// 保险类型
	private String unitInContract;		// 合同单位
	private String namePhonetic;		// 姓名拼音码

	private String clinicDept;
	private String doctor ;
	private String doctorTitle;
	private String clinicType;
	private String clinicPosition;
    private String masterId;
	public ClinicAppoints() {
		super();
	}

	public ClinicAppoints(String id){
		super(id);
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getVisitDateAppted() {
		return visitDateAppted;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setVisitDateAppted(Date visitDateAppted) {
		this.visitDateAppted = visitDateAppted;
	}


	public String getClinicLabel() {
		return clinicLabel;
	}

	public void setClinicLabel(String clinicLabel) {
		this.clinicLabel = clinicLabel;
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getVisitTimeAppted() {
		return visitTimeAppted;
	}

	public void setVisitTimeAppted(String visitTimeAppted) {
		this.visitTimeAppted = visitTimeAppted;
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getApptMadeDate() {
		return apptMadeDate;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setApptMadeDate(Date apptMadeDate) {
		this.apptMadeDate = apptMadeDate;
	}


	public String getModeCode() {
		return modeCode;
	}

	public void setModeCode(String modeCode) {
		this.modeCode = modeCode;
	}


	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}


	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public Integer getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Integer serialNo) {
		this.serialNo = serialNo;
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


	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}


	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}


	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}


	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}


	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}


	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}


	public String getUnitInContract() {
		return unitInContract;
	}

	public void setUnitInContract(String unitInContract) {
		this.unitInContract = unitInContract;
	}


	public String getNamePhonetic() {
		return namePhonetic;
	}

	public void setNamePhonetic(String namePhonetic) {
		this.namePhonetic = namePhonetic;
	}

	public String getClinicDept() {
		return clinicDept;
	}

	public void setClinicDept(String clinicDept) {
		this.clinicDept = clinicDept;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getDoctorTitle() {
		return doctorTitle;
	}

	public void setDoctorTitle(String doctorTitle) {
		this.doctorTitle = doctorTitle;
	}

	public String getClinicType() {
		return clinicType;
	}

	public void setClinicType(String clinicType) {
		this.clinicType = clinicType;
	}

	public String getClinicPosition() {
		return clinicPosition;
	}

	public void setClinicPosition(String clinicPosition) {
		this.clinicPosition = clinicPosition;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
}