package com.jims.demo.entity;




import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * HospitalDict entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "HOSPITAL_DICT", schema = "JIMS")
@XmlRootElement
public class HospitalDict implements java.io.Serializable {

	// Fields

	private String id;
	private String hospitalName;
    private String parentHospital ;
	private String unitCode;
	private String location;
	private String zipCode;
	private String organizationFullCode;
    private String inputCode ;

	// Constructors

	/** default constructor */
	public HospitalDict() {
	}

    @PrePersist
    public void prePersist(){
    }

	/** full constructor */
	public HospitalDict(String hospitalName,
                        String parentHospital, String unitCode, String location, String zipCode,
                        String organizationFullCode, String inputCode) {
		this.hospitalName = hospitalName;
        this.parentHospital = parentHospital;
        this.unitCode = unitCode;
		this.location = location;
		this.zipCode = zipCode;
		this.organizationFullCode = organizationFullCode;
        this.inputCode = inputCode;
    }

	// Property accessors
    @GenericGenerator(name = "generator", strategy = "uuid.hex")
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", unique = true, nullable = false, length = 64)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

    @Column(name = "parent_hospital")
    public String getParentHospital() {
        return parentHospital;
    }

    public void setParentHospital(String parentHospital) {
        this.parentHospital = parentHospital;
    }

    @Column(name = "hospital_name", length = 100)
	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	@Column(name = "unit_code", length = 11)
	public String getUnitCode() {
		return this.unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	@Column(name = "location", length = 100)
	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "zip_code", length = 6)
	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Column(name = "ORGANIZATION_FULL_CODE", length = 30)
	public String getOrganizationFullCode() {
		return this.organizationFullCode;
	}

	public void setOrganizationFullCode(String organizationFullCode) {
		this.organizationFullCode = organizationFullCode;
	}

    @Transient
    public String getInputCode() {
        return inputCode;
    }

    public void setInputCode(String inputCode) {
        this.inputCode = inputCode;
    }




}