package com.jims.phstock.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.phstock.entity.DrugInventoryCheck;
import com.jims.phstock.vo.DrugInventoryCheckVo;

import java.util.List;

/**
 * 盘点记录DAO接口
 * @author txb
 * @version 2016-05-23
 */
@MyBatisDao
public interface DrugInventoryCheckDao extends CrudDao<DrugInventoryCheck> {
    /**
     * 生成盘点数据
     * @param storage 库房
     * @param orgId 组织机构
     * @param checkYearMonth 盘点日期
     * @return
     * @author txb
     */
    public List<DrugInventoryCheckVo> generateInventory(String storage,String orgId,String checkYearMonth,String subStorage);
    /**
     * 提取盘点数据
     * @param storage 库房
     * @param orgId 组织机构
     * @param checkYearMonth 盘点日期
     * @return
     * @author txb
     */
    public List<DrugInventoryCheckVo> extractInventory(String storage,String orgId,String checkYearMonth,String subStorage);
}