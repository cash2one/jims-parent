package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.api.PatVisitServiceApi;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by che on 2016/4/20.
 */
@Service(version = "1.0.0")

public class PatVisitServiceImpl extends CrudImplService<PatVisitDao,PatVisit> implements PatVisitServiceApi {
   @Autowired
    private   PatVisitDao  patVisitDao;

    /**
     * 查询病人列表
     * @param deptCode
     * @return
     * @author zhaoning
     */
    @Override
    public List<PatientListDto> getPatientList(String deptCode,String status,String patName,String startTime,String endTime) {
        List<PatientListDto> list= new ArrayList<PatientListDto>();
        if(status!=null && status.equals("0")){
            list=patVisitDao.getPatientListInHos(deptCode,patName,startTime,endTime);
        }else if(status!=null && status.equals("1")){
            list=patVisitDao.getPatientListOutHos(deptCode,patName,startTime,endTime);
        }
        return list;
    }

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

    /**
     * 查询 所有需要新建病历的病人信息
     * @return
     */
    @Override
    public List<PatMasterIndex> getPatMaster(String deptCode) {
        return patVisitDao.getPatMaster(deptCode);
    }
}
