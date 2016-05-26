package com.jims.clinic.api;

import com.jims.clinic.entity.DoctDrugPrescDetail;
import com.jims.clinic.entity.DrugPrescDetailTemp;
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

    /**
     * 通过主表Id找到主表记录
     * @param id
     * @return
     */
    public DrugPrescMasterTemp get(String id);

    /**
     * 通过主表Id找到药品信息
     * @param masterId
     * @return
     */
    public List<DrugPrescDetailTemp> getDetail(String masterId);

  /**
   * 确认发药
   * @param id
   * @return
   */
   public  String confirmDrug(String id);
  /*缺少更新药品库存*/

}
