/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugExportClassDict;

import java.util.List;

/**
 * 出库分类字典生成DAO接口
 * @author luohk
 * @version 2016-05-10
 */
@MyBatisDao
public interface DrugExportClassDictDao extends CrudDao<DrugExportClassDict> {

    /**
     * 根据适用范围查询出库类型字典
     * @param storageType 出库类别
     * @return
     * @author luohk
     */
    public List<DrugExportClassDict> listDrugExportClassDictByStorageType(String storageType);

    /**
     * 获取出库数据列表
     * @return
     */
    public List<DrugExportClassDict> findAll();
}