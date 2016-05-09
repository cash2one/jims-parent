/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.DocBloodCapacity;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
 * 门诊用血量申请DAO接口
 * @author qlx
 * @version 2016-05-06
 */
@MyBatisDao
public interface DocBloodCapacityDao extends CrudDao<DocBloodCapacity> {
	public void delBloodCapacity(String applyNum);
    public List<DocBloodCapacity> getDocOperationGradeList(String applyNum);
}