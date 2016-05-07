/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PriceList;

import java.util.List;

/**
 * 价格表DAO接口
 * @author 罗海昆
 * @version 2016-04-26
 */
@MyBatisDao
public interface PriceListDao extends CrudDao<PriceList> {
    /**
     * 通过项目代码对照项目价格
     * @param clinicItemCode 项目代码
     * @param orgId 机构代码
     * @return
     */
	public List<PriceList> findListByItem(String clinicItemCode , String orgId);
}