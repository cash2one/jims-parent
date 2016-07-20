package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugExportMasterApi;
import com.jims.phstock.bo.DrugExportMasterBo;
import com.jims.phstock.bo.DrugImportMasterBo;
import com.jims.phstock.entity.DrugExportMaster;
import com.jims.phstock.entity.DrugImportMaster;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wei on 2016/7/12.
 */
@Service(version = "1.0.0")
public class DrugExportMasterService implements DrugExportMasterApi {

    @Autowired
    private DrugExportMasterBo drugExportMasterBo;

    /**
     * 查询出库记录
     * @param orgId
     * @return
     */
    @Override
    public List<DrugExportMaster> findMasterList(String orgId,String subStorage,String startImportDate,String stopImportDate,String storageCode) {
        return drugExportMasterBo.findMasterList(orgId, subStorage,  startImportDate, stopImportDate, storageCode);
    }

    @Override
    public String update(DrugExportMaster drugExportMaster) {
        return drugExportMasterBo.update(drugExportMaster);
    }


}
