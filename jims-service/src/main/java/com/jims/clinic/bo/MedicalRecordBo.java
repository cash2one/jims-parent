package com.jims.clinic.bo;

import com.jims.clinic.dao.EmrDiagnosisDao;
import com.jims.clinic.dao.PatMasterIndexDao;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.clinic.dto.InpBillDetailDto;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.clinic.vo.MedicalRecordVo;
import com.jims.drugPresc.dao.InpBillDetailDao;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.patient.entity.PatVisit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 * 病案首页 BO
 * @author zhaoning
 */
@Service
@Transactional(readOnly = false)
public class MedicalRecordBo {
    @Autowired
    private PatMasterIndexDao patMasterIndexDao;
    @Autowired
    private PatVisitDao patVisitDao;
    @Autowired
    private EmrDiagnosisDao emrDiagnosisDao;
    @Autowired
    private InpBillDetailDao inpBillDetailDao;

    /**
     * 获取病案首页信息
      * @return
     * @author zhaoning
     */
 public MedicalRecordVo getMedRcInfo(String patientId){
     MedicalRecordVo medicalRecordVo= new MedicalRecordVo();
     //基本信息查询
     PatMasterIndex patMasterIndex=patMasterIndexDao.get(patientId);
     //入出院信息
     PatVisit patVisit=patVisitDao.getPatVisit(patientId);
     //诊断记录
     List<EmrDiagnosis> emrDiagnosisList= emrDiagnosisDao.getListDiagnosis(patientId);
     //手术记录
     //费用
     InpBillDetailDto inpBillDetailDto=inpBillDetailDao.getInpDetail(patientId);

     medicalRecordVo.setPatMasterIndex(patMasterIndex);
     medicalRecordVo.setPatVisit(patVisit);
     medicalRecordVo.setEmrDiagnosises(emrDiagnosisList);
     medicalRecordVo.setInpBillDetailDto(inpBillDetailDto);

     return medicalRecordVo;
 }

    /**
     * 病案首页 更新 其他信息
     * @param patVisit
     * @param patientId
     * @return
     * @author zhaoning
     */
    public String updateOtherInfo(PatVisit patVisit,String patientId){
       String code="";
        if(patVisit!=null && patVisit.getId()!=null){
            patVisit.setPatientId(patientId);
            patVisitDao.updateOtherInfo(patVisit);
            code="1";
        }
       return code;
    }

    /**
     * 病案首页 更新 基本信息
     * @param patMasterIndex
     * @return
     * @author zhaoning
     *
     */
    public String updateBaseInfo(PatMasterIndex patMasterIndex){
        String code="";
        return  code;
    }
}
