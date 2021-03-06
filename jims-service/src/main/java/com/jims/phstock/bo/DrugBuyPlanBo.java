package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.phstock.dao.DrugBuyPlanDao;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.entity.DrugBuyPlan;
import com.jims.phstock.entity.DrugStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lgx on 2016/6/16.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugBuyPlanBo extends CrudImplService<DrugBuyPlanDao, DrugBuyPlan> {

    @Autowired
    private DrugStockDao stockDao;

    /**
     * 批量保存
     * @param recordBatch 需保存的数据
     */
    public void save(List<DrugBuyPlan> recordBatch) {
        for(DrugBuyPlan entity : recordBatch){
            save(entity);
        }
    }

    /**
     * 批量删除和保存
     * @param recordBatch 需要保存的数据
     * @param ids 需要删除的Id,多个以 , 隔开
     */
    public void saveAndDelete(List<DrugBuyPlan> recordBatch,String ids){
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
    }

    /**
     * 根据参数删除
     * @param buyId 采购单据号
     * @param orgId 所属机构ID
     */
    public void delete(String buyId, String orgId) {
        DrugBuyPlan paramObj = new DrugBuyPlan();
        paramObj.setBuyId(buyId);
        paramObj.setOrgId(orgId);
        dao.deleteByParameter(paramObj);
    }

    /**
     * 根据主键进行删除数据，而非修改数据的删除标志
     * @param ids ，多个主键以 , 隔开
     */
    public void deleteInfo(String ids){
        String[] id = ids.split(",");
        for (int j = 0; j < id.length; j++) {
            dao.deleteInfo(id[j]);
        }
    }

    /**
     * 获取指定日期的下一个单据号
     * @param date
     * @return
     */
    public String getNextBuyId(Date date,String orgId){
        String currentBuyId = dao.getMaxBuyId(date,orgId);
        if(currentBuyId == null || currentBuyId.trim().length() < 12){
            return DateUtils.formatDate(date, "yyyyMMdd") + "0001";
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
        return getBuyId(flag,orgId,null,null);
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
        List<DrugBuyPlan> plans = dao.getBuyId(flag,orgId,buyer,storage);
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

    public void drugInStock(List<DrugBuyPlan> recordBatch){
        for(DrugBuyPlan plan : recordBatch){
            DrugStock stock = new DrugStock();
            stock.setStorage(plan.getStorage());
            stock.setDrugCode(plan.getDrugCode());
            stock.setDrugSpec(plan.getDrugSpec());
            stock.setUnits(plan.getUnits());
            stock.setFirmId(plan.getFirmId());
            stock.setBatchNo(plan.getBatchNo());
            stock.setSubStorage(plan.getSubStorage());//存放库房
            stock.setOrgId(plan.getOrgId());
            stock.setPackageSpec(plan.getPackSpec());
            stock.setPackageUnits(plan.getPackSpec());
            List<DrugStock> stocks = stockDao.findListNoJoin(stock);
            if (stocks != null && stocks.size() > 0) {
                stock = stocks.get(0);
            }
            Double quantity = (stock.getQuantity() != null ? stock.getQuantity() : 0)
                    + (plan.getExecutedNumber() != null ? plan.getExecutedNumber() : 0 );   //数量更新
            stock.setQuantity(quantity);
            stock.setDocumentNo(plan.getImportDocument());
            if(stock.getId() != null) {
                stock.preUpdate();
                stockDao.update(stock);
            } else {
                stock.setExpireDate(plan.getExpireDate());    //有效期
                stock.setPurchasePrice(plan.getPurchasePrice());  //进货价
                stock.setDiscount(plan.getDiscount());        //折扣
                stock.setSupplyIndicator(1);    //供应标志
                stock.preInsert();
                stockDao.insert(stock);
            }
            save(plan);
        }
    }

    /**
     * 获取当前机构和子机构的采购单据号
     * @param orgId,flag,
     * @return DrugBuyPlanList
     * zhuqi
     */
    public List<DrugBuyPlan> getBuyListByOrg(String flag,String orgId){
        return dao.getBuyListByOrg(flag, orgId);
    }
}
