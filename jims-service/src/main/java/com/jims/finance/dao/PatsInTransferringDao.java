package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.PatsInTransferring;

/**
 * 用户反馈转科病人记录DAO接口
 * @author CTQ
 * @version 2016-05-25
 */
@MyBatisDao
public interface PatsInTransferringDao extends CrudDao<PatsInTransferring> {
	
}