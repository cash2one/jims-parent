package com.jims.phstock.entity;

import com.jims.common.persistence.DataEntity;

/**
 * 库存子单位Entity
 * @author lgx
 * @version 2016-05-17
 */
public class DrugSubStorageDept extends DataEntity<DrugSubStorageDept> {
	
	private static final long serialVersionUID = 1L;
	private String storageCode;		// 库存单位代码
	private String subStorage;		// 子库房
	private String importNoPrefix;		// 入库单号前缀
	private Integer importNoAva;		// 入库单可用流水号
	private String exportNoPrefix;		// 出库单号前缀
	private Integer exportNoAva;		// 出库单可用流水号
	private String subStorageCode;		// 子库房代码
	private String inputCode;		// 拼音码
	private String orgId;		// 所属结构
	
	public DrugSubStorageDept() {
		super();
	}

	public DrugSubStorageDept(String id){
		super(id);
	}


	public String getStorageCode() {
		return storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}


	public String getSubStorage() {
		return subStorage;
	}

	public void setSubStorage(String subStorage) {
		this.subStorage = subStorage;
	}


	public String getImportNoPrefix() {
		return importNoPrefix;
	}

	public void setImportNoPrefix(String importNoPrefix) {
		this.importNoPrefix = importNoPrefix;
	}


	public Integer getImportNoAva() {
		return importNoAva;
	}

	public void setImportNoAva(Integer importNoAva) {
		this.importNoAva = importNoAva;
	}


	public String getExportNoPrefix() {
		return exportNoPrefix;
	}

	public void setExportNoPrefix(String exportNoPrefix) {
		this.exportNoPrefix = exportNoPrefix;
	}


	public Integer getExportNoAva() {
		return exportNoAva;
	}

	public void setExportNoAva(Integer exportNoAva) {
		this.exportNoAva = exportNoAva;
	}


	public String getSubStorageCode() {
		return subStorageCode;
	}

	public void setSubStorageCode(String subStorageCode) {
		this.subStorageCode = subStorageCode;
	}


	public String getInputCode() {
		return inputCode;
	}

	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}


	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}