package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.PrepaymentRcpt;

/**
 * 预交金记录DAO接口
 * @author CTQ
 * @version 2016-05-25
 */
@MyBatisDao
public interface PrepaymentRcptDao extends CrudDao<PrepaymentRcpt> {
	
}