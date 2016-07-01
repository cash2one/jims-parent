package com.jims.asepsis.dao;

import com.jims.asepsis.entity.AsepsisStock;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
* 包库存初始化Dao
* @author yangruidong
* @version 2016-06-27
*/
@MyBatisDao
public interface AsepsisStockDao extends CrudDao<AsepsisStock> {

    /**
     * 检索单表
     * @param entity
     * @return
     */
    public List<AsepsisStock> findListNoJoin(AsepsisStock entity);

    /**
     * 检索有库存的
     * @param entity
     * @return
     */
    public List<AsepsisStock> findListHasStock(AsepsisStock entity);
}