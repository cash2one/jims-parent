/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.LabTestItems;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 检验项目DAO接口
 * @author xueyx
 * @version 2016-05-04
 */
@MyBatisDao
public interface LabTestItemsDao extends CrudDao<LabTestItems> {
	
}