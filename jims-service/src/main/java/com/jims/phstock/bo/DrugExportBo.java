package com.jims.phstock.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.dao.*;
import com.jims.phstock.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private DrugProvideApplicationDao applicationDao;

    public void saveMasterAndDetail(DrugExportMaster master) {
        saveMasterAndDetail(master,false);
    }

    /**
     * 保存药品出库主表、关联的明细表，
     * 以及更新库房子单位出库流水号
     * 如果记账标志为1，则记账到药品库房
     * @param master 药品主表数据，内含有明细表List序列
     * @param isRequest 是否更新申请出库
     */
    public void saveMasterAndDetail(DrugExportMaster master,Boolean isRequest) {
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
                if(quantity < 0) throw new RuntimeException("库存不足！");
                // 记账
                if(master.getAccountIndicator() != null && 1 == master.getAccountIndicator()){
                    stock.setQuantity(quantity);
                    stockDao.update(stock);
                }
                detail.setInventory(quantity);
                if(isRequest && detail.getId() != null){
                    DrugProvideApplication application = applicationDao.get(detail.getId());
                    application.setProvideStorage(master.getStorage());
                    application.setSubStorage(master.getSubStorage());
                    application.setNoProvideQuantity("0".equals(application.getFlag())
                            ? application.getQuantity() - detail.getQuantity()
                            : application.getNoProvideQuantity() - detail.getQuantity());
                    if(application.getNoProvideQuantity() > 0){
                        application.setFlag("1");
                    } else {
                        application.setFlag("2");
                    }
                    applicationDao.update(application);
                }
                detail.preInsert();
                dao.insert(detail);
            }
        }
    }

    /**
     * 检索出库数据
     * @param master
     * @return
     */
    public List<DrugExportMaster> findMasterList(DrugExportMaster master){
        return masterDao.findList(master);
    }

    /**
     * 检索出库数据(包含库存)
     * @param detail
     * @param storage 库存管理单位
     * @param subStorage 存放库房
     * @return
     */
    public List<DrugExportDetail> findDetailListWithStock(DrugExportDetail detail,String storage,String subStorage){
        List<DrugExportDetail> details = dao.findList(detail);
        for(int i=0;i<details.size();i++){
            detail = details.get(i);
            DrugStock stock = new DrugStock();
            stock.setStorage(storage);
            stock.setDrugCode(detail.getDrugCode());
            stock.setDrugSpec(detail.getDrugSpec());
            stock.setUnits(detail.getUnits());
            stock.setFirmId(detail.getFirmId());
            stock.setBatchNo(detail.getBatchNo());
            stock.setSubStorage(subStorage);//存放库房
            stock.setOrgId(detail.getOrgId());
            stock.setPackageSpec(detail.getPackageSpec());
            List<DrugStock> stocks = stockDao.findList(stock);
            if (stocks != null && stocks.size() > 0) {
                double quantity = stocks.get(0).getQuantity();
                detail.setStock(quantity);
            } else {
                detail.setStock(0.0);
            }
        }
        return details;
    }


    /**
     * 查询一段时间内的出库记录
     * @param startTime
     * @param endTime
     * @param orgId
     * @param storageCode
     * @return
     */
    public List<DrugExportMaster> findExportData(String startTime, String orgId, String storageCode) {
        DrugExportMaster drugExportMaster=new DrugExportMaster();
        try {
            if(startTime!=null&&!"".equals(startTime)){
                Date d=new SimpleDateFormat("yyyy-MM-dd").parse(startTime);
                drugExportMaster.setExportDate(d);
            }
            drugExportMaster.setOrgId(orgId);
            drugExportMaster.setStorage(storageCode);
            drugExportMaster.setDelFlag("0");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return masterDao.findList(new DrugExportMaster());
     }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    public DrugExportMaster findById(String id) {
       return masterDao.get(id);
    }

    /**
     * 修改
     * @param drugExportMaster
     * @return
     */
    public DrugExportMaster update(DrugExportMaster drugExportMaster) {
        int i=masterDao.update(drugExportMaster);
        return drugExportMaster;
    }
}
