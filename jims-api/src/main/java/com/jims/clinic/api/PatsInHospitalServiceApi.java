package com.jims.clinic.api;


import com.jims.clinic.entity.PatsInHospital;
import com.jims.clinic.vo.ComeDeptVo;

/**
 * @author CTQ
 * @date 2016-06-06 09:42:39
 * 病人在院记录
 */
public interface PatsInHospitalServiceApi {


    public String saveHospInfo(ComeDeptVo comeDeptVo);

    /**
     * 转科
     * @param comeDeptVo
     * @return
     */
    public String turnOutDept(ComeDeptVo comeDeptVo);

    /**
     * 查询病人是否已入院
     * @param patientId
     * @return
     */
    public PatsInHospital findByPatientId(String patientId);

}
