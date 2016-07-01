/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.asepsis.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 门诊病人诊疗收费项目明细Entity
 * @author zhaoning
 * @version 2016-05-26
 */
public class AsepsisAntiRec extends DataEntity<AsepsisAntiRec> {
//public class AsepsisAntiRec implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
//	private String id;//与DataEntity有冲突，若写了就获取不到ｉｄ值了//
	private String documnetNo;//	业务单据号
	private String asepsisCode;//	代码
	private String asepsisName;//	名称
	private String asepsisSpec;//	规格
	private String asepsisSpecDes;//	规格
	private String units;//	单位
	private String unitsDes;//	单位
	private String belongDept;//	所属科室
	private String belongDeptDes;//	所属科室
	private Date antiDate;//消毒日期
	private String antiOperator;//	消毒人
	private String antiOperatorDes;//	消毒人
	private String antiWays;//	消毒方式
	private String antiWaysDes;//	消毒方式
	private String sterOperator;//	清洗人
	private String sterOperatorDes;//	清洗人
	private Date sterDate;//清洗日期
	private String packOperator;//	打包人
	private String packOperatorDes;//	打包人
	private Date packDate;//打包日期
	private String memos;//	备注
	private String asepsisState;//	0-未清洗；1-清洗未打包；2-打包未消毒；3-消毒加库存；null-未作任何处理
	private String asepsisStateDes;//	0-未清洗；1-清洗未打包；2-打包未消毒；3-消毒加库存；null-未作任何处理
	private Integer amount;//	数量
	private Integer amountAnti;//	数量
	private String antiBatchNo;//	消毒包处理后的单据号，用来标记是同一批消毒、打包的
	private Date impDate;//还物送物时间
	private String boilerNo;//	锅号
	private String boilerTimes;//	锅次
	private String checker;//	查对者
	private String checkerDes;//	查对者
	private Integer itemNo;//
	private String packWays;//	打包方法
	private String packWaysDes;//	打包方法
	private String cleanWays;//	清洗方式
	private String cleanWaysDes;//	清洗方式
	private String cleanNo;//	清洗机号
	private String cleanTimes;//	清洗机次
	private String orgId;//	所属机构ID

	public AsepsisAntiRec() {}
	public AsepsisAntiRec(String id){
		super(id);
	}
//	@Length(min=0, max=128, message="收据号长度必须介于 0 和 128 之间")

	public Date getAntiDate() {
		return antiDate;
	}

	public Date getImpDate() {
		return impDate;
	}

	public Date getPackDate() {
		return packDate;
	}

	public Date getSterDate() {
		return sterDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public Integer getItemNo() {
		return itemNo;
	}

	public String getAntiOperator() {
		return antiOperator;
	}

	public String getAntiWays() {
		return antiWays;
	}

	public String getAntiBatchNo() {
		return antiBatchNo;
	}

	public String getAsepsisCode() {
		return asepsisCode;
	}

	public String getAsepsisName() {
		return asepsisName;
	}

	public String getAsepsisSpec() {
		return asepsisSpec;
	}

	public String getAsepsisState() {
		return asepsisState;
	}

	public String getBelongDept() {
		return belongDept;
	}

	public String getBoilerTimes() {
		return boilerTimes;
	}

	public String getBoilerNo() {
		return boilerNo;
	}

	public String getChecker() {
		return checker;
	}

	public String getCleanNo() {
		return cleanNo;
	}

	public String getCleanTimes() {
		return cleanTimes;
	}

	public String getCleanWays() {
		return cleanWays;
	}

	public String getDocumnetNo() {
		return documnetNo;
	}

	public String getMemos() {
		return memos;
	}

	public String getOrgId() {
		return orgId;
	}

	public String getPackOperator() {
		return packOperator;
	}

	public String getPackWays() {
		return packWays;
	}

	public String getSterOperator() {
		return sterOperator;
	}

	public String getUnits() {
		return units;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public void setAntiDate(Date antiDate) {
		this.antiDate = antiDate;
	}

	public void setAntiOperator(String antiOperator) {
		this.antiOperator = antiOperator;
	}

	public void setAntiWays(String antiWays) {
		this.antiWays = antiWays;
	}

	public void setAntiBatchNo(String antiBatchNo) {
		this.antiBatchNo = antiBatchNo;
	}

	public void setAsepsisCode(String asepsisCode) {
		this.asepsisCode = asepsisCode;
	}

	public void setAsepsisName(String asepsisName) {
		this.asepsisName = asepsisName;
	}

	public void setAsepsisSpec(String asepsisSpec) {
		this.asepsisSpec = asepsisSpec;
	}

	public void setAsepsisState(String asepsisState) {
		this.asepsisState = asepsisState;
	}

	public void setBelongDept(String belongDept) {
		this.belongDept = belongDept;
	}

	public void setBoilerTimes(String boilerTimes) {
		this.boilerTimes = boilerTimes;
	}

	public void setBoilerNo(String boilerNo) {
		this.boilerNo = boilerNo;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public void setCleanNo(String cleanNo) {
		this.cleanNo = cleanNo;
	}

	public void setCleanTimes(String cleanTimes) {
		this.cleanTimes = cleanTimes;
	}

	public void setCleanWays(String cleanWays) {
		this.cleanWays = cleanWays;
	}

	public void setDocumnetNo(String documnetNo) {
		this.documnetNo = documnetNo;
	}

	public void setImpDate(Date impDate) {
		this.impDate = impDate;
	}

	public void setItemNo(Integer itemNo) {
		this.itemNo = itemNo;
	}

	public void setMemos(String memos) {
		this.memos = memos;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public void setPackDate(Date packDate) {
		this.packDate = packDate;
	}

	public void setPackOperator(String packOperator) {
		this.packOperator = packOperator;
	}

	public void setPackWays(String packWays) {
		this.packWays = packWays;
	}

	public void setSterDate(Date sterDate) {
		this.sterDate = sterDate;
	}

	public void setSterOperator(String sterOperator) {
		this.sterOperator = sterOperator;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public Integer getAmountAnti() {
		return amountAnti;
	}

	public String getAntiOperatorDes() {
		return antiOperatorDes;
	}

	public String getAntiWaysDes() {
		return antiWaysDes;
	}

	public String getAsepsisSpecDes() {
		return asepsisSpecDes;
	}

	public String getAsepsisStateDes() {
		return asepsisStateDes;
	}

	public String getBelongDeptDes() {
		return belongDeptDes;
	}

	public String getCheckerDes() {
		return checkerDes;
	}

	public String getCleanWaysDes() {
		return cleanWaysDes;
	}

	public String getPackOperatorDes() {
		return packOperatorDes;
	}

	public String getPackWaysDes() {
		return packWaysDes;
	}

	public String getSterOperatorDes() {
		return sterOperatorDes;
	}

	public String getUnitsDes() {
		return unitsDes;
	}

	public void setAmountAnti(Integer amountAnti) {
		this.amountAnti = amountAnti;
	}

	public void setAntiOperatorDes(String antiOperatorDes) {
		this.antiOperatorDes = antiOperatorDes;
	}

	public void setAntiWaysDes(String antiWaysDes) {
		this.antiWaysDes = antiWaysDes;
	}

	public void setAsepsisSpecDes(String asepsisSpecDes) {
		this.asepsisSpecDes = asepsisSpecDes;
	}

	public void setAsepsisStateDes(String asepsisStateDes) {
		this.asepsisStateDes = asepsisStateDes;
	}

	public void setBelongDeptDes(String belongDeptDes) {
		this.belongDeptDes = belongDeptDes;
	}

	public void setCheckerDes(String checkerDes) {
		this.checkerDes = checkerDes;
	}

	public void setCleanWaysDes(String cleanWaysDes) {
		this.cleanWaysDes = cleanWaysDes;
	}

	public void setPackOperatorDes(String packOperatorDes) {
		this.packOperatorDes = packOperatorDes;
	}

	public void setPackWaysDes(String packWaysDes) {
		this.packWaysDes = packWaysDes;
	}

	public void setSterOperatorDes(String sterOperatorDes) {
		this.sterOperatorDes = sterOperatorDes;
	}

	public void setUnitsDes(String unitsDes) {
		this.unitsDes = unitsDes;
	}
}