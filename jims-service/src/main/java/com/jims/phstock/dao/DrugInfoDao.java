/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugDict;
import com.jims.phstock.entity.DrugInfo;

import java.util.List;

/**
 * 药品毒理信息维护DAO接口
 * @author yangruidong
 * @version 2016-05-16
 */
@MyBatisDao
public interface DrugInfoDao extends CrudDao<DrugInfo> {

    /**
     * 根据药品代码，获取药品毒理信息
     * @param drugCode
     * @return
     * @author  yangruidong
     */
    public DrugInfo getDrugInfoByDrugCode(String drugCode);

}