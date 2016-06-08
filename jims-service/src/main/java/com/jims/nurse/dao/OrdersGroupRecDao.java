package com.jims.nurse.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.nurse.entity.OrdersGroupRec;

/**
 * 核算组记录DAO接口
 * @author CTQ
 * @version 2016-06-06
 */
@MyBatisDao
public interface OrdersGroupRecDao extends CrudDao<OrdersGroupRec> {
	
}