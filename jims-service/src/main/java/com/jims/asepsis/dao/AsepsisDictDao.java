package com.jims.asepsis.dao;

import com.jims.asepsis.entity.AsepsisDict;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
* 包名称维护Dao
* @author yangruidong
* @version 2016-06-27
*/
@MyBatisDao
public interface AsepsisDictDao extends CrudDao<AsepsisDict> {

    /**
     * 根据科室和orgId 查询科室下的包
     * @param entity
     * @return
     */
    public List<AsepsisDict> findPageByDept(AsepsisDict entity);

    /**
     * 检索有库存的数据
     * @param entity
     * @return
     */
    public List<AsepsisDict> findListHasStock(AsepsisDict entity);
}