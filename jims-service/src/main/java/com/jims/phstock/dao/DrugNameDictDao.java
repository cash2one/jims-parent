/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugNameDict;

import java.util.List;


/**
 * 药品名称DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugNameDictDao extends CrudDao<DrugNameDict> {
    /**
     * 查询所有药品名称列表通过拼音码
     * @param inputCode 拼音码
     * @return
     * @author txb
     * @version 2016-05-11
     */
    public List<DrugNameDict> findDrugNameDictList(String inputCode);

    /**
     * 查询所有药品名称列表通过拼音码
     * @param drugCode 拼音码
     * @return
     * @author txb
     * @version 2016-05-11
     */
    public List<DrugNameDict> listDrugNameDictByDrugCode(String drugCode);
	
}