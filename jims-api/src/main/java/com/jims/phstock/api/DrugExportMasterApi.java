package com.jims.phstock.api;

import com.jims.phstock.entity.DrugExportMaster;


import java.util.List;

/**
 * Created by wei on 2016/7/12.
 */
public interface DrugExportMasterApi {

    /**
     * 查询c出库记录
     * @param orgId
     * @return
     */
    public List<DrugExportMaster> findMasterList(String orgId, String subStorage, String startImportDate, String stopImportDate, String storageCode);

    /**
     * 保存
     * @param drugExportMaster
     * @return
     */
    public String update(DrugExportMaster drugExportMaster);
}
