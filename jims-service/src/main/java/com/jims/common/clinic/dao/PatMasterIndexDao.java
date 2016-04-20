/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.common.clinic.dao;

import com.jims.clinic.entity.PatMasterIndex;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 病人主索引DAO接口
 * @author zhaoning
 * @version 2016-04-19
 */
@MyBatisDao
public interface PatMasterIndexDao extends CrudDao<PatMasterIndex> {
	
}