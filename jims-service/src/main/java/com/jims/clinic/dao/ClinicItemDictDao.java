package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 诊疗项目DAO接口
 * @author lgx
 * @version 2016-04-28
 */
@MyBatisDao
public interface ClinicItemDictDao extends CrudDao<ClinicItemDict> {
	
}