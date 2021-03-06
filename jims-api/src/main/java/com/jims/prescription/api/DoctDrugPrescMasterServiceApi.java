package com.jims.prescription.api;

import com.jims.prescription.entity.DoctDrugPrescMaster;

import java.util.List;

/**
 * 待发药住院处方主记录Api
 * @author CTQ
 * @version 2016-05-16
 */
public interface DoctDrugPrescMasterServiceApi {
    /**
     * 根据ID查询处方信息
     * @author CTQ
     * @date 2016年5月17日14:19:04
     * @param id
     * @return
     */
    public DoctDrugPrescMaster get(String id);
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

    /**
     * 根据住院ID查询最大处方号+1
     * @param visitId
     * @return
     * @author CTQ
     * @date 2016/5/17
     */
    public Integer searchPrescNo(String visitId);


    /**
     * 查询近一个月的处方记录（住院代发药记录）
     * @param doctDrugPrescMaster
     * @return
     * @author pq
     */
    public List<DoctDrugPrescMaster> getDrugMasterList(DoctDrugPrescMaster doctDrugPrescMaster);

    /**
     * 发药的时候修改处方的状态
     * @param id
     * @return
     * @author pq
     */
    public String confirmDoctDrugPresc(String id);
}
