/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.blood.entity.BloodCapacity;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 申请用血量DAO接口
 * @author qlx
 * @version 2016-04-28
 */
@MyBatisDao
public interface BloodCapacityDao extends CrudDao<BloodCapacity> {
	public  void delBloodCapacity(String applyNum);
	public List<BloodCapacity> getBloodCapacityList(BloodCapacity bloodCapacity);
}