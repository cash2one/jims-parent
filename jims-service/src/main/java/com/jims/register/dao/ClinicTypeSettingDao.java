package com.jims.register.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.ClinicTypeSetting;

/**
 * 号类字典DAO接口
 * @author 张耀
 * @version 2016-05-16
 */
@MyBatisDao
public interface ClinicTypeSettingDao extends CrudDao<ClinicTypeSetting> {
	
}