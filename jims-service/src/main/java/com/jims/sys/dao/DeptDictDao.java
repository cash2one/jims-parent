/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.DeptDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门信息DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface DeptDictDao extends CrudDao<DeptDict> {

    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAll();

    /**
     * 查询科室属性信息
     * @return
     */
    public List<DeptDict> findProperty();

    /**
     * 查询上级科室信息
     * @return
     */
    public List<DeptDict> findParent();

    /**
     * 查询科室代码下的所以科室
     * @return
     */
    public List<DeptDict> findListByCode(@Param("code")String code);
	
}