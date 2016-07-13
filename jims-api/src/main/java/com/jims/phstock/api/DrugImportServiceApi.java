package com.jims.phstock.api;

import com.jims.phstock.entity.DrugImportDetail;
import com.jims.phstock.entity.DrugImportMaster;

import java.util.List;

/**
 * 药品入库接口
 * Created by heren on 2016/5/16.
 */
public interface DrugImportServiceApi {

    /**
     * 保存药品入库单主单
     * @param entity
     * @return 0 失败，1成功
     */
    public String save(DrugImportMaster entity);

    /**
     * 保存药品入库单主单和明细
     * @param master 主表内含有明细表List序列
     * @return 0 失败，1成功
     */
    public String saveMasterAndDetail(DrugImportMaster master);

    public List<DrugImportMaster> findImportData(String orgId, String startTime, String storageCode);

    public DrugImportMaster findById(String id);

    public void update(DrugImportMaster drugImportMaster);
}
