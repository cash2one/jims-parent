/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;


import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 在院病人记录DAO接口
 * @author pq
 * @version 2016-05-12
 */
@MyBatisDao
public interface PatsInHospitalDao extends CrudDao<PatsInHospital> {
	
}