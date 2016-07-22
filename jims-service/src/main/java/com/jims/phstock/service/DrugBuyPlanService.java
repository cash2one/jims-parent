package com.jims.phstock.service;

import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugBuyPlanApi;
import com.jims.phstock.bo.DrugBuyPlanBo;
import com.jims.phstock.entity.DrugBuyPlan;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 药品采购计划Service
 * @author lgx
 * @version 2016-05-11
 */
@Service(version = "1.0.0")
public class DrugBuyPlanService implements DrugBuyPlanApi{

    @Autowired
    private DrugBuyPlanBo bo;

    @Override
    public DrugBuyPlan get(String id) {
        return bo.get(id);
    }

    @Override
    public List<DrugBuyPlan> findList(DrugBuyPlan entity) {
        return bo.findList(entity);
    }

    @Override
    public String save(DrugBuyPlan entity) {
        String result = "0";
        try {
            bo.save(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 批量保存
     * @param recordBatch 需保存的数据
     * @return 0 失败，1成功
     */
    @Override
    public String save(List<DrugBuyPlan> recordBatch) {
        String result = "0";
        try {
            bo.save(recordBatch);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 批量删除和保存
     * @param recordBatch 需要保存的数据
     * @param ids 需要删除的Id,多个以 , 隔开
     * @return 0 失败，1成功
     */
    public String saveAndDelete(List<DrugBuyPlan> recordBatch,String ids){
        String result = "0";
        try {
            bo.saveAndDelete(recordBatch,ids);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    @Override
    public String delete(String ids) {
        String result = "0";
        try {
            bo.delete(ids);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 根据参数删除
     * @param buyId 采购单据号
     * @param orgId 所属机构ID
     * @return 0 失败，1成功
     */
    @Override
    public String delete(String buyId, String orgId) {
        String result = "0";
        try {
            bo.delete(buyId, orgId);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 根据主键进行删除数据，而非修改数据的删除标志
     * @param ids ，多个主键以 , 隔开
     * @return 0 失败，1成功
     */
    public String deleteInfo(String ids){
        String result = "0";
        try {
            bo.deleteInfo(ids);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 获取指定日期的下一个单据号
     * @param date
     * @return
     */
    @Override
    public String getNextBuyId(Date date,String orgId){
        return bo.getNextBuyId(date,orgId);
    }

    /**
     * 根据执行标志获取采购单据号
     * @param flag
     * @param orgId 所属机构ID
     * @return
     */
    public List<String[]> getBuyId(String flag,String orgId){
        return bo.getBuyId(flag, orgId);
    }

    /**
     * 根据执行标志获取指定采购员的采购单据号
     * @param flag
     * @param orgId 所属机构ID
     * @param buyer 采购员
     * @param storage
     * @return
     */
    public List<String[]> getBuyId(String flag,String orgId,String buyer,String storage){
        return bo.getBuyId(flag, orgId,buyer,storage);
    }

    /**
     * 药品入库
     * @param plan
     * @return 0失败，1成功
     */
    public String drugInStock(List<DrugBuyPlan> plan){
        try{
            bo.drugInStock(plan);
            return "1";
        } catch (Exception e){
            e.printStackTrace();
        }
        return "0";
    }

    /**
     * 获取当前机构和子机构的采购单据号
     * @param orgId,flag,
     * @return DrugBuyPlanList
     * zhuqi
     */
    public List<DrugBuyPlan> getBuyListByOrg(String flag,String orgId){
        return bo.getBuyListByOrg(flag, orgId);
    }
}