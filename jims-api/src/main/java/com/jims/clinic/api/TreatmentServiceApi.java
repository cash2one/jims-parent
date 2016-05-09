package com.jims.clinic.api;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.OutpOrdersCosts;

import java.util.List;

/**
 * 处置治疗
 *
 * @author PangQian
 * @date2016/5/7 0007
 */
public interface TreatmentServiceApi {
    /**
     * 通过病人标识查询处置治疗
     * @return
     */
    public List<OutpOrdersCosts>  findTreatment(OutpOrdersCosts outpOrdersCosts);

    /**
     * 保存处置治疗
     * @param outpOrdersCosts
     * @return
     * @author
     */
    public  String saveClinicItem(List<OutpOrdersCosts> outpOrdersCosts);

    /**
     * 查询项目名称
     */
  //  public List<ClinicItemDict> findClinicItem(String itemClass);


}
