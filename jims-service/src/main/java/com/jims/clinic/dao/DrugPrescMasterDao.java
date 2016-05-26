package com.jims.clinic.dao;


import com.jims.clinic.entity.DrugPrescMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 药品处方主记录DAO接口
 * @author pq
 * @version 2016-05-24
 */
@MyBatisDao
public interface DrugPrescMasterDao extends CrudDao<DrugPrescMaster> {
	
}