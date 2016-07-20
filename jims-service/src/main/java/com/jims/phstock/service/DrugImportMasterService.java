package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.phstock.api.DrugImportMasterApi;
import com.jims.phstock.bo.DrugImportMasterBo;
import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by wei on 2016/7/12.
 */
@Service(version = "1.0.0")
public class DrugImportMasterService implements DrugImportMasterApi {

    @Autowired
    private DrugImportMasterBo drugImportMasterBo;


    /**
     * 查询入库记录
     * @param orgId
     * @return
     */
    @Override
    public List<DrugImportMaster> findMasterList(String orgId,String subStorage, String supplier,String startImportDate,String stopImportDate,String storageCode) {
        return drugImportMasterBo.findMasterList(orgId, subStorage, supplier, startImportDate, stopImportDate, storageCode);
    }

    @Override
    public String update(DrugImportMaster drugImportMaster) {
        return drugImportMasterBo.update(drugImportMaster);
    }


}
