/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.examclassdict.entity.ExamClassDict;

/**
 * ExamClassDictDAO接口
 * @author zhangpeng
 * @version 2016-04-26
 */
@MyBatisDao
public interface ExamClassDictDao extends CrudDao<ExamClassDict> {
	
}