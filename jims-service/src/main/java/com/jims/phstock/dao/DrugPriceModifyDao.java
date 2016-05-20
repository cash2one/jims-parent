package com.jims.phstock.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugPriceModify;

/**
 * 调价记录表DAO接口
 * @author txb
 * @version 2016-05-18
 */
@MyBatisDao
public interface DrugPriceModifyDao extends CrudDao<DrugPriceModify> {
	
}