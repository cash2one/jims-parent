/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * ExamClassDictEntity
 * @author zhangpeng
 * @version 2016-04-26
 */
public class ExamClassDict extends DataEntity<ExamClassDict> {
	
	private static final long serialVersionUID = 1L;
	private Long serialNo;		// 序号 反映项目的排列顺序
	private String examClassCode;		// 检查类别代码
	private String examClassName;		// 检查类别名称
	private String inputCode;		// 输入码
	private String performBy;		// 检查部门代码
	private String printStyle;		// 打印单样式
	private Long specialtiesDept;		// 是否按样式单打印
	private String loacalIdClass;		// 检查号类别
//===========不知道这是什么=========
//	private String performedBy;		// 执行科室
//	private String seqName;		// 产生检查号时所用的序列
//=================================
    private String orgId;//机构id
    private String wardCode;		// 护理单元
    private String httpIp;		// 结果反馈地址
    private String memo;		// memo
    private String outpPerform;		// 适用于门诊检查不可选控制

	public ExamClassDict() {
		super();
	}

	public ExamClassDict(String id){
		super(id);
	}

	public Long getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	
	@Length(min=0, max=1, message="检查类别代码长度必须介于 0 和 1 之间")
	public String getExamClassCode() {
		return examClassCode;
	}

	public void setExamClassCode(String examClassCode) {
		this.examClassCode = examClassCode;
	}
	
	@Length(min=0, max=12, message="检查类别名称长度必须介于 0 和 12 之间")
	public String getExamClassName() {
		return examClassName;
	}

	public void setExamClassName(String examClassName) {
		this.examClassName = examClassName;
	}
	
	@Length(min=0, max=16, message="输入码长度必须介于 0 和 16 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=20, message="检查部门代码长度必须介于 0 和 20 之间")
	public String getPerformBy() {
		return performBy;
	}

	public void setPerformBy(String performBy) {
		this.performBy = performBy;
	}
	
	@Length(min=0, max=12, message="打印单样式长度必须介于 0 和 12 之间")
	public String getPrintStyle() {
		return printStyle;
	}

	public void setPrintStyle(String printStyle) {
		this.printStyle = printStyle;
	}
	
	public Long getSpecialtiesDept() {
		return specialtiesDept;
	}

	public void setSpecialtiesDept(Long specialtiesDept) {
		this.specialtiesDept = specialtiesDept;
	}
	
	@Length(min=0, max=20, message="检查号类别长度必须介于 0 和 20 之间")
	public String getLoacalIdClass() {
		return loacalIdClass;
	}

	public void setLoacalIdClass(String loacalIdClass) {
		this.loacalIdClass = loacalIdClass;
	}

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getWardCode() {
        return wardCode;
    }

    public void setWardCode(String wardCode) {
        this.wardCode = wardCode;
    }

    public String getHttpIp() {
        return httpIp;
    }

    public void setHttpIp(String httpIp) {
        this.httpIp = httpIp;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getOutpPerform() {
        return outpPerform;
    }

    public void setOutpPerform(String outpPerform) {
        this.outpPerform = outpPerform;
    }
}