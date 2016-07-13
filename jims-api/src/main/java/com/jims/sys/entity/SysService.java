package com.jims.sys.entity;


import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.beans.Transient;
import java.util.List;

/**
 * 系统服务Entity
 * @author txb
 * @version 2016-05-31
 * update by chenxy
 * 将服务描述字段类型改为byte[]
 */
public class SysService extends DataEntity<SysService> {
	
	private static final long serialVersionUID = 1L;
	private String serviceName;		// 系统服务名称
//	private String serviceDescription;		// 服务描述
    private byte[] serviceDescription;		// 服务描述

    private String serviceType;		// 1,有偿服务,0无偿服务
	private String serviceClass;		// 2,所有服务，1,个人服务，0机构服务
	private String serviceImage;		// 服务图片
    private String tranServiceDescription;

    private List<MenuDict> menuDictList;
    private List<SysServicePrice> sysServicePriceList;

    public List<MenuDict> getMenuDictList() {
        return menuDictList;
    }

    public void setMenuDictList(List<MenuDict> menuDictList) {
        this.menuDictList = menuDictList;
    }

    public List<SysServicePrice> getSysServicePriceList() {
        return sysServicePriceList;
    }

    public void setSysServicePriceList(List<SysServicePrice> sysServicePriceList) {
        this.sysServicePriceList = sysServicePriceList;
    }

    public SysService() {
		super();
	}

	public SysService(String id){
		super(id);
	}

	@Length(min=0, max=100, message="系统服务名称长度必须介于 0 和 100 之间")
	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
 	public byte[] getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(byte[] serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	
	@Length(min=0, max=2, message="1,有偿服务,0无偿服务长度必须介于 0 和 2 之间")
	public String getServiceType() {
		return serviceType;
	}
    @Length(min=0, max=2, message="2,所有服务，1,个人服务，0机构服务长度必须介于 0 和 2 之间")
	public String getServiceClass() {
		return serviceClass;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	public String getServiceImage() {
		return serviceImage;
	}

	public void setServiceImage(String serviceImage) {
		this.serviceImage = serviceImage;
	}

    @Transient
    public String getTranServiceDescription() {
        return tranServiceDescription;
    }

    public void setTranServiceDescription(String tranServiceDescription) {
        this.tranServiceDescription = tranServiceDescription;
    }
}