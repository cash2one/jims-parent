package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.OutpAcctMaster;

/**
 * 门诊收费结帐主记录DAO接口
 * @author pq
 * @version 2016-05-31
 */
@MyBatisDao
public interface OutpAcctMasterDao extends CrudDao<OutpAcctMaster> {
	
}