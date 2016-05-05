/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.clinic.entity.LabTestMaster;

/**
 * 检验主记录DAO接口
 * @author xueyx
 * @version 2016-05-04
 */
@MyBatisDao
public interface LabTestMasterDao extends CrudDao<LabTestMaster> {
	
}