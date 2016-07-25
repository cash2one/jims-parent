/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 检查治疗医嘱明细记录Entity
 * @author zhaoning
 * @version 2016-04-25
 */
public class OutpTreatRec extends DataEntity<OutpTreatRec> {
	
	private static final long serialVersionUID = 1L;
	private Date visitDate;		// 就诊日期
    private String orgId;    //组织机构id
    private String clinicId;		//就诊id
	private Integer visitNo;		// 就诊序号
	private String serialNo;		// 流水号
	private Integer itemNo;		// 项目序号
	private String itemClass;		// 项目类别
	private String itemCode;		// 项目编码
	private String itemName;		// 项目名称
	private String itemSpec;		// 项目规格
	private String appoint;		//申请序号
	private String units;		// 单位
	private Double amount;		// 数量
	private String frequency;		// 频次
	private String performedBy;		// 执行科室
	private Double costs;		// 计价费用
	private Double charges;		// 实收费用
	private Integer chargeIndicator;		// 收费标记
	private String appointNo;		// 申请号
	private Integer appointItemNo;		// 申请明细号
	private String freqDetail;		// 执行时间详细描述
	private String wardCode;		// 护理单元
	private String explanation;		// 申请退费理由
	private String rcptNo;		// 收费单据号
	private Integer billVisitNo;		// 收费序号
	private Date billVisitDate;		// 收费日期
	private String operatorTrturn;		// 退费医生
	private Date dateTrturn;		// 退费日期
	
	public OutpTreatRec() {
		super();
	}

	public OutpTreatRec(String id){
		super(id);
	}

	public String getAppoint() {
		return appoint;
	}

	public void setAppoint(String appoint) {
		this.appoint = appoint;
	}

	public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getVisitDate() {
		return visitDate;
	}

	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	public Integer getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	
	@Length(min=0, max=20, message="流水号长度必须介于 0 和 20 之间")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}
	
	@Length(min=0, max=128, message="项目类别长度必须介于 0 和 128 之间")
	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	
	@Length(min=0, max=20, message="项目编码长度必须介于 0 和 20 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	@Length(min=0, max=100, message="项目名称长度必须介于 0 和 100 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=0, max=50, message="项目规格长度必须介于 0 和 50 之间")
	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}
	
	@Length(min=0, max=8, message="单位长度必须介于 0 和 8 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=16, message="频次长度必须介于 0 和 16 之间")
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	@Length(min=0, max=8, message="执行科室长度必须介于 0 和 8 之间")
	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}
	
	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}
	
	public Double getCharges() {
		return charges;
	}

	public void setCharges(Double charges) {
		this.charges = charges;
	}
	
	public Integer getChargeIndicator() {
		return chargeIndicator;
	}

	public void setChargeIndicator(Integer chargeIndicator) {
		this.chargeIndicator = chargeIndicator;
	}
	
	@Length(min=0, max=10, message="申请号长度必须介于 0 和 10 之间")
	public String getAppointNo() {
		return appointNo;
	}

	public void setAppointNo(String appointNo) {
		this.appointNo = appointNo;
	}
	
	public Integer getAppointItemNo() {
		return appointItemNo;
	}

	public void setAppointItemNo(Integer appointItemNo) {
		this.appointItemNo = appointItemNo;
	}
	
	@Length(min=0, max=80, message="执行时间详细描述长度必须介于 0 和 80 之间")
	public String getFreqDetail() {
		return freqDetail;
	}

	public void setFreqDetail(String freqDetail) {
		this.freqDetail = freqDetail;
	}
	
	@Length(min=0, max=20, message="护理单元长度必须介于 0 和 20 之间")
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	@Length(min=0, max=100, message="申请退费理由长度必须介于 0 和 100 之间")
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	
	@Length(min=0, max=20, message="收费单据号长度必须介于 0 和 20 之间")
	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}
	
	public Integer getBillVisitNo() {
		return billVisitNo;
	}

	public void setBillVisitNo(Integer billVisitNo) {
		this.billVisitNo = billVisitNo;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getBillVisitDate() {
		return billVisitDate;
	}
	@JsonDeserialize(using = CustomDateDeSerializer.class)
	public void setBillVisitDate(Date billVisitDate) {
		this.billVisitDate = billVisitDate;
	}
	
	@Length(min=0, max=20, message="退费医生长度必须介于 0 和 20 之间")
	public String getOperatorTrturn() {
		return operatorTrturn;
	}

	public void setOperatorTrturn(String operatorTrturn) {
		this.operatorTrturn = operatorTrturn;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getDateTrturn() {
		return dateTrturn;
	}

	public void setDateTrturn(Date dateTrturn) {
		this.dateTrturn = dateTrturn;
	}
	
}