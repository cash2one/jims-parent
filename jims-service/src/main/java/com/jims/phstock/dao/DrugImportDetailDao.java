package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugImportDetail;

/**
 * 药品入库明细单据DAO接口
 * @author lgx
 * @version 2016-05-17
 */
@MyBatisDao
public interface DrugImportDetailDao extends CrudDao<DrugImportDetail> {
	
}