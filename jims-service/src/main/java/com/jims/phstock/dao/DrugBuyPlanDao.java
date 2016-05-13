package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugBuyPlan;

import java.util.Date;
import java.util.List;

/**
 * 药品采购计划DAO接口
 * @author lgx
 * @version 2016-05-11
 */
@MyBatisDao
public interface DrugBuyPlanDao extends CrudDao<DrugBuyPlan> {

    /**
     * 根据参数（必须有采购单据号bugId,其他也可包含所属结构主键orgId）删除
     * @param entity
     * @return
     */
    public int deleteByParameter(DrugBuyPlan entity);

    /**
     * 获取指定日期最大的采购单据号
     * @param date 日期
     * @param orgId 所属机构ID
     * @return
     */
    public String getMaxBuyId(Date date,String orgId);

    /**
     * 根据执行标志获取采购单据号
     * @param flag
     * @param orgId 所属机构ID
     * @return
     */
    public List<String> getBuyId(String flag,String orgId);

    /**
     * 根据主键直接删除数据，而非修改数据中的删除标志
     * @param id
     * @return
     */
    public int deleteInfo(String id);
}