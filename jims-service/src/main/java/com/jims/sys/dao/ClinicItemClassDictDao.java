/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.ClinicItemClassDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 诊疗项目分类字典DAO接口
 * @author xueyx
 * @version 2016-05-04
 */
@MyBatisDao
public interface ClinicItemClassDictDao extends CrudDao<ClinicItemClassDict> {
    /**
     * 查询科室代码下的检验类别
     * @return
     */
    public List<ClinicItemClassDict> findListByDeptCode(@Param("deptCode")String deptCode);
}