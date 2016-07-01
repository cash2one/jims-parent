package com.jims.asepsis.dao;

import com.jims.asepsis.entity.AsepsisLendRec;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
* 借物还物Dao
* @author lgx
* @version 2016-06-27
*/
@MyBatisDao
public interface AsepsisLendRecDao extends CrudDao<AsepsisLendRec> {

    /**
     * 获取当天最大的编码
     * @param orgId
     * @return
     */
    public String getMaxDocumentNo(String orgId);

    /**
     * 检索有库存的
     * @param entity
     * @return
     */
    public List<AsepsisLendRec> findListWithStock(AsepsisLendRec entity);


}