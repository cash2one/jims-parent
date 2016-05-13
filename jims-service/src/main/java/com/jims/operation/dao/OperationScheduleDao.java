/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.operation.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.operation.entity.OperationSchedule;

/**
 * 手术安排DAO接口
 * @author pq
 * @version 2016-05-12
 */
@MyBatisDao
public interface OperationScheduleDao extends CrudDao<OperationSchedule> {
	
}