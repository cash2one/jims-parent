package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugExportServiceApi;

import com.jims.phstock.bo.DrugExportBo;
import com.jims.phstock.entity.DrugExportDetail;
import com.jims.phstock.entity.DrugExportMaster;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 药品出库Service
 * @author lgx
 * @version 2016-05-23
 */
@Service(version = "1.0.0")
public class DrugExportService implements DrugExportServiceApi{

    @Autowired
    private DrugExportBo bo;

    /**
     * 保存药品出库主表、关联的明细表，
     * 以及更新库房子单位出库流水号
     * 如果记账标志为1，则记账到药品库房
     * @param master 药品主表数据，内含有明细表List序列
     * @return 0 失败，1成功
     */
    @Override
    public String saveMasterAndDetail(DrugExportMaster master) {
        String result = "0";
        try {
            bo.saveMasterAndDetail(master);
            result = "1";
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 保存药品出库主表、关联的明细表
     * 以及更新库房子单位出库流水号
     * 如果记账标志为1，则记账到药品库房
     * @param master 药品主表数据，内含有明细表List序列
     * @param isRequest 是否更新申请出库
     * @return 0 失败，1成功
     */
    @Override
    public String saveMasterAndDetail(DrugExportMaster master,Boolean isRequest) {
        String result = "0";
        try {
            bo.saveMasterAndDetail(master,isRequest);
            result = "1";
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 检索出库数据
     * @param master
     * @return
     */
    public List<DrugExportMaster> findMasterList(DrugExportMaster master){
        return bo.findMasterList(master);
    }

    /**
     * 检索出库数据
     * @param detail
     * @return
     */
    public List<DrugExportDetail> findDetailList(DrugExportDetail detail){
        return bo.findList(detail);
    }

    /**
     * 检索出库数据(包含库存)
     * @param detail
     * @param storage 库存管理单位
     * @param subStorage 存放库房
     * @return
     */
    public List<DrugExportDetail> findDetailListWithStock(DrugExportDetail detail,String storage,String subStorage){
        return bo.findDetailListWithStock(detail,storage,subStorage);
    }

    @Override
    public List<DrugExportMaster> findExportData(String startTime, String orgId, String storageCode) {
        return bo.findExportData(startTime,orgId,storageCode);
    }

    @Override
    public DrugExportMaster findById(String id) {
        return bo.findById(id);
    }

    @Override
    public DrugExportMaster update(DrugExportMaster drugExportMaster) {
        return bo.update(drugExportMaster);
    }


    /**
     * 查询出库详情
     * @param documentNo
     * @return
     */
    @Override
    public List<DrugExportDetail> findDetailList(String documentNo) {
        return bo.findDetailList(documentNo);
    }

}