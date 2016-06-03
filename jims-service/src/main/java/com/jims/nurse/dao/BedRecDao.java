package com.jims.nurse.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.nurse.entity.BedRec;

/**
 * 床位记录DAO接口
 * @author pq
 * @version 2016-06-02
 */
@MyBatisDao
public interface BedRecDao extends CrudDao<BedRec> {
	
}