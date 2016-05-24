package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugImportMaster;

/**
 * 入库记录单DAO接口
 * @author lgx
 * @version 2016-05-17
 */
@MyBatisDao
public interface DrugImportMasterDao extends CrudDao<DrugImportMaster> {
	
}