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

    /**
     * 根据商品亚类 药品剂型,序号长度生成药品代码drug_code
     * @param secondType
     * @param drugForm
     * @param numLength
     * @return
     * @author txb
     *
     */
    public String  getDrugCodeByRule(String secondType,String drugForm,String numLength);

    /**
     * 根据药品名称或药品代码查询数据
     * @return
     * @author fengyuguang
     */
    public List<DrugDict> getByName(String drugCode,String drugName);
}