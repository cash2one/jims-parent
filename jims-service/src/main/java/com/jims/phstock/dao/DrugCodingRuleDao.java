/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugCodingRule;

import java.util.List;

/**
 * 药品编码字典生成DAO接口
 * @author luohk
 * @version 2016-05-10
 */
@MyBatisDao
public interface DrugCodingRuleDao extends CrudDao<DrugCodingRule> {

    /**
     * 获取药品编码规则列表
     * @return
     */
    public List<DrugCodingRule> findAll();

    /**
     * 通过编码名称获取编码长度
     *
     * @param className
     * @return
     */
    public DrugCodingRule findLevelWidth(String className);
}