package com.jims.nurse.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.nurse.entity.AdtLog;

/**
 * 病人入出转及状态变化日志DAO接口
 * @author CTQ
 * @version 2016-06-03
 */
@MyBatisDao
public interface AdtLogDao extends CrudDao<AdtLog> {
	
}