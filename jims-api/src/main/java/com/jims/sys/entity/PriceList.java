/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;



/**
 * 价格表Entity
 * @author 罗海昆
 * @version 2016-04-26
 */
public class PriceList extends DataEntity<PriceList> {	
	private static final long serialVersionUID = 1L;
	private String itemClass;		// 项目类别
	private String itemCode;		// 项目代码
	private String itemName;		// 项目名称
	private String itemSpec;		// 规格
	private String units;		// 单位
	private Double price;		// 基本价格
	private Double preferPrice;		// 优惠价格
	private Double foreignerPrice;		// 外宾价格
	private String performedBy;		// 执行科室
	private Integer feeTypeMask;		// 是否自费
	private String classOnInpRcpt;		// 住院收据分类
	private String classOnOutpRcpt;		// 门诊收据分类
	private String classOnReckoning;		// 核算科目
	private String subjCode;		// 会计科目
	private String classOnMr;		// 病案首页分类
	private String memo;		// 备注信息
	private Date startDate;		// 启用日期
	private Date stopDate;		// 停止日期
	private String operator;		// 维护者
	private Date enterDate;		// 输入日期
	private Double highPrice;		// 最高价格
	private String materialCode;		// 物价码
	private Double score1;		// score_1
	private Double score2;		// score_2
	private String priceNameCode;		// price_name_code
	private String controlFlag;		// control_flag
	private String inputCode;		// input_code
	private String inputCodeWb;		// input_code_wb
	private String stdCode1;		// std_code_1
	private String changedMemo;		// 价格变更原因包括调价和停用等都可以录入保存原因
	private String classOnInsurMr;		// class_on_insur_mr
	private String cwtjCode;		// cwtj_code
	private String xmWy;		// 未央项目对照
	private String lbWy;		// 未央类别对照
	private String mzsjWy;		// 门诊收据对照
	private String zysjWy;		// 住院收据对照
	private String groupFlag;		// group_flag
	private String stopOperator;		// stop_operator
    private String orgId; //组织机构

    private String priceType; // 药品分类，多个以逗号隔开（）
    private String q;  // 过滤字段

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }
	public PriceList() {
		super();
	}

	public PriceList(String id){
		super(id);
	}

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }


    @Length(min=1, max=1, message="项目类别长度必须介于 1 和 1 之间")
	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	
	@Length(min=1, max=20, message="项目代码长度必须介于 1 和 20 之间")
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
	
	@Length(min=1, max=50, message="规格长度必须介于 1 和 50 之间")
	public String getItemSpec() {
		return itemSpec;
	}

	public void setItemSpec(String itemSpec) {
		this.itemSpec = itemSpec;
	}
	
	@Length(min=1, max=8, message="单位长度必须介于 1 和 8 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public Double getPreferPrice() {
		return preferPrice;
	}

	public void setPreferPrice(Double preferPrice) {
		this.preferPrice = preferPrice;
	}
	
	public Double getForeignerPrice() {
		return foreignerPrice;
	}

	public void setForeignerPrice(Double foreignerPrice) {
		this.foreignerPrice = foreignerPrice;
	}
	
	@Length(min=0, max=8, message="执行科室长度必须介于 0 和 8 之间")
	public String getPerformedBy() {
		return performedBy;
	}

	public void setPerformedBy(String performedBy) {
		this.performedBy = performedBy;
	}
	
	public Integer getFeeTypeMask() {
		return feeTypeMask;
	}

	public void setFeeTypeMask(Integer feeTypeMask) {
		this.feeTypeMask = feeTypeMask;
	}
	
	@Length(min=0, max=1, message="住院收据分类长度必须介于 0 和 1 之间")
	public String getClassOnInpRcpt() {
		return classOnInpRcpt;
	}

	public void setClassOnInpRcpt(String classOnInpRcpt) {
		this.classOnInpRcpt = classOnInpRcpt;
	}
	
	@Length(min=0, max=1, message="门诊收据分类长度必须介于 0 和 1 之间")
	public String getClassOnOutpRcpt() {
		return classOnOutpRcpt;
	}

	public void setClassOnOutpRcpt(String classOnOutpRcpt) {
		this.classOnOutpRcpt = classOnOutpRcpt;
	}
	
	@Length(min=0, max=10, message="核算科目长度必须介于 0 和 10 之间")
	public String getClassOnReckoning() {
		return classOnReckoning;
	}

	public void setClassOnReckoning(String classOnReckoning) {
		this.classOnReckoning = classOnReckoning;
	}
	
	@Length(min=0, max=3, message="会计科目长度必须介于 0 和 3 之间")
	public String getSubjCode() {
		return subjCode;
	}

	public void setSubjCode(String subjCode) {
		this.subjCode = subjCode;
	}
	
	@Length(min=0, max=4, message="病案首页分类长度必须介于 0 和 4 之间")
	public String getClassOnMr() {
		return classOnMr;
	}

	public void setClassOnMr(String classOnMr) {
		this.classOnMr = classOnMr;
	}
	
	@Length(min=0, max=100, message="备注信息长度必须介于 0 和 100 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	@NotNull(message="启用日期不能为空")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	
	@Length(min=0, max=8, message="维护者长度必须介于 0 和 8 之间")
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getEnterDate() {
		return enterDate;
	}

	public void setEnterDate(Date enterDate) {
		this.enterDate = enterDate;
	}
	
	public Double getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(Double highPrice) {
		this.highPrice = highPrice;
	}
	
	@Length(min=0, max=20, message="物价码长度必须介于 0 和 20 之间")
	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	
	public Double getScore1() {
		return score1;
	}

	public void setScore1(Double score1) {
		this.score1 = score1;
	}
	
	public Double getScore2() {
		return score2;
	}

	public void setScore2(Double score2) {
		this.score2 = score2;
	}
	
	@Length(min=0, max=20, message="price_name_code长度必须介于 0 和 20 之间")
	public String getPriceNameCode() {
		return priceNameCode;
	}

	public void setPriceNameCode(String priceNameCode) {
		this.priceNameCode = priceNameCode;
	}
	
	@Length(min=0, max=1, message="control_flag长度必须介于 0 和 1 之间")
	public String getControlFlag() {
		return controlFlag;
	}

	public void setControlFlag(String controlFlag) {
		this.controlFlag = controlFlag;
	}
	
	@Length(min=0, max=8, message="input_code长度必须介于 0 和 8 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=8, message="input_code_wb长度必须介于 0 和 8 之间")
	public String getInputCodeWb() {
		return inputCodeWb;
	}

	public void setInputCodeWb(String inputCodeWb) {
		this.inputCodeWb = inputCodeWb;
	}
	
	@Length(min=0, max=20, message="std_code_1长度必须介于 0 和 20 之间")
	public String getStdCode1() {
		return stdCode1;
	}

	public void setStdCode1(String stdCode1) {
		this.stdCode1 = stdCode1;
	}
	
	@Length(min=0, max=40, message="价格变更原因包括调价和停用等都可以录入保存原因长度必须介于 0 和 40 之间")
	public String getChangedMemo() {
		return changedMemo;
	}

	public void setChangedMemo(String changedMemo) {
		this.changedMemo = changedMemo;
	}
	
	@Length(min=0, max=24, message="class_on_insur_mr长度必须介于 0 和 24 之间")
	public String getClassOnInsurMr() {
		return classOnInsurMr;
	}

	public void setClassOnInsurMr(String classOnInsurMr) {
		this.classOnInsurMr = classOnInsurMr;
	}
	
	@Length(min=0, max=24, message="cwtj_code长度必须介于 0 和 24 之间")
	public String getCwtjCode() {
		return cwtjCode;
	}

	public void setCwtjCode(String cwtjCode) {
		this.cwtjCode = cwtjCode;
	}
	
	@Length(min=0, max=24, message="未央项目对照长度必须介于 0 和 24 之间")
	public String getXmWy() {
		return xmWy;
	}

	public void setXmWy(String xmWy) {
		this.xmWy = xmWy;
	}
	
	@Length(min=0, max=24, message="未央类别对照长度必须介于 0 和 24 之间")
	public String getLbWy() {
		return lbWy;
	}

	public void setLbWy(String lbWy) {
		this.lbWy = lbWy;
	}
	
	@Length(min=0, max=24, message="门诊收据对照长度必须介于 0 和 24 之间")
	public String getMzsjWy() {
		return mzsjWy;
	}

	public void setMzsjWy(String mzsjWy) {
		this.mzsjWy = mzsjWy;
	}
	
	@Length(min=0, max=24, message="住院收据对照长度必须介于 0 和 24 之间")
	public String getZysjWy() {
		return zysjWy;
	}

	public void setZysjWy(String zysjWy) {
		this.zysjWy = zysjWy;
	}
	
	@Length(min=0, max=1, message="group_flag长度必须介于 0 和 1 之间")
	public String getGroupFlag() {
		return groupFlag;
	}

	public void setGroupFlag(String groupFlag) {
		this.groupFlag = groupFlag;
	}
	
	@Length(min=0, max=20, message="stop_operator长度必须介于 0 和 20 之间")
	public String getStopOperator() {
		return stopOperator;
	}

	public void setStopOperator(String stopOperator) {
		this.stopOperator = stopOperator;
	}
	
}