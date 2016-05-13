/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugDispPropertyDict;

import java.util.List;

/**
 * 摆药类别字典生成DAO接口
 * @author luohk
 * @version 2016-05-10
 */
@MyBatisDao
public interface DrugDispPropertyDictDao extends CrudDao<DrugDispPropertyDict> {

    /**
     * 获取摆药类别列表
     * @return
     */
    public List<DrugDispPropertyDict> findAll();
}