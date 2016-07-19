package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PerformDept;

/**
 * 价表项目执行科室dao层
 * Created by fyg on 2016/7/18.
 */
@MyBatisDao
public interface PerformDeptDao extends CrudDao<PerformDept> {
}
