package com.jims.phstock.api;

import com.jims.phstock.entity.DrugExportMaster;

/**
 * 药品出库接口
 * Created by heren on 2016/5/16.
 */
public interface DrugExportServiceApi {

    /**
     * 保存药品出库单主单和明细
     * @param master 主表内含有明细表List序列
     * @return 0 失败，1成功
     */
    public String saveMasterAndDetail(DrugExportMaster master);
}
