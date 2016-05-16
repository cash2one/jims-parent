package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.patient.api.PatVisitServiceApi;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.patient.entity.PatVisit;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by che on 2016/4/20.
 */
@Service(version ="1.0.0")
@Transactional(readOnly = true)
public class PatVisitServiceImpl extends CrudImplService<PatVisitDao, PatVisit> implements PatVisitServiceApi {
    @Autowired
    private PatVisitDao patVisitDao;

    /**
     * 点击用血申请获取病人信息通过patient_id获得
     *
     * @param patientId
     * @return
     */
    @Override
    public PatVisit getPatientInformation(String patientId) {
        PatVisit patVisit = patVisitDao.getPatientInformation(patientId);
        return patVisit;
    }
}
