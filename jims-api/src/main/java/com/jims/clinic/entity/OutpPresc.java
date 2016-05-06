/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 处方医嘱明细记录Entity
 * @author zhaoning
 * @version 2016-04-20
 */
public class OutpPresc extends DataEntity<OutpPresc> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String clinicId;//病人就诊记录ID
	private Date visitDate;		// 就诊日期
	private Integer visitNo;		// 就诊序号
	private String serialNo;		// 流水号
	private Integer prescNo;		// 处方序号
	private Integer itemNo;		// 项目序号
	private String itemClass;		// 项目类别
	private String drugCode;		// 药名编码
	private String drugName;		// 药品名称
	private String drugSpec;		// 药品规格
	private String firmId;		// 厂家标识
	private String units;		// 单位
	private Double amount;		// 数量
	private Double dosage;		// 一次用量
	private String dosageUnits;		// 用量单位
	private String administration;		// 用药途径
	private String frequency;		// 频次
	private Integer providedIndicator;		// 自备标记
	private Double costs;		// 计价金额
	private Double charges;		// 实收费用
	private Integer chargeIndicator;		// 收费标记
	private String dispensary;		// 摆药药局
	private Integer repetition;		// repetition
	private Integer orderNo;		// 医嘱组别
	private Integer subOrderNo;		// 子医嘱组别
	private String freqDetail;		// 执行时间详细描述
	private Integer getdrugFlag;		// 取药标志
	private String prescAttr;		// 处方属性
	private Integer abidance;		// abidance
	private String performNurse;		// perform_nurse
	private String performResult;		// perform_result
	private String skinFlag;		// skin_flag
	private Integer prescPsno;		// presc_psno
	private String skinResult;		// skin_result
    private List<OutpPresc> list;
	
	public OutpPresc() {
		super();
	}

	public OutpPresc(String id){
		super(id);
	}

	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	public Integer getVisitNo() {
		return visitNo;
	}

	public void setVisitNo(Integer visitNo) {
		this.visitNo = visitNo;
	}
	
	@Length(min=1, max=128, message="流水号长度必须介于 1 和 128 之间")
	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
	
	@NotNull(message="处方序号不能为空")
	public Integer getPrescNo() {
		return prescNo;
	}

	public void setPrescNo(Integer prescNo) {
		this.prescNo = prescNo;
	}
	
	@NotNull(message="项目序号不能为空")
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
	
	@Length(min=0, max=128, message="药名编码长度必须介于 0 和 128 之间")
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Length(min=0, max=200, message="药品名称长度必须介于 0 和 200 之间")
	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	
	@Length(min=0, max=128, message="药品规格长度必须介于 0 和 128 之间")
	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	
	@Length(min=0, max=200, message="厂家标识长度必须介于 0 和 200 之间")
	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	
	@Length(min=0, max=128, message="单位长度必须介于 0 和 128 之间")
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
	
	public Double getDosage() {
		return dosage;
	}

	public void setDosage(Double dosage) {
		this.dosage = dosage;
	}
	
	@Length(min=0, max=128, message="用量单位长度必须介于 0 和 128 之间")
	public String getDosageUnits() {
		return dosageUnits;
	}

	public void setDosageUnits(String dosageUnits) {
		this.dosageUnits = dosageUnits;
	}
	
	@Length(min=0, max=200, message="用药途径长度必须介于 0 和 200 之间")
	public String getAdministration() {
		return administration;
	}

	public void setAdministration(String administration) {
		this.administration = administration;
	}
	
	@Length(min=0, max=128, message="频次长度必须介于 0 和 128 之间")
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
	public Integer getProvidedIndicator() {
		return providedIndicator;
	}

	public void setProvidedIndicator(Integer providedIndicator) {
		this.providedIndicator = providedIndicator;
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
	
	@Length(min=0, max=128, message="摆药药局长度必须介于 0 和 128 之间")
	public String getDispensary() {
		return dispensary;
	}

	public void setDispensary(String dispensary) {
		this.dispensary = dispensary;
	}
	
	public Integer getRepetition() {
		return repetition;
	}

	public void setRepetition(Integer repetition) {
		this.repetition = repetition;
	}
	
	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	
	public Integer getSubOrderNo() {
		return subOrderNo;
	}

	public void setSubOrderNo(Integer subOrderNo) {
		this.subOrderNo = subOrderNo;
	}
	
	@Length(min=0, max=160, message="执行时间详细描述长度必须介于 0 和 160 之间")
	public String getFreqDetail() {
		return freqDetail;
	}

	public void setFreqDetail(String freqDetail) {
		this.freqDetail = freqDetail;
	}
	
	public Integer getGetdrugFlag() {
		return getdrugFlag;
	}

	public void setGetdrugFlag(Integer getdrugFlag) {
		this.getdrugFlag = getdrugFlag;
	}
	
	@Length(min=0, max=200, message="presc_attr长度必须介于 0 和 200 之间")
	public String getPrescAttr() {
		return prescAttr;
	}

	public void setPrescAttr(String prescAttr) {
		this.prescAttr = prescAttr;
	}
	
	public Integer getAbidance() {
		return abidance;
	}

	public void setAbidance(Integer abidance) {
		this.abidance = abidance;
	}
	
	@Length(min=0, max=40, message="perform_nurse长度必须介于 0 和 40 之间")
	public String getPerformNurse() {
		return performNurse;
	}

	public void setPerformNurse(String performNurse) {
		this.performNurse = performNurse;
	}
	
	@Length(min=0, max=128, message="perform_result长度必须介于 0 和 128 之间")
	public String getPerformResult() {
		return performResult;
	}

	public void setPerformResult(String performResult) {
		this.performResult = performResult;
	}
	
	@Length(min=0, max=128, message="skin_flag长度必须介于 0 和 128 之间")
	public String getSkinFlag() {
		return skinFlag;
	}

	public void setSkinFlag(String skinFlag) {
		this.skinFlag = skinFlag;
	}
	
	public Integer getPrescPsno() {
		return prescPsno;
	}

	public void setPrescPsno(Integer prescPsno) {
		this.prescPsno = prescPsno;
	}
	
	@Length(min=0, max=128, message="skin_result长度必须介于 0 和 128 之间")
	public String getSkinResult() {
		return skinResult;
	}

	public void setSkinResult(String skinResult) {
		this.skinResult = skinResult;
	}

    public List<OutpPresc> getList() {
        return list;
    }

    public void setList(List<OutpPresc> list) {
        this.list = list;
    }

	public String getClinicId() {
		return clinicId;
	}

	public void setClinicId(String clinicId) {
		this.clinicId = clinicId;
	}
}