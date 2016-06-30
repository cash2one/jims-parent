package com.jims.asepsis.bo;

import com.jims.asepsis.dao.AsepsisAntiRecDao;
import com.jims.asepsis.dao.AsepsisStockDao;
import com.jims.asepsis.entity.AsepsisAntiRec;
import com.jims.asepsis.entity.AsepsisLendRec;
import com.jims.asepsis.dao.AsepsisLendRecDao;
import com.jims.asepsis.entity.AsepsisStock;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 借物还物bo
* @author lgx
* @version 2016-06-27
*/
@Service
@Transactional(readOnly = false)
public class AsepsisLendRecBo extends CrudImplService<AsepsisLendRecDao, AsepsisLendRec>{

    @Autowired
    private AsepsisStockDao stockDao;
    @Autowired
    private AsepsisAntiRecDao antiDao;
    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<AsepsisLendRec> list) {
        if(list != null && list.size() > 0) {
            for(AsepsisLendRec entity : list) {
                if("4".equals(entity.getReturnFlag())){
                    if(!entity.getIsNewRecord()) {
                        Integer stock = entity.getStock() == null ? 0 : entity.getStock();
                        AsepsisStock stockParam = new AsepsisStock();
                        stockParam.setFromDept(entity.getToDept());
                        stockParam.setDocumentNo("T");
                        stockParam.setOrgId(entity.getOrgId());
                        List<AsepsisStock> stocks = stockDao.findListNoJoin(stockParam);
                        for (int i = 0; i < stocks.size(); i++) {
                            AsepsisStock asepsisStock = stocks.get(i);
                            Integer amount = asepsisStock.getAmount() == null ? 0 : asepsisStock.getAmount().intValue();
                            if (amount > stock) {
                                asepsisStock.setAmount(amount - stock.doubleValue());
                                asepsisStock.preUpdate();
                                stockDao.update(asepsisStock);
                                break;
                            } else if (amount == stock) {
                                stockDao.delete(asepsisStock.getId());
                                break;
                            } else {
                                stock = stock - amount;
                                stockDao.delete(asepsisStock.getId());
                            }
                        }
                    } else {
                        saveAntiData(entity);
                    }
                } else {
                    if(entity.getIsNewRecord()){
                        Integer stock = entity.getLendAmount() == null ? 0 : entity.getLendAmount().intValue();
                        AsepsisStock stockParam = new AsepsisStock();
                        stockParam.setFromDept("161303");
                        stockParam.setDocumentNo(entity.getExpDocumentNo());
                        stockParam.setOrgId(entity.getOrgId());
                        List<AsepsisStock> stocks = stockDao.findListNoJoin(stockParam);
                        for (int i = 0; i < stocks.size(); i++) {
                            AsepsisStock asepsisStock = stocks.get(i);
                            Integer amount = asepsisStock.getAmount() == null ? 0 : asepsisStock.getAmount().intValue();
                            if (amount > stock) {
                                asepsisStock.setAmount(amount - stock.doubleValue());
                                asepsisStock.preUpdate();
                                stockDao.update(asepsisStock);
                                break;
                            } else if (amount == stock) {
                                stockDao.delete(asepsisStock.getId());
                                break;
                            } else {
                                stock = stock - amount;
                                stockDao.delete(asepsisStock.getId());
                            }
                        }
                    } else {
                        saveAntiData(entity);
                    }
                }

                save(entity);
            }
        }
    }

    /**
     * 获取当天最大的编码
     * @param orgId
     * @return
     */
    public String getMaxDocumentNo(String orgId){
        return dao.getMaxDocumentNo(orgId);
    }

    /**
     * 检索有库存的
     * @param entity
     * @return
     */
    public List<AsepsisLendRec> findListWithStock(AsepsisLendRec entity){
        return dao.findListWithStock(entity);
    }

    private void saveAntiData(AsepsisLendRec entity){
        AsepsisAntiRec anti = new AsepsisAntiRec();
        anti.setImpDate(entity.getReturnDate());
        anti.preInsert();
        anti.setDocumnetNo(entity.getDocumentNo());
        anti.setAsepsisCode(entity.getItemCode());
        anti.setAsepsisName(entity.getItemName());
        anti.setAsepsisSpec(entity.getItemSpec());
        anti.setUnits(entity.getUnits());
        anti.setBelongDept(entity.getToDept());
        anti.setAsepsisState("0");
        anti.setAmount(entity.getLendAmount().intValue());
        anti.setOrgId(entity.getOrgId());
        anti.setItemNo(1);
        antiDao.insert(anti);
    }
}