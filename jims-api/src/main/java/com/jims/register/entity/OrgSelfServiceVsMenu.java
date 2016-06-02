package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 机构自定义服务对应菜单Entity
 * @author lgx
 * @version 2016-05-31
 */
public class OrgSelfServiceVsMenu extends DataEntity<OrgSelfServiceVsMenu> {
	
    private static final long serialVersionUID = 1L;
    private String selfServiceId;		// 服务ID
    private String menuId;		// 菜单ID
    private String menuSort;		// 菜单排序
    private String careateBy;		// careate_by
    private String remark;		// remark
    private Date createTime;		// create_time
	
	public OrgSelfServiceVsMenu() {
		super();
	}

	public OrgSelfServiceVsMenu(String id){
		super(id);
	}


	public String getSelfServiceId() {
		return selfServiceId;
	}

	public void setSelfServiceId(String selfServiceId) {
		this.selfServiceId = selfServiceId;
	}


	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public String getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(String menuSort) {
		this.menuSort = menuSort;
	}


	public String getCareateBy() {
		return careateBy;
	}

	public void setCareateBy(String careateBy) {
		this.careateBy = careateBy;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}