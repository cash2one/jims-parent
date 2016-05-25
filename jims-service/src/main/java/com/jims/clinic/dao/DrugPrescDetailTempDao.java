package com.jims.clinic.dao;


import com.jims.clinic.entity.DrugPrescDetailTemp;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 待发处方门诊明细DAO接口
 * @author pq
 * @version 2016-05-24
 */
@MyBatisDao
public interface DrugPrescDetailTempDao extends CrudDao<DrugPrescDetailTemp> {
	
}