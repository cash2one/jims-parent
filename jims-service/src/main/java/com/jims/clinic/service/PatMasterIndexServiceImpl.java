/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.jims.clinic.dao.EmrDiagnosisDao;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.finance.dao.PatsInTransferringDao;
import com.jims.finance.dao.PrepaymentRcptDao;
import com.jims.finance.entity.PatsInTransferring;
import com.jims.finance.entity.PrepaymentRcpt;
import com.jims.patient.api.PatMasterIndexServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.clinic.dao.PatMasterIndexDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.patient.entity.PatVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 病人主索引Service
 * @author zhaoning
 * @version 2016-04-19
 */
@Service
@Transactional(readOnly = true)
public  class PatMasterIndexServiceImpl extends CrudImplService<PatMasterIndexDao, PatMasterIndex> implements PatMasterIndexServiceApi {
    @Autowired
    PatsInHospitalDao patsInHospitalDao;
    @Autowired
    EmrDiagnosisDao emrDiagnosisDao;
    @Autowired
    PatsInTransferringDao patsInTransferringDao;
    @Autowired
    PatVisitDao patVisitDao;
    @Autowired
    PrepaymentRcptDao prepaymentRcptDao;
    @Override
    public List<PatMasterIndex> findList(PatMasterIndex patMasterIndex) {
        return dao.findList(patMasterIndex);
    }

    @Override
    public String saveMasterIndex(PatMasterIndex patMasterIndex) {
        int num = 0;
        /**1.保存病人主索引信息**/
        num = dao.insert(patMasterIndex);
        /**2.保存在院病人记录**/
        PatsInHospital patsInHospital = new PatsInHospital();
        patsInHospital.preInsert();
        patsInHospitalDao.insert(patsInHospital);

        /**3.保存诊断信息**/
        EmrDiagnosis emrDiagnosis = new EmrDiagnosis();
        emrDiagnosis.preInsert();
        emrDiagnosisDao.insert(emrDiagnosis);

        /**4.保存转科病人记录**/
        PatsInTransferring patsInTransferring = new PatsInTransferring();
        patsInTransferring.preInsert();
        patsInTransferringDao.insert(patsInTransferring);
        /**5.保存病人住院记录信息**/
        PatVisit patVisit = new PatVisit();
        patVisit.preInsert();
        patVisitDao.insert(patVisit);
        /**6.保存预交金记录信息**/
        PrepaymentRcpt prepaymentRcpt = new PrepaymentRcpt();
        prepaymentRcpt.preInsert();
        prepaymentRcptDao.insert(prepaymentRcpt);
        return String.valueOf(num);
    }

    @Override
    public String deleteMasterIndex(String ids) {
        int num = 0;
        num = dao.delete(ids);
        return String.valueOf(num);
    }
}