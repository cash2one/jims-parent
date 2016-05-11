package com.jims.phstock.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugBuyPlan;

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
}