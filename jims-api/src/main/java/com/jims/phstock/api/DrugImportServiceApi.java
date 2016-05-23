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
     * @return
     */
    public String save(DrugImportMaster entity);

    /**
     * 保存药品入库单详单
     * @param detail
     * @return
     */
    public String saveDetail(DrugImportDetail detail);

    /**
     * 批量保存药品入库单详单
     * @param details
     * @return
     */
    public String saveDetailBatch(List<DrugImportDetail> details);

}
