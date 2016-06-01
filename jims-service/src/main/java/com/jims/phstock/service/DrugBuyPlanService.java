package com.jims.phstock.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.phstock.api.DrugBuyPlanApi;
import com.jims.phstock.dao.DrugBuyPlanDao;
import com.jims.phstock.entity.DrugBuyPlan;


/**
 * 药品采购计划Service
 * @author lgx
 * @version 2016-05-11
 */
@Service(version = "1.0.0")

public class DrugBuyPlanService extends CrudImplService<DrugBuyPlanDao, DrugBuyPlan> implements DrugBuyPlanApi{

    /**
     * 批量保存
     * @param recordBatch 需保存的数据
     * @return 成功个数
     */
    @Override
    public String save(List<DrugBuyPlan> recordBatch) {
        int _successNum = 0;
        try {
            for(DrugBuyPlan entity : recordBatch){
                _successNum += Integer.valueOf(save(entity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(_successNum);
    }

    /**
     * 批量删除和保存
     * @param recordBatch 需要保存的数据
     * @param ids 需要删除的Id,多个以 , 隔开
     * @return
     */
    public String saveAndDelete(List<DrugBuyPlan> recordBatch,String ids){
        String result = "0";
        try{
            // 由于采购单据号、采购单序号唯一，所以必须先删除，才能保证保存的成功
            if(ids != null) {
                String[] id = ids.split(",");
                for (int j = 0; j < id.length; j++) {
                    dao.deleteInfo(id[j]);
                }
            }
             if(recordBatch != null) {
                 for (DrugBuyPlan entity : recordBatch) {
                     if (entity.getId() != null) {
                         entity.preUpdate();
                         dao.update(entity);
                     } else {
                         entity.preInsert();
                         dao.insert(entity);
                     }
                 }
             }
            result = "1";
        } catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据参数删除
     * @param buyId 采购单据号
     * @param orgId 所属机构ID
     * @return
     */
    @Override
    public String delete(String buyId, String orgId) {
        if(buyId == null || buyId.trim().length() < 1) return "0";
        DrugBuyPlan paramObj = new DrugBuyPlan();
        paramObj.setBuyId(buyId);
        paramObj.setOrgId(orgId);
        int _success = 0;
        try {
            _success += dao.deleteByParameter(paramObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(_success);
    }

    /**
     * 根据主键进行删除数据，而非修改数据的删除标志
     * @param ids ，多个主键以 , 隔开
     * @return
     */
    public String deleteInfo(String ids){
        int i=0;
        try {
            String[] id = ids.split(",");
            for (int j = 0; j < id.length; j++){
                i += dao.deleteInfo(id[j]);
            }
        }catch(Exception e){
            return i+"";
        }
        return i+"";
    }

    /**
     * 获取指定日期的下一个单据号
     * @param date
     * @return
     */
    @Override
    public String getNextBuyId(Date date,String orgId){
        String currentBuyId = dao.getMaxBuyId(date,orgId);
        if(currentBuyId == null || currentBuyId.trim().length() < 12){
            return DateUtils.formatDate(date,"yyyyMMdd") + "0001";
        }
        String nextBuyIdPrefix = currentBuyId.substring(0,8);
        String nextBuyIdSuffix = ("000" + (Integer.valueOf(currentBuyId.substring(8)) + 1));
        return nextBuyIdPrefix + nextBuyIdSuffix.substring(nextBuyIdSuffix.length() - 4);
    }

    /**
     * 根据执行标志获取采购单据号
     * @param flag
     * @param orgId 所属机构ID
     * @return
     */
    public List<String[]> getBuyId(String flag,String orgId){
        return getBuyId(flag,orgId,null);
    }

    /**
     * 根据执行标志获取指定采购员的采购单据号
     * @param flag
     * @param orgId 所属机构ID
     * @param buyer 采购员
     * @return
     */
    public List<String[]> getBuyId(String flag,String orgId,String buyer){
        List<DrugBuyPlan> plans = dao.getBuyId(flag,orgId,buyer);
        List<String[]> results = new ArrayList<String[]>();
        if(plans != null && plans.size() > 0){
            for(DrugBuyPlan o : plans){
                String[] result = new String[2];
                result[0] = o.getBuyId();
                result[1] = o.getFlag().toString();
                results.add(result);
            }
        }
        return results;
    }
}