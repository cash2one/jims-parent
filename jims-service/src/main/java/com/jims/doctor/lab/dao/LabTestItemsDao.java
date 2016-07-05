/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.lab.dao;

import com.jims.lab.entity.LabTestItems;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 检验项目DAO接口
 * @author xueyx
 * @version 2016-05-04
 */
@MyBatisDao
public interface LabTestItemsDao extends CrudDao<LabTestItems> {
	public List<LabTestItems> getItemName(@Param("testNo")String testNo);

	/***
	 * 删除住院检验记录
	 * @param labMaster
	 * @return
	 */
	public String deleteItmes(@Param("labMaster")String labMaster);
}