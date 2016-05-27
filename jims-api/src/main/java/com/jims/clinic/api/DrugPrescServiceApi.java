package com.jims.clinic.api;

import com.jims.clinic.entity.DrugPrescDetail;
import com.jims.clinic.entity.DrugPrescMaster;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * 药品处方的API
 *
 * @author PangQian
 * @date2016/5/25 0025
 */
public interface DrugPrescServiceApi {
    /**
     * 查询药品处方主表列表信息
     * @param drugPrescMasterPage
     * @param drugPrescMaster
     * @return
     */
    public Page<DrugPrescMaster> findPage(Page<DrugPrescMaster> drugPrescMasterPage, DrugPrescMaster drugPrescMaster);

    /**
     * 查询药品处方明细表的列表信息
     * @param masterId
     * @return
     */
    public List<DrugPrescDetail> findDrugDetail(String masterId);

}
