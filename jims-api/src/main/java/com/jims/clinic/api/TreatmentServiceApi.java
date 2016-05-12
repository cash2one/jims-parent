package com.jims.clinic.api;

import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpTreatRec;

import java.util.List;

/**
 * Treatment
 *
 * @author PangQian
 * @date2016/5/11 0011
 */
public interface TreatmentServiceApi {


    /**
     * 通过clinicId查找治疗项目
     * @param clinicId
     * @return
     * pq
     */
    public List<OutpTreatRec> findTreatment(String clinicId);
    /**
     * 保存处置治疗
     * @param outpTreatRecs
     * @return
     * @author
     */
    public  String saveClinicItem(List<OutpTreatRec> outpTreatRecs,String clinicId);

    /**
     * 删除处置项目
     * pq
     * @param outpTreatRec
     * @return
     */
    public int deleteTreat(OutpTreatRec outpTreatRec);

    /**
     * 通过项目code，itemClass(检查治疗医嘱明细里的code和class)
     * @param itemCode
     * @param itemClass
     * @return
     */
  /*   public  List<OutpOrdersCosts> findSubTreatment(String itemCode,String itemClass);*/



}
