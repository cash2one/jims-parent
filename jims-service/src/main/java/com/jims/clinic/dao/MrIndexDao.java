/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.MrIndex;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
 * 病案索引DAO接口
 * @author zhaoning
 * @version 2016-06-29
 */
@MyBatisDao
public interface MrIndexDao extends CrudDao<MrIndex> {
    /**
     * 根据 病人ID 获取 病案信息
     * @param patId
     * @return
     */
	public MrIndex getMrIndexBypat(String patId);
}