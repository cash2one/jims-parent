/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.ClinicForRegist;

/**
 * 生成号表DAO接口
 * @author zhaoning
 * @version 2016-05-18
 */
@MyBatisDao
public interface ClinicForRegistDao extends CrudDao<ClinicForRegist> {
	
}