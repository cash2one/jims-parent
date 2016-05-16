package com.jims.clinic.dao;

import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 待发药住院处方明细记录DAO接口
 * @author CTQ
 * @version 2016-05-16
 */
@MyBatisDao
public interface DoctDrugPrescDetailDao extends CrudDao<DoctDrugPrescDetail> {
	
}