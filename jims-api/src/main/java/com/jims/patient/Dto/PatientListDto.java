package com.jims.patient.Dto;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * Created by Administrator on 2016/5/13.
 * 病人列表DTO 封装
 * @author zhaoning
 */
public class PatientListDto extends DataEntity<PatientListDto> {
    private String patientId;//病人ID
    private String inpNo;//病人住院号
    private String bedNo;//住院床号
    private String patType;	   //住院状态
    private String deptCode;  //所在科室
    private String name;      // 姓名
    private String sex;  // 性别
    private String age;//年龄
    private String chargeType;		//费别
    private String inpCount;//病人住院天数
    private String tubeBedDoctor;//管床医生
    private String admissionDateTime;	//入院时间
    private String superiorDoctor;//上级医师
    private String attendingDoctor;//主治医师
    private Date dataOfbith;//出生日期
    private String patIdentity;		//身份
    private String patientCondition;// 病情
    private String wardCode;	//护理单元(病房代码)
    private String diagnosis;		// 主要诊断
    private Date dischargeDateTime;//出院时间
    private Double prepayments;//预交金余额

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getInpNo() {
        return inpNo;
    }

    public void setInpNo(String inpNo) {
        this.inpNo = inpNo;
    }

    public String getBedNo() {
        return bedNo;
    }

    public void setBedNo(String bedNo) {
        this.bedNo = bedNo;
    }

    public String getPatType() {
        return patType;
    }

    public void setPatType(String patType) {
        this.patType = patType;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
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

    public String getChargeType() {
        return chargeType;
    }

    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public String getInpCount() {
        return inpCount;
    }

    public void setInpCount(String inpCount) {
        this.inpCount = inpCount;
    }

    public String getTubeBedDoctor() {
        return tubeBedDoctor;
    }

    public void setTubeBedDoctor(String tubeBedDoctor) {
        this.tubeBedDoctor = tubeBedDoctor;
    }

    public String getAdmissionDateTime() {
        return admissionDateTime;
    }

    public void setAdmissionDateTime(String admissionDateTime) {
        this.admissionDateTime = admissionDateTime;
    }

    public String getSuperiorDoctor() {
        return superiorDoctor;
    }

    public void setSuperiorDoctor(String superiorDoctor) {
        this.superiorDoctor = superiorDoctor;
    }

    public String getAttendingDoctor() {
        return attendingDoctor;
    }

    public void setAttendingDoctor(String attendingDoctor) {
        this.attendingDoctor = attendingDoctor;
    }

    public Date getDataOfbith() {
        return dataOfbith;
    }

    public void setDataOfbith(Date dataOfbith) {
        this.dataOfbith = dataOfbith;
    }

    public String getPatIdentity() {
        return patIdentity;
    }

    public void setPatIdentity(String patIdentity) {
        this.patIdentity = patIdentity;
    }

    public String getPatientCondition() {
        return patientCondition;
    }

    public void setPatientCondition(String patientCondition) {
        this.patientCondition = patientCondition;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public Date getDischargeDateTime() {
        return dischargeDateTime;
    }

    public void setDischargeDateTime(Date dischargeDateTime) {
        this.dischargeDateTime = dischargeDateTime;
    }

    public Double getPrepayments() {
        return prepayments;
    }

    public void setPrepayments(Double prepayments) {
        this.prepayments = prepayments;
    }
}
