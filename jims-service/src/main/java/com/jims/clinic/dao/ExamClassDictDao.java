/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.exam.entity.ExamClassDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ExamClassDictDAO接口
 * @author zhangpeng
 * @version 2016-04-26
 */
@MyBatisDao
public interface ExamClassDictDao extends CrudDao<ExamClassDict> {
    public List getEx(@Param("orgId")String orgId);
    /**
     * 通过orgID获取检查类别列表
     * @param orgId 机构id
     * @return 集合
     */
    public List<ExamClassDict> findListByOrgId(String orgId);
}