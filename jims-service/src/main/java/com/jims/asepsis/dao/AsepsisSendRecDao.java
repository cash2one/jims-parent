package com.jims.asepsis.dao;

import com.jims.asepsis.entity.AsepsisSendRec;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

import java.util.List;

/**
* 送物领物Dao
* @author lgx
* @version 2016-06-27
*/
@MyBatisDao
public interface AsepsisSendRecDao extends CrudDao<AsepsisSendRec> {

    /**
     * 获取当天最大的编码
     * @param orgId
     * @return
     */
    public String getMaxDocumentNo(String orgId);

    /**
     * 检索有库存、在保质期内的数据
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListWithStock(AsepsisSendRec entity);

    /**
     * 检索没有库存、在保质期内的数据
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListNoStock(AsepsisSendRec entity);
}