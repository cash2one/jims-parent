package com.jims.asepsis.dao;

import com.jims.asepsis.entity.AsepsisStock;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
* 包库存初始化Dao
* @author yangruidong
* @version 2016-06-27
*/
@MyBatisDao
public interface AsepsisStockDao extends CrudDao<AsepsisStock> {


}