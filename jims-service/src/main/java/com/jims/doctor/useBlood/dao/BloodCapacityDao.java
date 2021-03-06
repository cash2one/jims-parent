/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.useBlood.dao;

import com.jims.blood.entity.BloodCapacity;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

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

	/**
	 * 根据主表id删除记录
	 * @param applyId
	 * @return
	 */
	public int deleteBloodCapacity(@Param("applyId")String applyId);

	/**
	 * 根据字表删除子表记录
	 * @param id
	 * @return
	 */
	public int deleteCapacity(@Param("id")String id);

}