package com.jims.clinic.dao;


import com.jims.clinic.entity.DrugPrescMasterTemp;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 待发处方门诊主表DAO接口
 * @author pq
 * @version 2016-05-24
 */
@MyBatisDao
public interface DrugPrescMasterTempDao extends CrudDao<DrugPrescMasterTemp> {
	
}