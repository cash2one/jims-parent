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
     * 根据单据号，所属科室，代码等查询相应的送物领物表
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListNoId(AsepsisSendRec entity);
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
    /**
     * 科室消毒费统计：明细
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListFee(AsepsisSendRec entity);
    /**
     * 科室消毒费统计：汇总
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListFeeSum(AsepsisSendRec entity);
    /**
     * 科室消毒费统计：交叉(日期，科室)
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListFeeAcross(AsepsisSendRec entity);
    /**
     * 科室消毒费统计：年报表
     * @param entity
     * @return
     */
    public List<AsepsisSendRec> findListFeeYear(AsepsisSendRec entity);
}