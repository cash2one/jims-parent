package com.jims.clinic.api;


import com.jims.clinic.vo.ComeDeptVo;

/**
 * @author CTQ
 * @date 2016-06-06 09:42:39
 * 病人在院记录
 */
public interface PatsInHospitalServiceApi {

    public String saveHospInfo(ComeDeptVo comeDeptVo);

    public String turnOutDept(ComeDeptVo comeDeptVo);

}
