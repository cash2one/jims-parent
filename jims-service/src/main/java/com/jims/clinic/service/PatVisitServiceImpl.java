package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.api.PatVisitServiceApi;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.patient.entity.PatVisit;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by che on 2016/4/20.
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
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
    public List<PatientListDto> getPatientList(String deptCode) {
        return patVisitDao.getPatientListInHos(deptCode);
    }
}
