package com.jims.phstock.api;

import com.jims.phstock.entity.DrugBuyPlan;

import java.util.Date;
import java.util.List;

/**
 * 药品采购计划接口
 * @author lgx
 * @version 2016/5/11
 */
public interface DrugBuyPlanApi {

    /**
     * 根据主键获取单条数据
     * @param id 主键
     * @return
     */
    public DrugBuyPlan get(String id);

    /**
     * 根据参数（可包含机构ID、采购单据号、执行标志、入库单号）检索未删除的数据序列
     * @param entity
     * @return
     */
    public List<DrugBuyPlan> findList(DrugBuyPlan entity);

    /**
     * 保存
     * @param entity
     * @return 0 失败，1 成功
     */
    public String save(DrugBuyPlan entity);

    /**
     * 批量保存
     * @param recordBatch 需保存的数据
     * @return 0 失败，1 成功
     */
    public String save(List<DrugBuyPlan> recordBatch);

    /**
     * 批量删除和保存
     * @param recordBatch 需要保存的数据
     * @param ids 需要删除的Id,多个以 , 隔开
     * @return 0 失败，1 成功
     */
    public String saveAndDelete(List<DrugBuyPlan> recordBatch,String ids);

    /**
     * 根据主键进行删除
     * @param ids ，多个主键以 , 隔开
     * @return 0 失败，1 成功
     */
    public String delete(String ids);

    /**
     * 根据参数删除
     * @param buyId 采购单据号
     * @param orgId 所属机构ID
     * @return 0 失败，1 成功
     */
    public String delete(String buyId,String orgId);

    /**
     * 根据主键进行删除数据，而非修改数据的删除标志
     * @param ids ，多个主键以 , 隔开
     * @return 0 失败，1 成功
     */
    public String deleteInfo(String ids);

    /**
     * 获取指定日期的下一个单据号
     * @param date
     * @param orgId 所属机构ID
     * @return
     */
    public String getNextBuyId(Date date,String orgId);

    /**
     * 根据执行标志获取采购单据号
     * @param flag
     * @param orgId 所属机构ID
     * @return
     */
    public List<String[]> getBuyId(String flag,String orgId);

    /**
     * 根据执行标志获取指定采购员的采购单据号
     * @param flag
     * @param orgId 所属机构ID
     * @param buyer 采购员
     * @return
     */
    public List<String[]> getBuyId(String flag,String orgId,String buyer);
}
