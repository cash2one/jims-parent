package com.jims.register.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.ClinicReturnedAcct;

/**
 * 退号DAO接口
 * @author 121
 * @version 2016-05-19
 */
@MyBatisDao
public interface ClinicReturnedAcctDao extends CrudDao<ClinicReturnedAcct> {
	
}