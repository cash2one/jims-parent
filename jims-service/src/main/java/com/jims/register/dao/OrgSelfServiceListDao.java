package com.jims.register.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.OrgSelfServiceList;

/**
 * 机构自定义服务DAO接口
 * @author lgx
 * @version 2016-05-31
 */
@MyBatisDao
public interface OrgSelfServiceListDao extends CrudDao<OrgSelfServiceList> {
	
}