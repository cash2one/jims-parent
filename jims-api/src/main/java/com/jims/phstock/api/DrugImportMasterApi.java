package com.jims.phstock.api;

import com.jims.phstock.entity.DrugImportMaster;

import java.util.List;

/**
 * Created by wei on 2016/7/12.
 */
public interface DrugImportMasterApi {

    /**
     * 查询入库记录
     * @param orgId
     * @return
     */
    public List<DrugImportMaster> findMasterList(String orgId,String subStorage, String supplier,String startImportDate,String stopImportDate,String storageCode);

    /**
     * 保存
     * @param drugImportMaster
     * @return
     */
    public String update(DrugImportMaster drugImportMaster);
}
