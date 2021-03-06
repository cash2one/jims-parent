package com.jims.doctor.clinicItem.api;

import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.vo.LoginInfo;

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
    public  String saveClinicItem(List<OutpTreatRec> outpTreatRecs,LoginInfo loginInfo);

    /**
     * 删除处置项目
     * pq
     * @param id
     * @return
     */
    public int deleteTreat(String id);

    /**
     * 通过项目code，itemClass(检查治疗医嘱明细里的code和class)
     * @param itemCode
     * @param itemClass
     * @return
     */
  /*   public  List<OutpOrdersCosts> findSubTreatment(String itemCode,String itemClass);*/



}
