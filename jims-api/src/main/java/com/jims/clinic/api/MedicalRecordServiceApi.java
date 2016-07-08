package com.jims.clinic.api;

import com.jims.clinic.vo.MedicalRecordVo;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;

/**
 * Created by Administrator on 2016/7/7.
 * 病案首页 API 接口
 * @author zhaoning
 */
public interface MedicalRecordServiceApi {
    /**
     * 根据病人ID获取 病案首页信息
     * @param patientId
     * @return
     * @author zhaoning
     */
    public MedicalRecordVo getMedicalInfo(String patientId);

    /**
     * 病案首页 更新住院其他信息
     * @param patVisit
     * @param patientId
     * @return
     * @author zhaoning
     */
    public String updateOtherInfo(PatVisit patVisit ,String patientId);

    /**
     * 病案首页 更新基本信息
     * @param patMasterIndex
     * @return
     * @author zhaoning
     */
    public String updateBaseInfo(PatMasterIndex patMasterIndex);

}
