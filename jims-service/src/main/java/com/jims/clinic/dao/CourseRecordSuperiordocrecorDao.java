/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.CourseRecordSuperiordocrecor;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
/**
 * 病程记录--上级医师查房记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface CourseRecordSuperiordocrecorDao extends CrudDao<CourseRecordSuperiordocrecor> {
	
}