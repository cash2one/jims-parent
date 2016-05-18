/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;


import com.jims.clinic.entity.OrdersCosts;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 医嘱计价项目DAO接口
 * @author pq
 * @version 2016-05-18
 */
@MyBatisDao
public interface OrdersCostsDao extends CrudDao<OrdersCosts> {
	
}