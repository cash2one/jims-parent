package com.jims.phstock.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.DrugExportDetailDao;
import com.jims.phstock.dao.DrugExportMasterDao;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.dao.DrugSubStorageDeptDao;
import com.jims.phstock.entity.DrugExportDetail;
import com.jims.phstock.entity.DrugExportMaster;
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
public class DrugExportBo extends CrudImplService<DrugExportDetailDao, DrugExportDetail> {
    @Autowired
    private DrugExportMasterDao masterDao;

    @Autowired
    private DrugStockDao stockDao;

    @Autowired
    private DrugSubStorageDeptDao subStorageDeptDao;

    /**
     * 保存药品出库主表、关联的明细表，
     * 以及更新库房子单位出库流水号
     * 如果记账标志为1，则记账到药品库房
     * @param master 药品主表数据，内含有明细表List序列
     */
    public void saveMasterAndDetail(DrugExportMaster master) {
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
    }
}
