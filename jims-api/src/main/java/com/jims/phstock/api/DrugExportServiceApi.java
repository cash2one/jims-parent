package com.jims.phstock.api;

import com.jims.phstock.entity.DrugExportMaster;

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
}
