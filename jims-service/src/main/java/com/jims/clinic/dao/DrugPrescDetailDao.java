package com.jims.clinic.dao;


import com.jims.clinic.entity.DrugPrescDetail;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 药品处方明细表DAO接口
 * @author pq
 * @version 2016-05-24
 */
@MyBatisDao
public interface DrugPrescDetailDao extends CrudDao<DrugPrescDetail> {
	
}