package com.jims.register.entity;


import com.jims.common.persistence.DataEntity;

/**
 * 号类字典Entity
 * @author 张耀
 * @version 2016-05-16
 */
public class ClinicTypeSetting extends DataEntity<ClinicTypeSetting> {
	
	private static final long serialVersionUID = 1L;
	private String orgId;		// 所属组织
	private String clinicTypeName;		// 号类名称
	private String clinicTypeCode;		// 号类代码
	
	public ClinicTypeSetting() {
		super();
	}

	public ClinicTypeSetting(String id){
		super(id);
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}


	public String getClinicTypeName() {
		return clinicTypeName;
	}

	public void setClinicTypeName(String clinicTypeName) {
		this.clinicTypeName = clinicTypeName;
	}


	public String getClinicTypeCode() {
		return clinicTypeCode;
	}

	public void setClinicTypeCode(String clinicTypeCode) {
		this.clinicTypeCode = clinicTypeCode;
	}

}