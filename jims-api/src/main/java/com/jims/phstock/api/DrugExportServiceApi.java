package com.jims.phstock.api;

import com.jims.phstock.entity.DrugExportDetail;
import com.jims.phstock.entity.DrugExportMaster;

import java.util.List;

/**
 * 药品出库接口
 * Created by heren on 2016/5/16.
 */
public interface DrugExportServiceApi {

    /**
     * 保存药品出库主表、关联的明细表，
     * 以及更新库房子单位出库流水号
     * 如果记账标志为1，则记账到药品库房
     * @param master 药品主表数据，内含有明细表List序列
     * @return 0 失败，1成功
     */
    public String saveMasterAndDetail(DrugExportMaster master);

    /**
     * 保存药品出库主表、关联的明细表
     * 以及更新库房子单位出库流水号
     * 如果记账标志为1，则记账到药品库房
     * @param master 药品主表数据，内含有明细表List序列
     * @param isRequest 是否更新申请出库
     * @return 0 失败，1成功
     */
    public String saveMasterAndDetail(DrugExportMaster master,Boolean isRequest);

    /**
     * 检索出库数据
     * @param master
     * @return
     */
    public List<DrugExportMaster> findMasterList(DrugExportMaster master);

    /**
     * 检索出库数据
     * @param detail
     * @return
     */
    public List<DrugExportDetail> findDetailList(DrugExportDetail detail);

    /**
     * 检索出库数据(包含库存)
     * @param detail
     * @param storage 库存管理单位
     * @param subStorage 存放库房
     * @return
     */
    public List<DrugExportDetail> findDetailListWithStock(DrugExportDetail detail,String storage,String subStorage);
}
