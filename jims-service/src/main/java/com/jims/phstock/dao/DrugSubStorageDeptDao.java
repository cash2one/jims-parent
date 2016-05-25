package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugSubStorageDept;

/**
 * 药品库存子单位DAO接口
 * @author zhaoning
 * @version 2016-04-22
 */
@MyBatisDao
public interface DrugSubStorageDeptDao extends CrudDao<DrugSubStorageDept> {

}