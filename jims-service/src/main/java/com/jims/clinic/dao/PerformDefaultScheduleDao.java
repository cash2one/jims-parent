package com.jims.clinic.dao;


import com.jims.clinic.entity.PerformDefaultSchedule;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 医嘱执行缺省时间DAO接口
 * @author pq
 * @version 2016-06-22
 */
@MyBatisDao
public interface PerformDefaultScheduleDao extends CrudDao<PerformDefaultSchedule> {
	
}