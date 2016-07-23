package com.jims.doctor.diagnosis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.vo.LoginInfo;
import com.jims.diagnosis.api.EmrDiagnosisServiceApi;
import com.jims.doctor.diagnosis.bo.EmrDiagnosisBo;
import com.jims.diagnosis.entity.EmrDiagnosis;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 诊断Service
 * @author zhangyao
 * @version 2015-12-27
 */
@Service(version = "1.0.0")
public class EmrDiagnosisServiceImpl implements EmrDiagnosisServiceApi{
	@Autowired
	private EmrDiagnosisBo emrDiagnosisBo;


    @Override
    public List<EmrDiagnosis> findAllListByParent(String parentId) {
        return emrDiagnosisBo.findAllListByParent(parentId);
    }

    @Override
    public List<EmrDiagnosis> findAllListByType(String parent, String type) {
        return emrDiagnosisBo.findAllListByType(parent,type);
    }

    @Override
    public List<EmrDiagnosis> findList(EmrDiagnosis emrDiagnosis) {
        return emrDiagnosisBo.findList(emrDiagnosis);
    }

    @Override
    public String saveDiagnosis(List<EmrDiagnosis> emrDiagnosis,LoginInfo loginInfo) {
        return emrDiagnosisBo.saveDiagnosis(emrDiagnosis,loginInfo);
    }

    @Override
    public String saveIn(List<EmrDiagnosis> emrDiagnosis) {
        return emrDiagnosisBo.saveIn(emrDiagnosis);
    }

    @Override
    public String delete(String ids) {
        return emrDiagnosisBo.delete(ids);
    }

    @Override
    public String save(EmrDiagnosis emrDiagnosis) {
        return emrDiagnosisBo.save(emrDiagnosis);
    }

    @Override
    public List<EmrDiagnosis> findAllDiagnosisForOne(EmrDiagnosis emrDiagnosis) {
        return emrDiagnosisBo.findAllDiagnosisForOne(emrDiagnosis);
    }

    @Override
    public EmrDiagnosis getDescription(String clinicId, String visitIds) {
        return emrDiagnosisBo.getDescription(clinicId,visitIds);
    }

    /**
     * 查询最大的序号
     * @param emrDiagnosis
     * @return
     */
    public int getMaxItemNo(EmrDiagnosis emrDiagnosis){
        return  emrDiagnosisBo.getMaxItemNo(emrDiagnosis);
    }

}