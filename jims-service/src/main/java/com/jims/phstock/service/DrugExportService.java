package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugExportServiceApi;

import com.jims.phstock.bo.DrugExportBo;
import com.jims.phstock.entity.DrugExportMaster;
import org.springframework.beans.factory.annotation.Autowired;


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
}