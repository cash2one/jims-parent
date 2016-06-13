package com.jims.sys.entity;



import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * 角色服务权限 Entity
 * @author luohk
 * @version 2016-05-31
 */
public class OrgRoleVsService extends DataEntity<OrgRoleVsService> {
	
	private static final long serialVersionUID = 1L;
	private String serviceId;		// 服务id
	private String roleId;		// 角色id
    private String remark;
    private String serviceName;
	
	public OrgRoleVsService() {
		super();
	}

	public OrgRoleVsService(String id){
		super(id);
	}


	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}