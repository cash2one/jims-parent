package com.jims.phstock.api;

import com.jims.phstock.vo.DrugInventoryCheckVo;

import java.util.List;

/**
 * 盘点记录api
 * @author txb
 * @version 2016-05-23
 */
public interface DrugInventoryCheckApi {

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

    /**
     * 暂存盘点数据
     * @param drugInventoryCheckVos
     * @return
     * @author txb
     */
    public String temporaryStorage(List<DrugInventoryCheckVo> drugInventoryCheckVos);
    /**
     * 保存盘点数据
     * @param drugInventoryCheckVos
     * @return 0失败，1成功
     * @author txb
     */
    public String saveInventory(List<DrugInventoryCheckVo> drugInventoryCheckVos);
}
