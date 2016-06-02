package com.jims.finance.outpAccounts.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.outpAccounts.entity.OutpAcctMoney;

/**
 * 门诊收费结帐金额分类DAO接口
 * @author pq
 * @version 2016-06-01
 */
@MyBatisDao
public interface OutpAcctMoneyDao extends CrudDao<OutpAcctMoney> {
	
}