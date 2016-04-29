/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">EMR</a> All rights reserved.
 */
package com.jims.clinic.dao;


import com.jims.clinic.entity.EmrDataIcd10;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * ICD10编码DAO接口
 * @author zhaoning
 * @version 2015-12-04
 */
@MyBatisDao
public interface EmrDataIcd10Dao extends CrudDao<EmrDataIcd10> {
	
}