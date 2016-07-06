/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.useBlood.dao;

import com.jims.blood.entity.BloodComponent;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 血液成分字典表DAO接口
 * @author zhangpeng
 * @version 2016-05-13
 */
@MyBatisDao
public interface BloodComponentDao extends CrudDao<BloodComponent> {

    /**
     * 获得血液成分列表
     * @return
     */
    public List<BloodComponent> getBloodComponent();
	
}