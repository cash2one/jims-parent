/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;
import com.jims.clinic.entity.DocOperationGrade;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 门诊手术等级表DAO接口
 * @author qlx
 * @version 2016-05-06
 */
@MyBatisDao
public interface DocOperationGradeDao extends CrudDao<DocOperationGrade> {
	
}