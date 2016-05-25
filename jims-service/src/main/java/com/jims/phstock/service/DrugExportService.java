package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugExportServiceApi;

import com.jims.phstock.dao.DrugExportMasterDao;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.dao.DrugSubStorageDeptDao;
import com.jims.phstock.entity.DrugExportDetail;
import com.jims.phstock.dao.DrugExportDetailDao;
import com.jims.phstock.entity.DrugExportMaster;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.entity.DrugSubStorageDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 药品出库Service
 * @author lgx
 * @version 2016-05-23
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugExportService extends CrudImplService<DrugExportDetailDao, DrugExportDetail> implements DrugExportServiceApi{

    @Autowired
    private DrugExportMasterDao masterDao;

    @Autowired
    private DrugStockDao stockDao;

    @Autowired
    private DrugSubStorageDeptDao subStorageDeptDao;

    /**
     * 保存药品出库单主单和明细
     * @param master 主表内含有明细表List序列
     * @return 0 失败，1成功
     */
    @Override
    public String saveMasterAndDetail(DrugExportMaster master) {
        String result = "0";
        if(master != null){
            try {
                // 更新出库单可用流水号
                DrugSubStorageDept subStorageDept = subStorageDeptDao.get(master.getSubStorageDeptId());
                subStorageDept.setExportNoAva(subStorageDept.getExportNoAva() == null ?
                        1 : (subStorageDept.getExportNoAva() + 1));
                subStorageDept.preUpdate();
                subStorageDeptDao.update(subStorageDept);
                // 保存出库单主表
                master.preInsert();
                masterDao.insert(master);
                //保存出库单明细表
                List<DrugExportDetail> details = master.getDetailList();
                if(details != null && details.size() > 0){
                    for (DrugExportDetail detail : details) {
                        DrugStock stock = stockDao.get(detail.getDrugStockId());
                        Double quantity = (stock.getQuantity() != null ? stock.getQuantity() : 0) - detail.getQuantity();
                        // 记账
                        if(master.getAccountIndicator() != null && 1 == master.getAccountIndicator()){
                            stock.setQuantity(quantity);
                            stockDao.update(stock);
                        }
                        detail.setInventory(quantity);
                        save(detail);
                    }
                }
                result = "1";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}