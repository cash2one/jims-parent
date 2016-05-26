package com.jims.clinic.api;

import com.jims.clinic.entity.DrugPrescDetail;
import com.jims.clinic.entity.DrugPrescMaster;

import java.util.List;

/**
 * 药品处方的API
 *
 * @author PangQian
 * @date2016/5/25 0025
 */
public interface DrugPrescServiceApi {

    /**
     * 保存药品处方主表
     * @param drugPrescMaster
     * @return
     */
    public String saveDrugaster(DrugPrescMaster drugPrescMaster);

    /**
     * 保存药品处方明细表
     * @param drugPrescDetailList
     * @return
     */
    public String saveDrugDetail(List<DrugPrescDetail> drugPrescDetailList);
}
