/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 人员拥有服务Entity
 * @author yangruidong
 * @version 2016-04-13
 */
public class PersionServiceList extends DataEntity<PersionServiceList> {
	
	private static final long serialVersionUID = 1L;
	private String persionId;		// 人员
	private String menuId;		// 菜单
	private String flag;		// 0默认服务1，增值服务
	private Date serviceStartDate;		// 服务开始时间
	private Date serviceEndDate;		// 服务结束时间
	
	public PersionServiceList() {
		super();
	}

	public PersionServiceList(String id){
		super(id);
	}

	@Length(min=0, max=64, message="人员长度必须介于 0 和 64 之间")
	public String getPersionId() {
		return persionId;
	}

	public void setPersionId(String persionId) {
		this.persionId = persionId;
	}
	
	@Length(min=0, max=64, message="菜单长度必须介于 0 和 64 之间")
	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	
	@Length(min=0, max=2, message="0默认服务1，增值服务长度必须介于 0 和 2 之间")
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getServiceStartDate() {
		return serviceStartDate;
	}

	public void setServiceStartDate(Date serviceStartDate) {
		this.serviceStartDate = serviceStartDate;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getServiceEndDate() {
		return serviceEndDate;
	}

	public void setServiceEndDate(Date serviceEndDate) {
		this.serviceEndDate = serviceEndDate;
	}
	
}