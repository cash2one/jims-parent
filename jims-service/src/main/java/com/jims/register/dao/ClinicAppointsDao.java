package com.jims.register.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.ClinicAppoints;

/**
 * 预约挂号DAO接口
 * @author zhangyao
 * @version 2016-05-20
 */
@MyBatisDao
public interface ClinicAppointsDao extends CrudDao<ClinicAppoints> {
	
}