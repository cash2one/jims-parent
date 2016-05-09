/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;


import com.jims.clinic.entity.ExamRptPattern;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * ExamRptPatternDAO接口
 * @author zhangpeng
 * @version 2016-04-27
 */
@MyBatisDao
public interface ExamRptPatternDao extends CrudDao<ExamRptPattern> {
	public List getExamRptPattern(String examSubClass);
    /**
     * 获取检查项目通过类别
     * @param orgId 机构id
     * @param className 父类别
     * @param subClassName 子类别
     * @return
     */
    public List<ExamRptPattern> listByClass(String orgId,String className,String subClassName);
}