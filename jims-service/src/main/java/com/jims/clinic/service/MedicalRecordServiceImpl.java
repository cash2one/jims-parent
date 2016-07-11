package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.MedicalRecordServiceApi;
import com.jims.clinic.bo.MedicalRecordBo;
import com.jims.clinic.vo.MedicalRecordVo;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2016/7/7.
 * 病案首页 service
 * @author zhaoning
 */
@Service(version="1.0.0")
public class MedicalRecordServiceImpl implements MedicalRecordServiceApi {
    @Autowired
    private MedicalRecordBo medicalRecordBo;

    /**
     * 根据病人ID 获取病案首页信息
     * @param patientId
     * @return
     * @author zhaoning
     */
    @Override
    public MedicalRecordVo getMedicalInfo(String patientId) {
        return medicalRecordBo.getMedRcInfo(patientId);
    }

    /**
     * 病案首页 更新 住院其他信息
     * @param patVisit
     * @param patientId
     * @return
     * @author zhaoning
     */
    @Override
    public String updateOtherInfo(PatVisit patVisit, String patientId) {
        return medicalRecordBo.updateOtherInfo(patVisit,patientId);
    }

    /**
     * 病案首页 更新 基本信息
     * @param patMasterIndex
     * @return
     * @author zhaoning
     */
    @Override
    public String updateBaseInfo(PatMasterIndex patMasterIndex) {
        return null;
    }
}
