/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.entity;

import com.jims.common.utils.CustomDateSerializer;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 药品价格Entity
 * @author zhaoning
 * @version 2016-04-22
 */
public class DrugPriceList extends DataEntity<DrugPriceList> {
	
	private static final long serialVersionUID = 1L;
	private String drugCode;		// 药品代码
	private String drugSpec;		// 规格
	private String firmId;		// 厂家标识
	private String units;		// 单位
	private Double tradePrice;		// 市场批发价
	private Double retailPrice;		// 市场零售价
	private Integer amountPerPackage;		// 包装数量
	private String minSpec;		// 最小单位规格
	private String minUnits;		// 最小单位
	private Date startDate;		// 起用日期
	private Date stopDate;		// 停止日期
	private String memos;		// 备注
	private String classOnInpRcpt;		// class_on_inp_rcpt
	private String classOnOutpRcpt;		// class_on_outp_rcpt
	private String classOnReckoning;		// class_on_reckoning
	private String subjCode;		// subj_code
	private String classOnMr;		// class_on_mr
	private Double hlimitPrice;		// hlimit_price
	private String priceClass;		// price_class
	private String passNo;		// pass_no
	private Integer gmp;		// gmp
	private String orgId;		// 所属组织结构
	private String drugName ; //药品名称

    //无效字段
    private String supplier; // 厂商
    private String stopDateCheck; // 停价标志

    public String getStopDateCheck() {
        return stopDateCheck;
    }

    public void setStopDateCheck(String stopDateCheck) {
        this.stopDateCheck = stopDateCheck;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public DrugPriceList() {
		super();
	}

	public DrugPriceList(String id){
		super(id);
	}

	@Length(min=1, max=20, message="药品代码长度必须介于 1 和 20 之间")
	public String getDrugCode() {
		return drugCode;
	}

	public void setDrugCode(String drugCode) {
		this.drugCode = drugCode;
	}
	
	@Length(min=1, max=20, message="规格长度必须介于 1 和 20 之间")
	public String getDrugSpec() {
		return drugSpec;
	}

	public void setDrugSpec(String drugSpec) {
		this.drugSpec = drugSpec;
	}
	
	@Length(min=1, max=64, message="厂家标识长度必须介于 1 和 64 之间")
	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}
	
	@Length(min=0, max=8, message="单位长度必须介于 0 和 8 之间")
	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}
	
	public Double getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}
	
	public Double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public Integer getAmountPerPackage() {
		return amountPerPackage;
	}

	public void setAmountPerPackage(Integer amountPerPackage) {
		this.amountPerPackage = amountPerPackage;
	}
	
	@Length(min=0, max=20, message="最小单位规格长度必须介于 0 和 20 之间")
	public String getMinSpec() {
		return minSpec;
	}

	public void setMinSpec(String minSpec) {
		this.minSpec = minSpec;
	}
	
	@Length(min=0, max=8, message="最小单位长度必须介于 0 和 8 之间")
	public String getMinUnits() {
		return minUnits;
	}

	public void setMinUnits(String minUnits) {
		this.minUnits = minUnits;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	@NotNull(message="起用日期不能为空")
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
	
	@Length(min=0, max=20, message="备注长度必须介于 0 和 20 之间")
	public String getMemos() {
		return memos;
	}

	public void setMemos(String memos) {
		this.memos = memos;
	}
	
	@Length(min=0, max=1, message="class_on_inp_rcpt长度必须介于 0 和 1 之间")
	public String getClassOnInpRcpt() {
		return classOnInpRcpt;
	}

	public void setClassOnInpRcpt(String classOnInpRcpt) {
		this.classOnInpRcpt = classOnInpRcpt;
	}
	
	@Length(min=0, max=1, message="class_on_outp_rcpt长度必须介于 0 和 1 之间")
	public String getClassOnOutpRcpt() {
		return classOnOutpRcpt;
	}

	public void setClassOnOutpRcpt(String classOnOutpRcpt) {
		this.classOnOutpRcpt = classOnOutpRcpt;
	}
	
	@Length(min=0, max=4, message="class_on_reckoning长度必须介于 0 和 4 之间")
	public String getClassOnReckoning() {
		return classOnReckoning;
	}

	public void setClassOnReckoning(String classOnReckoning) {
		this.classOnReckoning = classOnReckoning;
	}
	
	@Length(min=0, max=4, message="subj_code长度必须介于 0 和 4 之间")
	public String getSubjCode() {
		return subjCode;
	}

	public void setSubjCode(String subjCode) {
		this.subjCode = subjCode;
	}
	
	@Length(min=0, max=4, message="class_on_mr长度必须介于 0 和 4 之间")
	public String getClassOnMr() {
		return classOnMr;
	}

	public void setClassOnMr(String classOnMr) {
		this.classOnMr = classOnMr;
	}
	
	public Double getHlimitPrice() {
		return hlimitPrice;
	}

	public void setHlimitPrice(Double hlimitPrice) {
		this.hlimitPrice = hlimitPrice;
	}
	
	@Length(min=0, max=4, message="price_class长度必须介于 0 和 4 之间")
	public String getPriceClass() {
		return priceClass;
	}

	public void setPriceClass(String priceClass) {
		this.priceClass = priceClass;
	}
	
	@Length(min=0, max=30, message="pass_no长度必须介于 0 和 30 之间")
	public String getPassNo() {
		return passNo;
	}

	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}
	
	public Integer getGmp() {
		return gmp;
	}

	public void setGmp(Integer gmp) {
		this.gmp = gmp;
	}
	
	@Length(min=0, max=64, message="所属组织结构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}