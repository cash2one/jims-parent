/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.MrOnLine;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 联机病历描述DAO接口
 * @author zhaoning
 * @version 2016-06-29
 */
@MyBatisDao
public interface MrOnLineDao extends CrudDao<MrOnLine> {
	
}