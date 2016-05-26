/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.patient.entity;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.utils.CustomDateDeSerializer;
import com.jims.common.utils.CustomDateSerializer;
import com.jims.register.entity.ClinicAppoints;
import com.jims.register.entity.ClinicForRegist;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;;
import org.hibernate.validator.constraints.Length;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 病人主索引Entity
 * @author zhaoning
 * @version 2016-04-19
 */
public class PatMasterIndex extends DataEntity<PatMasterIndex> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 医院ID
	private String inpNo;		// 住院号
	private String name;		// 姓名
	private String namePhonetic;		// 姓名拼音
	private String sex;		// 性别
	private Date dateOfBirth;		// 出生日期
	private String birthPlace;		// 出生地
	private String citizenship;		// 国籍
	private String nation;		// 民族
	private String idNo;		// 身份证号
	private String identity;		// 身份
	private String chargeType;		// 费别
	private String unitInContract;		// 合同单位
	private String mailingAddress;		// （通信地址）户口地址
	private String zipCode;		// 邮政编码
	private String phoneNumberHome;		// 家庭电话号码
	private String phoneNumberBusiness;		// 单位电话号码
	private String nextOfKin;		// 联系人姓名
	private String relationship;		// 与联系人关系
	private String nextOfKinAddr;		// 联系人地址
	private String nextOfKinZipCode;		// 联系人邮政编码
	private String nextOfKinPhone;		// 联系人电话号码
	private Date lastVisitDate;		// 上次就诊日期
	private Integer vipIndicator;		// 重要人物标志
	private String operator;		// 操作员
	private String serviceAgency;		// 医疗体系病人标志
	private String businessZipCode;		// business_zip_code
	private String photo;		// UUF,
	private String patientClass;		// patient_class
	private String degree;		// degree
	private String race;		// race
	private String religion;		// religion
	private String motherLanguage;		// mother_language
	private String foreignLanguage;		// foreign_language
	private String idType;		// id_type
	private String vipNo;		// vip_no
	private String eName;		// e_name
	private String occupation;		// occupation
	private String nextOfId;		// next_of_id
	private String nextOfBath;		// next_of_bath
	private String nextOfSex;		// next_of_sex
	private String insurNhNo;		// insur_nh_no
	private String insuranceNo;		// insurance_no
	private String alergyDrugs;		// alergy_drugs
	private String nativePlace;		// native_place
	private String mailingAddressCode;		// 户口地址行政区划
	private String healthyCardNo;		// healthy_card_no
	private String addressNow;		// 现住址
	private PatVisit patVisit;//病人住院记录信息
	private ClinicMaster clinicMaster;//病人就诊记录
	private String patientId; //病人id


	private String age;//年龄

	private List<ClinicForRegist> clinicForRegistList;
	private List<ClinicAppoints> clinicAppointses;

	public PatMasterIndex() {
		super();
	}

	public PatMasterIndex(String id){
		super(id);
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getInpNo() {
		return inpNo;
	}

	public void setInpNo(String inpNo) {
		this.inpNo = inpNo;
	}
	
	@Length(min=0, max=40, message="姓名长度必须介于 0 和 40 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=32, message="姓名拼音长度必须介于 0 和 32 之间")
	public String getNamePhonetic() {
		return namePhonetic;
	}

	public void setNamePhonetic(String namePhonetic) {
		this.namePhonetic = namePhonetic;
	}
	
	@Length(min=0, max=8, message="性别长度必须介于 0 和 8 之间")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	

	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	@Length(min=0, max=200, message="出生地长度必须介于 0 和 200 之间")
	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	
	@Length(min=0, max=4, message="国籍长度必须介于 0 和 4 之间")
	public String getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	
	@Length(min=0, max=20, message="民族长度必须介于 0 和 20 之间")
	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	@Length(min=0, max=36, message="身份证号长度必须介于 0 和 36 之间")
	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
	@Length(min=0, max=20, message="身份长度必须介于 0 和 20 之间")
	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	@Length(min=0, max=16, message="费别长度必须介于 0 和 16 之间")
	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}
	
	@Length(min=0, max=80, message="合同单位长度必须介于 0 和 80 之间")
	public String getUnitInContract() {
		return unitInContract;
	}

	public void setUnitInContract(String unitInContract) {
		this.unitInContract = unitInContract;
	}
	
	@Length(min=0, max=400, message="（通信地址）户口地址长度必须介于 0 和 400 之间")
	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	
	@Length(min=0, max=12, message="邮政编码长度必须介于 0 和 12 之间")
	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	@Length(min=0, max=32, message="家庭电话号码长度必须介于 0 和 32 之间")
	public String getPhoneNumberHome() {
		return phoneNumberHome;
	}

	public void setPhoneNumberHome(String phoneNumberHome) {
		this.phoneNumberHome = phoneNumberHome;
	}
	
	@Length(min=0, max=32, message="单位电话号码长度必须介于 0 和 32 之间")
	public String getPhoneNumberBusiness() {
		return phoneNumberBusiness;
	}

	public void setPhoneNumberBusiness(String phoneNumberBusiness) {
		this.phoneNumberBusiness = phoneNumberBusiness;
	}
	
	@Length(min=0, max=40, message="联系人姓名长度必须介于 0 和 40 之间")
	public String getNextOfKin() {
		return nextOfKin;
	}

	public void setNextOfKin(String nextOfKin) {
		this.nextOfKin = nextOfKin;
	}
	
	@Length(min=0, max=4, message="与联系人关系长度必须介于 0 和 4 之间")
	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	@Length(min=0, max=200, message="联系人地址长度必须介于 0 和 200 之间")
	public String getNextOfKinAddr() {
		return nextOfKinAddr;
	}

	public void setNextOfKinAddr(String nextOfKinAddr) {
		this.nextOfKinAddr = nextOfKinAddr;
	}
	
	@Length(min=0, max=12, message="联系人邮政编码长度必须介于 0 和 12 之间")
	public String getNextOfKinZipCode() {
		return nextOfKinZipCode;
	}

	public void setNextOfKinZipCode(String nextOfKinZipCode) {
		this.nextOfKinZipCode = nextOfKinZipCode;
	}
	
	@Length(min=0, max=32, message="联系人电话号码长度必须介于 0 和 32 之间")
	public String getNextOfKinPhone() {
		return nextOfKinPhone;
	}

	public void setNextOfKinPhone(String nextOfKinPhone) {
		this.nextOfKinPhone = nextOfKinPhone;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastVisitDate() {
		return lastVisitDate;
	}

	public void setLastVisitDate(Date lastVisitDate) {
		this.lastVisitDate = lastVisitDate;
	}
	
	public Integer getVipIndicator() {
		return vipIndicator;
	}

	public void setVipIndicator(Integer vipIndicator) {
		this.vipIndicator = vipIndicator;
	}
	
	@Length(min=0, max=40, message="操作员长度必须介于 0 和 40 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@Length(min=0, max=80, message="医疗体系病人标志长度必须介于 0 和 80 之间")
	public String getServiceAgency() {
		return serviceAgency;
	}

	public void setServiceAgency(String serviceAgency) {
		this.serviceAgency = serviceAgency;
	}
	
	@Length(min=0, max=12, message="business_zip_code长度必须介于 0 和 12 之间")
	public String getBusinessZipCode() {
		return businessZipCode;
	}

	public void setBusinessZipCode(String businessZipCode) {
		this.businessZipCode = businessZipCode;
	}
	
	@Length(min=0, max=40, message="UUF,长度必须介于 0 和 40 之间")
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	@Length(min=0, max=2, message="patient_class长度必须介于 0 和 2 之间")
	public String getPatientClass() {
		return patientClass;
	}

	public void setPatientClass(String patientClass) {
		this.patientClass = patientClass;
	}
	
	@Length(min=0, max=20, message="degree长度必须介于 0 和 20 之间")
	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@Length(min=0, max=20, message="race长度必须介于 0 和 20 之间")
	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
	
	@Length(min=0, max=32, message="religion长度必须介于 0 和 32 之间")
	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}
	
	@Length(min=0, max=32, message="mother_language长度必须介于 0 和 32 之间")
	public String getMotherLanguage() {
		return motherLanguage;
	}

	public void setMotherLanguage(String motherLanguage) {
		this.motherLanguage = motherLanguage;
	}
	
	@Length(min=0, max=32, message="foreign_language长度必须介于 0 和 32 之间")
	public String getForeignLanguage() {
		return foreignLanguage;
	}

	public void setForeignLanguage(String foreignLanguage) {
		this.foreignLanguage = foreignLanguage;
	}
	
	@Length(min=0, max=20, message="id_type长度必须介于 0 和 20 之间")
	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}
	
	@Length(min=0, max=36, message="vip_no长度必须介于 0 和 36 之间")
	public String getVipNo() {
		return vipNo;
	}

	public void setVipNo(String vipNo) {
		this.vipNo = vipNo;
	}
	
	@Length(min=0, max=200, message="e_name长度必须介于 0 和 200 之间")
	public String getEName() {
		return eName;
	}

	public void setEName(String eName) {
		this.eName = eName;
	}
	
	@Length(min=0, max=200, message="occupation长度必须介于 0 和 200 之间")
	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	@Length(min=0, max=36, message="next_of_id长度必须介于 0 和 36 之间")
	public String getNextOfId() {
		return nextOfId;
	}

	public void setNextOfId(String nextOfId) {
		this.nextOfId = nextOfId;
	}
	
	@Length(min=0, max=36, message="next_of_bath长度必须介于 0 和 36 之间")
	public String getNextOfBath() {
		return nextOfBath;
	}

	public void setNextOfBath(String nextOfBath) {
		this.nextOfBath = nextOfBath;
	}
	
	@Length(min=0, max=36, message="next_of_sex长度必须介于 0 和 36 之间")
	public String getNextOfSex() {
		return nextOfSex;
	}

	public void setNextOfSex(String nextOfSex) {
		this.nextOfSex = nextOfSex;
	}
	
	@Length(min=0, max=36, message="insur_nh_no长度必须介于 0 和 36 之间")
	public String getInsurNhNo() {
		return insurNhNo;
	}

	public void setInsurNhNo(String insurNhNo) {
		this.insurNhNo = insurNhNo;
	}
	
	@Length(min=0, max=36, message="insurance_no长度必须介于 0 和 36 之间")
	public String getInsuranceNo() {
		return insuranceNo;
	}

	public void setInsuranceNo(String insuranceNo) {
		this.insuranceNo = insuranceNo;
	}
	
	@Length(min=0, max=200, message="alergy_drugs长度必须介于 0 和 200 之间")
	public String getAlergyDrugs() {
		return alergyDrugs;
	}

	public void setAlergyDrugs(String alergyDrugs) {
		this.alergyDrugs = alergyDrugs;
	}
	
	@Length(min=0, max=12, message="native_place长度必须介于 0 和 12 之间")
	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	
	@Length(min=0, max=60, message="户口地址行政区划长度必须介于 0 和 60 之间")
	public String getMailingAddressCode() {
		return mailingAddressCode;
	}

	public void setMailingAddressCode(String mailingAddressCode) {
		this.mailingAddressCode = mailingAddressCode;
	}
	
	@Length(min=0, max=60, message="healthy_card_no长度必须介于 0 和 60 之间")
	public String getHealthyCardNo() {
		return healthyCardNo;
	}

	public void setHealthyCardNo(String healthyCardNo) {
		this.healthyCardNo = healthyCardNo;
	}
	
	@Length(min=0, max=400, message="现住址长度必须介于 0 和 400 之间")
	public String getAddressNow() {
		return addressNow;
	}

	public void setAddressNow(String addressNow) {
		this.addressNow = addressNow;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public PatVisit getPatVisit() {
		return patVisit;
	}

	public void setPatVisit(PatVisit patVisit) {
		this.patVisit = patVisit;
	}

	public ClinicMaster getClinicMaster() {
		return clinicMaster;
	}

	public void setClinicMaster(ClinicMaster clinicMaster) {
		this.clinicMaster = clinicMaster;
	}


	public static  String getAge(Date dateOfBirth) {
		if (dateOfBirth == null){
			throw new RuntimeException("出生日期不能为null");
		}
		int age = 0;
		Date now = new Date();
		SimpleDateFormat format_y = new SimpleDateFormat("yyyy");
		SimpleDateFormat format_M = new SimpleDateFormat("MM");

		String birth_year = format_y.format(dateOfBirth);
		String this_year = format_y.format(now);

		String birth_month = format_M.format(dateOfBirth);
		String this_month = format_M.format(now);
		// 初步，估算
		age =Integer.parseInt(this_year) - Integer.parseInt(birth_year);
		// 如果未到出生月份，则age - 1
		if(this_month.compareTo(birth_month) < 0) {
			age -= 1;
		}
		if (age <0) {
			age = 0;
		}
		return String.valueOf(age);
	}

	public void setAge(String age) {
		this.age = age;
	}

	public List<ClinicForRegist> getClinicForRegistList() {
		return clinicForRegistList;
	}

	public void setClinicForRegistList(List<ClinicForRegist> clinicForRegistList) {
		this.clinicForRegistList = clinicForRegistList;
	}

	public List<ClinicAppoints> getClinicAppointses() {
		return clinicAppointses;
	}

	public void setClinicAppointses(List<ClinicAppoints> clinicAppointses) {
		this.clinicAppointses = clinicAppointses;
	}

}