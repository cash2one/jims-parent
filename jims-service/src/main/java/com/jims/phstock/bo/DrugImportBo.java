package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugImportDetailDao;
import com.jims.phstock.dao.DrugImportMasterDao;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.dao.DrugSubStorageDeptDao;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.entity.DrugSubStorageDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lgx on 2016/6/16.
 */
@Service
@Component
@Transactional(readOnly = false)
public class DrugImportBo extends CrudImplService<DrugImportDetailDao, DrugImportDetail> {
    @Autowired
    private DrugImportMasterDao masterDao;

    @Autowired
    private DrugStockDao stockDao;

    @Autowired
    private DrugSubStorageDeptDao subStorageDeptDao;

    /**
     * 保存药品出库主表数据
     * @param entity
     */
    public void save(DrugImportMaster entity) {
        if (entity.getIsNewRecord()){
            entity.preInsert();
            masterDao.insert(entity);
        }else{
            entity.preUpdate();
            masterDao.update(entity);
        }
    }

    /**
     * 保存药品入库单主单和明细
     * @param master 主表内含有明细表List序列
     */
    public void saveMasterAndDetail(DrugImportMaster master){
        // 更新入库单可用流水号
        DrugSubStorageDept subStorageDept = subStorageDeptDao.get(master.getSubStorageDeptId());
        subStorageDept.setImportNoAva(subStorageDept.getImportNoAva()+1);
        subStorageDept.preUpdate();
        subStorageDeptDao.update(subStorageDept);
        // 保存入库单主表
        master.preInsert();
        masterDao.insert(master);
        //保存入库单明细表
        List<DrugImportDetail> details = master.getDetailList();
        if(details != null && details.size() > 0){
            for (DrugImportDetail detail : details) {
                DrugStock stock = new DrugStock();
                stock.setStorage(master.getStorage());
                stock.setDrugCode(detail.getDrugCode());
                stock.setDrugSpec(detail.getDrugSpec());
                stock.setUnits(detail.getUnits());
                stock.setFirmId(detail.getFirmId());
                stock.setBatchNo(detail.getBatchNo());
                stock.setOrgId(detail.getOrgId());
                stock.setPackageSpec(detail.getPackageSpec());
                List<DrugStock> stocks = stockDao.findList(stock);
                if (stocks != null && stocks.size() > 0) {
                    stock = stocks.get(0);
                }
                Double quantity = (stock.getQuantity() != null ? stock.getQuantity() : 0) + detail.getQuantity();
                // 记账
                if(master.getAccountIndicator() != null && 1 == master.getAccountIndicator()){
                    stock.setQuantity(quantity);
                    if(stock.getId() != null) {
                        stock.preUpdate();
                        stockDao.update(stock);
                    } else {
                        stock.setExpireDate(detail.getExpireDate());
                        stock.setPurchasePrice(detail.getPurchasePrice());
                        stock.setDiscount(detail.getDiscount());
                        stock.setQuantity(detail.getQuantity());
                        stock.setPackageUnits(detail.getPackageUnits());
                        stock.setSubPackage1(detail.getSubPackage1());
                        stock.setSubPackageUnits1(detail.getSubPackageUnits1());
                        stock.setSubPackageSpec1(detail.getSubPackageSpec1());
                        stock.setSubPackage2(detail.getSubPackage2());
                        stock.setSubPackageUnits2(detail.getSubPackageUnits2());
                        stock.setSubPackageSpec2(detail.getSubPackageSpec2());
                        stock.setSubStorage(master.getSubStorage());
                        stock.setDocumentNo(detail.getDocumentNo());
                        stock.setSupplyIndicator(1);
                        stock.preInsert();
                        stockDao.insert(stock);
                    }
                }
                detail.setInventory(quantity);
                save(detail);
            }
        }
    }
}
