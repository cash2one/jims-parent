package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugImportServiceApi;
import com.jims.phstock.bo.DrugImportBo;
import com.jims.phstock.dao.DrugImportDetailDao;
import com.jims.phstock.dao.DrugImportMasterDao;
import com.jims.phstock.dao.DrugStockDao;
import com.jims.phstock.dao.DrugSubStorageDeptDao;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;
import com.jims.phstock.entity.DrugStock;
import com.jims.phstock.entity.DrugSubStorageDept;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;

/**
 * 药品入库明细单据Service
 * @author lgx
 * @version 2016-05-17
 */
@Service(version = "1.0.0")
public class DrugImportDetailService implements DrugImportServiceApi{

    @Autowired
    private DrugImportBo bo;

    /**'
     * 保存药品出库主表数据
     * @param entity
     * @return 0 失败，1成功
     */
    @Override
    public String save(DrugImportMaster entity) {
        String result = "0";
        try {
            bo.save(entity);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }

    /**
     * 保存药品出库主表数据
     * @param master
     * @return 0 失败，1成功
     */
    @Override
    public String saveMasterAndDetail(DrugImportMaster master) {
        String result = "0";
        try {
            bo.saveMasterAndDetail(master);
            result = "1";
        } catch (RuntimeException e){
        }
        return result;
    }
}