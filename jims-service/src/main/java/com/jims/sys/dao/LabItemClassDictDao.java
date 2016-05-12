/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.LabItemClassDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 检验项目类别字典DAO接口
 * @author xueyx
 * @version 2016-05-05
 */
@MyBatisDao
public interface LabItemClassDictDao extends CrudDao<LabItemClassDict> {

    /**
     * 查询科室代码下的检验类别
     * @return
     */
    public List<LabItemClassDict> findListByDeptCode(@Param("deptCode") String deptCode, @Param("orgId")String orgId);

    /**
     * 获取列表
     */
    public List<LabItemClassDict> findAllList();

}