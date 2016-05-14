/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugPriceList;

import java.util.List;

/**
 * 药品价格DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugPriceListDao extends CrudDao<DrugPriceList> {

    /**
     * 检索当前日期所属机构的药品
     * @param orgId 机构ID
     * @return
     */
    public List<DrugDict> findDrugDict(String orgId);
}