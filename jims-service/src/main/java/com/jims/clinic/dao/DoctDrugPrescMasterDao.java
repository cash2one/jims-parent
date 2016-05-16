package com.jims.clinic.dao;


import com.jims.clinic.entity.DoctDrugPrescMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 待发药住院处方主记录DAO接口
 * @author CTQ
 * @version 2016-05-16
 */
@MyBatisDao
public interface DoctDrugPrescMasterDao extends CrudDao<DoctDrugPrescMaster> {
	
}