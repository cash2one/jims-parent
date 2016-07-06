package com.jims.prescription.api;

import com.jims.prescription.entity.DoctDrugPrescDetail;

import java.util.List;

/**
 * 待发药住院处方明细记录API
 * @author CTQ
 * @version 2016-05-16
 */
public interface DoctDrugPrescDetailServiceApi {

    /**
     * @param prescMasterId 传递参数
     * @return List    返回类型
     * @Title: findListByPrescMasterId
     * @Desription: (根据条件处方主记录ID查询明细列表)
     * @author CTQ
     * @date 2016/5/16
     */
    public List<DoctDrugPrescDetail> findListByPrescMasterId(String prescMasterId);

}
