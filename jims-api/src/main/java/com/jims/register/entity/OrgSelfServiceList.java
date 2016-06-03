package com.jims.register.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;
import java.util.List;

/**
 * 机构自定义服务Entity
 * @author lgx
 * @version 2016-05-31
 */
public class OrgSelfServiceList extends DataEntity<OrgSelfServiceList> {
	
	private static final long serialVersionUID = 1L;
	private String serviceName;		// 自定义服务名字
	private String orgId;		// 机构ID
	private String careateBy;		// careate_by
	private String remark;		// remark
	private Date createTime;		// create_time

    private List<OrgSelfServiceVsMenu> menus;   // 自定义服务对应的菜单

    private String operateFlag ;    // 操作标志 ， 1 为删除自定义服务，id为需删除的id,
                                              // 2 为更改的菜单数据，id为删除的菜单id,menus为更改的菜单


    public String getOperateFlag() {
        return operateFlag;
    }

    public void setOperateFlag(String operateFlag) {
        this.operateFlag = operateFlag;
    }

    public List<OrgSelfServiceVsMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<OrgSelfServiceVsMenu> menus) {
        this.menus = menus;
    }

    public OrgSelfServiceList() {
		super();
	}

	public OrgSelfServiceList(String id){
		super(id);
	}


	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
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