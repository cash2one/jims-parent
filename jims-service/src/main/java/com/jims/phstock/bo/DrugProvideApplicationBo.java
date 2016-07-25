package com.jims.phstock.bo;

import com.jims.phstock.dao.DrugPriceListDao;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.entity.DrugPriceList;
import com.jims.phstock.entity.DrugProvideApplication;
import com.jims.phstock.dao.DrugProvideApplicationDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.vo.DrugProvideApplicationVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 录入申请bo
 *
 * @author yangruidong
 * @version 2016-07-04
 */
@Service
@Transactional(readOnly = false)
public class DrugProvideApplicationBo extends CrudImplService<DrugProvideApplicationDao, DrugProvideApplication> {

    @Autowired
    private DrugPriceListDao priceDao;
    @Autowired
    private DrugStockDao stockDao;
    /**
     * 批量保存（插入或更新）
     *
     * @param list
     */
    public void save(List<DrugProvideApplication> list) {
        if (list != null && list.size() > 0) {
            for (DrugProvideApplication entity : list) {
                save(entity);
            }
        }
    }

    /**
     * 保存  增删改
     *
     * @param drugProvideApplicationVo
     * @return
     * @author yangruidong
     */
    public List<DrugProvideApplication> saveAll(DrugProvideApplicationVo<DrugProvideApplication> drugProvideApplicationVo) {
        List<DrugProvideApplication> newUpdateDict = new ArrayList<DrugProvideApplication>();
        List<DrugProvideApplication> inserted = drugProvideApplicationVo.getInserted();
        List<DrugProvideApplication> updated = drugProvideApplicationVo.getUpdated();
        List<DrugProvideApplication> deleted = drugProvideApplicationVo.getDeleted();
        //插入
        for (DrugProvideApplication drugProvideApplication : inserted) {
            drugProvideApplication.preInsert();
            drugProvideApplication.setEnterDateTime(new Date());
            int num = dao.insert(drugProvideApplication);
        }
        //更新
        for (DrugProvideApplication drugProvideApplication : updated) {
            drugProvideApplication.preUpdate();
            int num = dao.update(drugProvideApplication);
        }

        //删除
        List<String> ids = new ArrayList<String>();

        for (DrugProvideApplication drugProvideApplication : deleted) {
            ids.add(drugProvideApplication.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }

        return newUpdateDict;
    }

    /**
     *查询去除重复的申请号
     * @param entity
     * @return
     */
    public List<DrugProvideApplication> findDocumentByDistinct(DrugProvideApplication entity){
        return  dao.findDocumentByDistinct(entity);
    }

    /**
     *查询去除重复的申请时间和单位
     * @param entity
     * @return
     */
    public List<DrugProvideApplication> findListByDistinct(DrugProvideApplication entity){
       return dao.findListByDistinct(entity);
    }

    /**
     * 检索(含有价格)
     * @param entity
     * @param storage 库存管理单位
     * @param subStorage 存放库房
     * @return
     */
    public List<DrugProvideApplication> findListWithPrice(DrugProvideApplication entity,String storage,String subStorage){
        List<DrugProvideApplication> list = dao.findList(entity);
        for(DrugProvideApplication application : list){
            DrugPriceList price = new DrugPriceList();
            price.setOrgId(application.getOrgId());
            price.setDrugCode(application.getDrugCode());
            price.setMinSpec(application.getDrugSpec());
            price.setMinUnits(application.getUnits());
            price.setDrugSpec(application.getPackageSpec());
            price.setUnits(application.getPackageUnits());
            price.setFirmId(application.getFirmId());
            price.setStopDate(new Date());
            List<DrugPriceList> priceList = priceDao.findListNoJoin(price);
            if(priceList != null && priceList.size() > 0){
                application.setRetailPrice(priceList.get(0).getRetailPrice());
                application.setTradePrice(priceList.get(0).getTradePrice());
            }
            DrugStock stock = new DrugStock();
            stock.setStorage(storage);
            stock.setDrugCode(application.getDrugCode());
            stock.setDrugSpec(application.getDrugSpec());
            stock.setUnits(application.getUnits());
            stock.setFirmId(application.getFirmId());
            stock.setBatchNo(application.getBatchNo());
            stock.setSubStorage(subStorage);//存放库房
            stock.setOrgId(application.getOrgId());
            stock.setPackageSpec(application.getPackageSpec());
            stock.setPackageUnits(application.getPackageUnits());
            List<DrugStock> stocks = stockDao.findList(stock);
            if (stocks != null && stocks.size() > 0) {
                double quantity = stocks.get(0).getQuantity();
                application.setStock(quantity);
                application.setDrugStockId(stocks.get(0).getId());
            } else {
                application.setStock(0.0);
            }
        }
        return list;
    }
}