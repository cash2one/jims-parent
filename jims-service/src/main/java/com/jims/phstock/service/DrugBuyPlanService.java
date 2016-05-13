package com.jims.phstock.service;

import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.phstock.api.DrugBuyPlanApi;
import com.jims.phstock.dao.DrugBuyPlanDao;
import com.jims.phstock.entity.DrugBuyPlan;
import org.springframework.transaction.annotation.Transactional;

/**
 * 药品采购计划Service
 * @author lgx
 * @version 2016-05-11
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
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
     * @return
     */
    public List<String> getBuyId(String flag,String orgId){
        return dao.getBuyId(flag,orgId);
    }
}