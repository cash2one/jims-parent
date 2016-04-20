/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
/**
 * 门诊医生收费明细DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface OutpOrdersCostsDao extends CrudDao<OutpOrdersCosts> {
	
}