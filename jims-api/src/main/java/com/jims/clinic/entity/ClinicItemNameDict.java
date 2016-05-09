package com.jims.clinic.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 诊疗项目名称Entity
 * @author lgx
 * @version 2016-04-28
 */
public class ClinicItemNameDict extends DataEntity<ClinicItemNameDict> {
	
	private static final long serialVersionUID = 1L;
	private String itemClass;		// 项目分类
	private String itemName;		// 项目名称
	private String itemCode;		// 项目代码
	private Integer stdIndicator;		// 正名标志
	private String inputCode;		// 输入码
	private String inputCodeWb;		// 五笔码
	private String expand1;		// expand1
	private String expand2;		// expand2
	private String expand3;		// expand3
	private String expand4;		// expand4
	private String expand5;		// expand5
	private String itemStatus;		// item_status
	private String bbsm;		// bbsm
	private String userGrant;		// user_grant
	private String orgId;		// 所属结构
	private String price;
	
	public ClinicItemNameDict() {
		super();
	}

	public ClinicItemNameDict(String id){
		super(id);
	}

	@Length(min=1, max=1, message="项目分类长度必须介于 1 和 1 之间")
	public String getItemClass() {
		return itemClass;
	}

	public void setItemClass(String itemClass) {
		this.itemClass = itemClass;
	}
	
	@Length(min=1, max=100, message="项目名称长度必须介于 1 和 100 之间")
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Length(min=1, max=20, message="项目代码长度必须介于 1 和 20 之间")
	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public Integer getStdIndicator() {
		return stdIndicator;
	}

	public void setStdIndicator(Integer stdIndicator) {
		this.stdIndicator = stdIndicator;
	}
	
	@Length(min=0, max=8, message="输入码长度必须介于 0 和 8 之间")
	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	
	@Length(min=0, max=8, message="五笔码长度必须介于 0 和 8 之间")
	public String getInputCodeWb() {
		return inputCodeWb;
	}

	public void setInputCodeWb(String inputCodeWb) {
		this.inputCodeWb = inputCodeWb;
	}
	
	@Length(min=0, max=100, message="expand1长度必须介于 0 和 100 之间")
	public String getExpand1() {
		return expand1;
	}

	public void setExpand1(String expand1) {
		this.expand1 = expand1;
	}
	
	@Length(min=0, max=100, message="expand2长度必须介于 0 和 100 之间")
	public String getExpand2() {
		return expand2;
	}

	public void setExpand2(String expand2) {
		this.expand2 = expand2;
	}
	
	@Length(min=0, max=100, message="expand3长度必须介于 0 和 100 之间")
	public String getExpand3() {
		return expand3;
	}

	public void setExpand3(String expand3) {
		this.expand3 = expand3;
	}
	
	@Length(min=0, max=100, message="expand4长度必须介于 0 和 100 之间")
	public String getExpand4() {
		return expand4;
	}

	public void setExpand4(String expand4) {
		this.expand4 = expand4;
	}
	
	@Length(min=0, max=100, message="expand5长度必须介于 0 和 100 之间")
	public String getExpand5() {
		return expand5;
	}

	public void setExpand5(String expand5) {
		this.expand5 = expand5;
	}
	
	@Length(min=0, max=1, message="item_status长度必须介于 0 和 1 之间")
	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	
	@Length(min=0, max=100, message="bbsm长度必须介于 0 和 100 之间")
	public String getBbsm() {
		return bbsm;
	}

	public void setBbsm(String bbsm) {
		this.bbsm = bbsm;
	}
	
	@Length(min=0, max=20, message="user_grant长度必须介于 0 和 20 之间")
	public String getUserGrant() {
		return userGrant;
	}

	public void setUserGrant(String userGrant) {
		this.userGrant = userGrant;
	}
	
	@Length(min=0, max=64, message="所属结构长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}