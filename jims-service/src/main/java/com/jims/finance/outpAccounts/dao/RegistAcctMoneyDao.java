package com.jims.finance.outpAccounts.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.outpAccounts.entity.RegistAcctMoney;

/**
 * 挂号结帐金额分类DAO接口
 * @author CTQ
 * @version 2016-06-01
 */
@MyBatisDao
public interface RegistAcctMoneyDao extends CrudDao<RegistAcctMoney> {
	
}