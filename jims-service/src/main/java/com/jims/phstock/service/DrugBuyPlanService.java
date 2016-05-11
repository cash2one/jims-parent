package com.jims.phstock.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
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
}