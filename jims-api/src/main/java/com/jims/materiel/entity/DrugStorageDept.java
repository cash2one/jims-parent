/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.materiel.entity;

import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

/**
 * 药品库存单位Entity
 * @author zhaoning
 * @version 2016-04-22
 */
public class DrugStorageDept extends DataEntity<DrugStorageDept> {
	
	private static final long serialVersionUID = 1L;
	private String storageCode;		// 单位代码
	private String storageName;		// 单位名称
	private String storageType;		// 单位性质
	private String disburseNoPrefix;		// 付款单前缀
	private Integer disburseNoAva;		// 当前付款单号
	private Integer exportNoAva;		// 出库单可用流水号
	private String exportNoPrefix;		// 出库单号前缀
	private Integer importNoAva;		// 入库单可用流水号
	private String importNoPrefix;		// 入库单号前缀
	private String storageFlag;		// 标志
	private String ygFlag;		// 是否纳入药柜范畴
	private String wardCode;		// 对应药柜护理单元
	private String orgId;		// 结构Id
	
	public DrugStorageDept() {
		super();
	}

	public DrugStorageDept(String id){
		super(id);
	}

	@Length(min=1, max=8, message="单位代码长度必须介于 1 和 8 之间")
	public String getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}
	
	@Length(min=0, max=20, message="单位名称长度必须介于 0 和 20 之间")
	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	
	@Length(min=0, max=8, message="单位性质长度必须介于 0 和 8 之间")
	public String getStorageType() {
		return storageType;
	}

	public void setStorageType(String storageType) {
		this.storageType = storageType;
	}
	
	@Length(min=0, max=6, message="付款单前缀长度必须介于 0 和 6 之间")
	public String getDisburseNoPrefix() {
		return disburseNoPrefix;
	}

	public void setDisburseNoPrefix(String disburseNoPrefix) {
		this.disburseNoPrefix = disburseNoPrefix;
	}
	
	public Integer getDisburseNoAva() {
		return disburseNoAva;
	}

	public void setDisburseNoAva(Integer disburseNoAva) {
		this.disburseNoAva = disburseNoAva;
	}
	
	public Integer getExportNoAva() {
		return exportNoAva;
	}

	public void setExportNoAva(Integer exportNoAva) {
		this.exportNoAva = exportNoAva;
	}
	
	@Length(min=0, max=6, message="出库单号前缀长度必须介于 0 和 6 之间")
	public String getExportNoPrefix() {
		return exportNoPrefix;
	}

	public void setExportNoPrefix(String exportNoPrefix) {
		this.exportNoPrefix = exportNoPrefix;
	}
	
	public Integer getImportNoAva() {
		return importNoAva;
	}

	public void setImportNoAva(Integer importNoAva) {
		this.importNoAva = importNoAva;
	}
	
	@Length(min=0, max=6, message="入库单号前缀长度必须介于 0 和 6 之间")
	public String getImportNoPrefix() {
		return importNoPrefix;
	}

	public void setImportNoPrefix(String importNoPrefix) {
		this.importNoPrefix = importNoPrefix;
	}
	
	@Length(min=0, max=2, message="标志长度必须介于 0 和 2 之间")
	public String getStorageFlag() {
		return storageFlag;
	}

	public void setStorageFlag(String storageFlag) {
		this.storageFlag = storageFlag;
	}
	
	@Length(min=0, max=2, message="是否纳入药柜范畴长度必须介于 0 和 2 之间")
	public String getYgFlag() {
		return ygFlag;
	}

	public void setYgFlag(String ygFlag) {
		this.ygFlag = ygFlag;
	}
	
	@Length(min=0, max=10, message="对应药柜护理单元长度必须介于 0 和 10 之间")
	public String getWardCode() {
		return wardCode;
	}

	public void setWardCode(String wardCode) {
		this.wardCode = wardCode;
	}
	
	@Length(min=0, max=64, message="结构Id长度必须介于 0 和 64 之间")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	
}