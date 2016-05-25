package com.jims.clinic.api;

import com.jims.clinic.entity.DrugPrescMasterTemp;

import java.util.List;

/**
 * 门诊发药的API
 *
 * @author PangQian
 * @date2016/5/24 0024
 */
public interface DrugPrescTempServiceApi  {

    /**
     * 查询门诊代发药主记录的列表（已收费的）
     * @param dispensary 发药药局
     * @param dispensarySub 发药子药局
     * @return
     */
  public List<DrugPrescMasterTemp> getPrescMasterTemp(String dispensary,String dispensarySub);


}
