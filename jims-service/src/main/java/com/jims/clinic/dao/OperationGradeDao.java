/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.OperationGrade;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 手术等级DAO接口
 * @author qlx
 * @version 2016-04-26
 */
@MyBatisDao
public interface OperationGradeDao extends CrudDao<OperationGrade> {
	public void delOperation(String operationId);
}