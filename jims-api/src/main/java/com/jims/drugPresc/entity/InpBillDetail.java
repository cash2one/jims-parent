package com.jims.drugPresc.entity;



import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 住院病人费用明细记录Entity
 * @author pq
 * @version 2016-05-30
 */
public class InpBillDetail extends DataEntity<InpBillDetail> {
	
	private static final long serialVersionUID = 1L;
	private String patientId;		// 病人标识
	private String visitId;		// 病人本次住院标识
	private Integer itemNo;		// 费用项目序号
	private String itemClass;		// 项目类别
	private String itemName;		// 项目名称
	private String itemCode;		// 项目代码
	private String itemSpec;		// 项目规划
	private Double amount;		// 数量
	private String units;		// 单位
	private String orderedBy;		// 开单科室
	private String performedBy;		// 执行科室
	private Double costs;		// 费用
	private Double charges;		// 应收费用
	private Date billingDateTime;		// 计价日期及时间
	private String operatorNo;		// 计价员号
	private String rcptNo;		// 对应的结算序号
	private String classOnInpRcpt;		// 住院收据分类
	private String subjCode;		// 会计科目分类
	private String classOnMr;		// 病案首页分类
	private Double itemPrice;		// 项目标准单价
	private Double priceQuotiety;		// 收费系数
	private Integer dischargeTakingIndicator;		// 出院带药标志
	private String wardCode;		// 护理单元
	private String classOnReckoning;		// 核算项目分类
	private String orderGroup;		// 开单医生核算组
	private String orderDoctor;		// 开单医生姓名
	private String performGroup;		// 执行医生核算组
	private String performDoctor;		// 执行医生
	private Date conveyDate;		// 转储时间
	private String doctorUser;		// 医生代码
	private String transflag;		// 医保传送标志
	private String memo;		// 备注
	private String operType;		// 操作类型
	private String operId;		// oper_id
	private String operCode;		// 操作编码
	private Integer adaptSymptomIndicate;		// 适应症标志
	private String documentNo;		// 出库单号
	private String transFlag;		// 医保传送标志
	private String qybJzxh;		// 就诊编号
	private String qybJzlsh;		// 记账流水号
	private Double qybQzfje;		// 明细项目全自费金额
	private Double qybGgzfje;		// 明细项目挂钩自付金额
	private Double qybFhfwje;		// 明细项目符合范围金额
	private String qybInsurName;		// qyb_insur_name
	private String qybParamid;		// qyb_paramid
	private String performedBySub;		// 发药药房的子管理单位
	private Integer ybUpload;		// 医保费用上传标志
	private Integer nhUpload;		// 农合费用上传标志
	
	public InpBillDetail() {
		super();
	}

	public InpBillDetail(String id){
		super(id);
	}


	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getVisitId() {
		return visitId;
	}

	public void setVisitId(String visitId) {
		this.visitId = visitId;
	}


	public Integer getItemNo() {
		return itemNo;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}


	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}


	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}


	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}


	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}


	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}


	public String getOrderedBy() {
		return orderedBy;
	}

	public void setOrderedBy(String orderedBy) {
		this.orderedBy = orderedBy;
	}


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


	public Date getBillingDateTime() {
		return billingDateTime;
	}

	public void setBillingDateTime(Date billingDateTime) {
		this.billingDateTime = billingDateTime;
	}


	public String getOperatorNo() {
		return operatorNo;
	}

	public void setOperatorNo(String operatorNo) {
		this.operatorNo = operatorNo;
	}


	public String getRcptNo() {
		return rcptNo;
	}

	public void setRcptNo(String rcptNo) {
		this.rcptNo = rcptNo;
	}


	public String getClassOnInpRcpt() {
		return classOnInpRcpt;
	}

	public void setClassOnInpRcpt(String classOnInpRcpt) {
		this.classOnInpRcpt = classOnInpRcpt;
	}


	public String getSubjCode() {
		return subjCode;
	}

	public void setSubjCode(String subjCode) {
		this.subjCode = subjCode;
	}


	public String getClassOnMr() {
		return classOnMr;
	}

	public void setClassOnMr(String classOnMr) {
		this.classOnMr = classOnMr;
	}


	public Double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Double itemPrice) {
		this.itemPrice = itemPrice;
	}


	public Double getPriceQuotiety() {
		return priceQuotiety;
	}

	public void setPriceQuotiety(Double priceQuotiety) {
		this.priceQuotiety = priceQuotiety;
	}


	public Integer getDischargeTakingIndicator() {
		return dischargeTakingIndicator;
	}

	public void setDischargeTakingIndicator(Integer dischargeTakingIndicator) {
		this.dischargeTakingIndicator = dischargeTakingIndicator;
	}


	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}


	public String getClassOnReckoning() {
		return classOnReckoning;
	}

	public void setClassOnReckoning(String classOnReckoning) {
		this.classOnReckoning = classOnReckoning;
	}


	public String getOrderGroup() {
		return orderGroup;
	}

	public void setOrderGroup(String orderGroup) {
		this.orderGroup = orderGroup;
	}


	public String getOrderDoctor() {
		return orderDoctor;
	}

	public void setOrderDoctor(String orderDoctor) {
		this.orderDoctor = orderDoctor;
	}


	public String getPerformGroup() {
		return performGroup;
	}

	public void setPerformGroup(String performGroup) {
		this.performGroup = performGroup;
	}


	public String getPerformDoctor() {
		return performDoctor;
	}

	public void setPerformDoctor(String performDoctor) {
		this.performDoctor = performDoctor;
	}


	public Date getConveyDate() {
		return conveyDate;
	}

	public void setConveyDate(Date conveyDate) {
		this.conveyDate = conveyDate;
	}


	public String getDoctorUser() {
		return doctorUser;
	}

	public void setDoctorUser(String doctorUser) {
		this.doctorUser = doctorUser;
	}


	public String getTransflag() {
		return transflag;
	}

	public void setTransflag(String transflag) {
		this.transflag = transflag;
	}


	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}


	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}


	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}


	public String getOperCode() {
		return operCode;
	}

	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}


	public Integer getAdaptSymptomIndicate() {
		return adaptSymptomIndicate;
	}

	public void setAdaptSymptomIndicate(Integer adaptSymptomIndicate) {
		this.adaptSymptomIndicate = adaptSymptomIndicate;
	}


	public String getDocumentNo() {
		return documentNo;
	}

	public void setDocumentNo(String documentNo) {
		this.documentNo = documentNo;
	}


	public String getTransFlag() {
		return transFlag;
	}

	public void setTransFlag(String transFlag) {
		this.transFlag = transFlag;
	}


	public String getQybJzxh() {
		return qybJzxh;
	}

	public void setQybJzxh(String qybJzxh) {
		this.qybJzxh = qybJzxh;
	}


	public String getQybJzlsh() {
		return qybJzlsh;
	}

	public void setQybJzlsh(String qybJzlsh) {
		this.qybJzlsh = qybJzlsh;
	}


	public Double getQybQzfje() {
		return qybQzfje;
	}

	public void setQybQzfje(Double qybQzfje) {
		this.qybQzfje = qybQzfje;
	}


	public Double getQybGgzfje() {
		return qybGgzfje;
	}

	public void setQybGgzfje(Double qybGgzfje) {
		this.qybGgzfje = qybGgzfje;
	}


	public Double getQybFhfwje() {
		return qybFhfwje;
	}

	public void setQybFhfwje(Double qybFhfwje) {
		this.qybFhfwje = qybFhfwje;
	}


	public String getQybInsurName() {
		return qybInsurName;
	}

	public void setQybInsurName(String qybInsurName) {
		this.qybInsurName = qybInsurName;
	}


	public String getQybParamid() {
		return qybParamid;
	}

	public void setQybParamid(String qybParamid) {
		this.qybParamid = qybParamid;
	}


	public String getPerformedBySub() {
		return performedBySub;
	}

	public void setPerformedBySub(String performedBySub) {
		this.performedBySub = performedBySub;
	}


	public Integer getYbUpload() {
		return ybUpload;
	}

	public void setYbUpload(Integer ybUpload) {
		this.ybUpload = ybUpload;
	}


	public Integer getNhUpload() {
		return nhUpload;
	}

	public void setNhUpload(Integer nhUpload) {
		this.nhUpload = nhUpload;
	}

}