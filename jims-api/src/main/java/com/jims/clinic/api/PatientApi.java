package com.jims.clinic.api;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.clinic.entity.PatMasterIndex;
import com.jims.clinic.entity.PatVisit;

/**
 * 病人基本信息
 *
 * @author PangQian
 * @date2016/4/23 0023
 */
public interface PatientApi {
    /**
     * 保存病人信息
     * @param patVisit
     * @param patMasterIndex
     * @param clinicMaster
     * @return
     */
    public String savePatient(PatVisit patVisit,PatMasterIndex patMasterIndex,ClinicMaster clinicMaster);


}
