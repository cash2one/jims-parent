/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugDict;

import java.util.List;

/**
 * 药品字典DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugDictDao extends CrudDao<DrugDict> {
    /**
     * 通过药品代码查询药品列表
     * @param drugCode 药品代码
     * @return
     * @author txb
     */
    public List<DrugDict> listDrugDictByDrugCode(String drugCode);
}