package com.jims.nurse.entity;


import com.jims.common.persistence.DataEntity;

/**
 * 床位记录Entity
 * @author pq
 * @version 2016-06-02
 */
public class BedRec extends DataEntity<BedRec> {
	
	private static final long serialVersionUID = 1L;
	private String wardCode;		// 病房（护理单元）代码
	private Integer bedNo;		// 床号
	private String bedLabel;		// 床标号
	private String roomNo;		// 房间
	private String deptCode;		// 所属科室代码
	private String bedApprovedType;		// 床位编制类型
	private String bedSexType;		// 床位类型
	private String bedClass;		// 床位等级
	private String bedStatus;		// 床位状态
	private String lendAttr;		// lend_attr
	private Integer lendBedNo;		// lend_bed_no
	private String lendBedDept;		// lend_bed_dept
	private String lendBedWard;		// 借床标志
	private String lockStatus;		// 是否锁住床位
	private String lockOperator;		// 锁床位操作员
	private String airconditionClass;		// 空调类型
	private String patientId;		// patient_id
	
	public BedRec() {
		super();
	}

	public BedRec(String id){
		super(id);
	}


	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}


	public Integer getBedNo() {
		return bedNo;
	}

	public void setBedNo(Integer bedNo) {
		this.bedNo = bedNo;
	}


	public String getBedLabel() {
		return bedLabel;
	}

	public void setBedLabel(String bedLabel) {
		this.bedLabel = bedLabel;
	}


	public String getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}


	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}


	public String getBedApprovedType() {
		return bedApprovedType;
	}

	public void setBedApprovedType(String bedApprovedType) {
		this.bedApprovedType = bedApprovedType;
	}


	public String getBedSexType() {
		return bedSexType;
	}

	public void setBedSexType(String bedSexType) {
		this.bedSexType = bedSexType;
	}


	public String getBedClass() {
		return bedClass;
	}

	public void setBedClass(String bedClass) {
		this.bedClass = bedClass;
	}


	public String getBedStatus() {
		return bedStatus;
	}

	public void setBedStatus(String bedStatus) {
		this.bedStatus = bedStatus;
	}


	public String getLendAttr() {
		return lendAttr;
	}

	public void setLendAttr(String lendAttr) {
		this.lendAttr = lendAttr;
	}


	public Integer getLendBedNo() {
		return lendBedNo;
	}

	public void setLendBedNo(Integer lendBedNo) {
		this.lendBedNo = lendBedNo;
	}


	public String getLendBedDept() {
		return lendBedDept;
	}

	public void setLendBedDept(String lendBedDept) {
		this.lendBedDept = lendBedDept;
	}


	public String getLendBedWard() {
		return lendBedWard;
	}

	public void setLendBedWard(String lendBedWard) {
		this.lendBedWard = lendBedWard;
	}


	public String getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(String lockStatus) {
		this.lockStatus = lockStatus;
	}


	public String getLockOperator() {
		return lockOperator;
	}

	public void setLockOperator(String lockOperator) {
		this.lockOperator = lockOperator;
	}


	public String getAirconditionClass() {
		return airconditionClass;
	}

	public void setAirconditionClass(String airconditionClass) {
		this.airconditionClass = airconditionClass;
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

}