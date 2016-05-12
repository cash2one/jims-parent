/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.operation.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.operation.entity.ScheduledOperationName;

/**
 * 手术安排名称DAO接口
 * @author pq
 * @version 2016-05-12
 */
@MyBatisDao
public interface ScheduledOperationNameDao extends CrudDao<ScheduledOperationName> {
	
}