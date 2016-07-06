/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;


import com.jims.exam.entity.ExamSubclassDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ExamSubclassDictDAO接口
 * @author zhangpeng
 * @version 2016-04-27
 */
@MyBatisDao
public interface ExamSubclassDictDao extends CrudDao<ExamSubclassDict> {
	public List getEx(@Param("examClassName")String examClassName,@Param("orgId")String orgId);
    /**
     * 通过orgID获取检查子类别列表
     * @param orgId 机构id
     * @return 集合
     */
    public List<ExamSubclassDict> findListByOrgId(String orgId);

    /**
     * 获取当前类别子类项目
     * @param orgId 机构id
     * @param className 父类别
     * @return
     */
    public List<ExamSubclassDict> listByClass(String orgId,String className);
}