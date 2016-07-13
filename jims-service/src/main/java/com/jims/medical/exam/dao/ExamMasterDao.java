/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.medical.exam.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.ExamMaster;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 检查主记录DAO接口
 * @author zhaoning
 * @version 2016-07-05
 */
@MyBatisDao
public interface ExamMasterDao extends CrudDao<ExamMaster> {
    /**
     * 查询 检查确认列表(门诊 || 住院)
     * @param performedBy 执行科室
     * @param outOrIn 门诊 or 住院
     * @return
     * @author zhaoning
     */
	public List<ExamAppoints> getExamAppointses(@Param("performedBy")String performedBy,@Param("outOrIn")String outOrIn);
}