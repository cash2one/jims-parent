package com.jims.patient.api;

import com.jims.patient.entity.PatVisit;

/**
 * 病人住院记录信息api
 * @author Chefj
 * @version 2016-04-20
 */
public interface PatVisitServiceApi {
    /**
     * 新增\修改 病人住院记录信息
     * @param patVisit
     */
   public String  save(PatVisit patVisit );

    /**
     * 点击用血申请获取病人信息通过patient_id获得
     * @param patientId
     * @return
     */
    public PatVisit getPatientInformation(String patientId);

}
