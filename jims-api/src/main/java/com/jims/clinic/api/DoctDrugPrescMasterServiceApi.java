package com.jims.clinic.api;

import com.jims.clinic.entity.DoctDrugPrescMaster;

import java.util.List;

/**
 * 待发药住院处方主记录Api
 * @author CTQ
 * @version 2016-05-16
 */
public interface DoctDrugPrescMasterServiceApi {
    /**
     * 根据参数查询列表
     * @param doctDrugPrescMaster
     * @author CTQ
     * @date 2016年5月16日15:50:38
     * @return
     */
    public List<DoctDrugPrescMaster> findListByParams(DoctDrugPrescMaster doctDrugPrescMaster);
    /**
     * 保存处方相关信息
     * @param doctDrugPrescMaster
     * @author CTQ
     * @date 2016年5月16日15:50:38
     * @return
     */
    public String savePresc(DoctDrugPrescMaster doctDrugPrescMaster);

    /**
     * 删除处方相关信息
     * @param id
     * @author CTQ
     * @date 2016年5月16日15:50:38
     * @return
     */
    public String deletePresc(String id);
}
