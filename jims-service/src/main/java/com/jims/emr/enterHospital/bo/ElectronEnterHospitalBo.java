package com.jims.emr.enterHospital.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.vo.LoginInfo;
import com.jims.diagnosis.entity.EmrDiagnosis;
import com.jims.doctor.diagnosis.dao.EmrDiagnosisDao;
import com.jims.emr.enterHospital.dao.ElectronEnterHospitalDao;
import com.jims.enterHospital.entity.ElectronEnterHospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 病历文书--入院记录Service
 * @author zhaonig
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = false)
public  class ElectronEnterHospitalBo extends CrudImplService<ElectronEnterHospitalDao,ElectronEnterHospital> {
    @Autowired
    private ElectronEnterHospitalDao electronEnterHospitalDao;
    @Autowired
    private EmrDiagnosisDao emrDiagnosisDao;

    /**
     * 保存病历文书
     * @param electronEnterHospital
     * @author pq
     * @return
     */
    public String saveEnter(ElectronEnterHospital electronEnterHospital,LoginInfo loginInfo){
        int num = 0;

        if (electronEnterHospital!=null) {
            electronEnterHospital.setOrgId(loginInfo.getOrgId());
            String	str = save(electronEnterHospital);
            num =Integer.parseInt(str==null?"0":str);

            List<EmrDiagnosis> emrDiagnosisList = electronEnterHospital.getDiagnosisList();
            if (emrDiagnosisList!=null) {
                if (emrDiagnosisList.size() > 0) {
                    for (int i = 0; i < emrDiagnosisList.size(); i++) {
                        EmrDiagnosis diagnosis = emrDiagnosisList.get(i);
                        if(diagnosis.getId()!=null && !"".equals(diagnosis.getId())){
                            emrDiagnosisDao.delete(diagnosis.getId());
                            diagnosis.setId("");
                        }
                        diagnosis.setDiagnosisParent(electronEnterHospital.getId());
                        diagnosis.setParentId("0");
                        diagnosis.setDiagnosisDoc(loginInfo.getPersionId());
                        diagnosis.setOrgId(loginInfo.getOrgId());
                        diagnosis.setItemNo(i+1);
                        try {
                            if (diagnosis.getIsNewRecord()) {
                                diagnosis.preInsert();
                                num = emrDiagnosisDao.insert(diagnosis);
                            } else {
                                diagnosis.preUpdate();
                                num = emrDiagnosisDao.update(diagnosis);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            return num + "";
                        }

                    }
                    return num + "";
                }
            }
        }



        return num+"";
    }

    /**
     * 查询病历文书
     * @param electronEnterHospital
     * @author pq
     * @return
     */

    public ElectronEnterHospital getElectronEnteHos(ElectronEnterHospital electronEnterHospital) {
        return electronEnterHospitalDao.getElectronEnteHos(electronEnterHospital);
    }


}