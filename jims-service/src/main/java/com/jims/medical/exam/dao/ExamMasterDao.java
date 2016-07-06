/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.medical.exam.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamMaster;

import java.util.List;

/**
 * 检查主记录DAO接口
 * @author zhaoning
 * @version 2016-07-05
 */
@MyBatisDao
public interface ExamMasterDao extends CrudDao<ExamMaster> {
    /**
     * 查询 检查确认列表
     * @param performedBy 执行科室
     * @return
     * @author zhaoning
     */
	public List<ExamAppoints> getExamAppointses(String performedBy);
}