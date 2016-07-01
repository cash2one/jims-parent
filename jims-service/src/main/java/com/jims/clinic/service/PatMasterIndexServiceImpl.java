/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.*;
import com.jims.clinic.entity.EmrDiagnosis;
import com.jims.clinic.entity.PatHospitalNotice;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.dao.PatsInTransferringDao;
import com.jims.finance.dao.PrepaymentRcptDao;
import com.jims.finance.entity.PatsInTransferring;
import com.jims.finance.entity.PrepaymentRcpt;
import com.jims.patient.api.PatMasterIndexServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.common.service.impl.CrudImplService;
import com.jims.patient.entity.PatVisit;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.Date;
import java.util.List;


/**
 * 病人主索引Service
 * @author zhaoning
 * @version 2016-04-19
 */
@Service(version = "1.0.0")

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
    @Autowired
    PatHospitalNoticeDao patHospitalNoticeDao;
    /**
     * 查询入院患者信息
     * @param patMasterIndex
     * @author CTQ
     * @return
     */
    @Override
    public List<PatMasterIndex> findList(PatMasterIndex patMasterIndex) {
        return dao.findList(patMasterIndex);
    }
    /**
     * 保存入院患者信息
     * @param patMasterIndex
     * @author CTQ
     * @return
     */
    @Override
    public String saveMasterIndex(PatMasterIndex patMasterIndex) {
        int num = 0;
        PatsInHospital patsInHospital = new PatsInHospital();
        EmrDiagnosis emrDiagnosis = new EmrDiagnosis();
        PatsInTransferring patsInTransferring = new PatsInTransferring();
        PatVisit patVisit = new PatVisit();
        PrepaymentRcpt prepaymentRcpt = new PrepaymentRcpt();
        if(patMasterIndex!=null&&patMasterIndex.getId()!=null&&!"".equals(patMasterIndex.getId())){
            num = dao.update(patMasterIndex);
        }else{
            /**1.保存病人主索引信息**/
            patMasterIndex.preInsert();
            num = dao.insert(patMasterIndex);
        }
        /**2.保存在院病人记录**/
        copytoInHospital(patMasterIndex, patsInHospital);
        patsInHospital.preInsert();
        patsInHospitalDao.insert(patsInHospital);
        /**3.保存诊断信息**/
        copytoDiagnosis(patMasterIndex, emrDiagnosis);
        emrDiagnosis.preInsert();
        emrDiagnosisDao.insert(emrDiagnosis);
        /**4.保存转科病人记录**/
        copytoInTrans(patMasterIndex, patsInTransferring);
        patsInTransferring.preInsert();
        patsInTransferringDao.insert(patsInTransferring);
        /**5.保存病人住院记录信息**/
        copytoVisit(patMasterIndex, patVisit);
        patVisit.preInsert();
        patVisitDao.insert(patVisit);
        /**6.保存预交金记录信息**/
        prepaymentRcpt.preInsert();
//      prepaymentRcptDao.insert(prepaymentRcpt);

        return String.valueOf(num);
    }
    /**
     * 取消入院患者登记信息
     * @param ids
     * @author CTQ
     * @return
     */
    @Override
    public String deleteMasterIndex(String ids) {
        int num = 0;
        if(ids!=null&&!"".equals(ids)&&!ids.contains(",")){
            cancel(ids);
        }else if(ids.contains(",")){
            String[] strs = ids.split(",");
            for(String str : strs){
                cancel(str);
            }
        }

        return String.valueOf(num);
    }
    public int cancel(String id){
        int num = 0;
        try {
            PatMasterIndex patMasterIndex = dao.get(id);
            /**删除在院病人记录**/
            //DELETE From pats_in_hospital where pats_in_hospital.patient_id ='02000030'
            num = patsInHospitalDao.deleteByPatientId(id);
            /**删除诊断记录**/
            EmrDiagnosis emrDiagnosis = new EmrDiagnosis();
            emrDiagnosis.setParentId(id);
            emrDiagnosis.setType("2");
            emrDiagnosis.setItemNo(1);
            emrDiagnosis.setDiagnosisDate(patMasterIndex.getCreateDate());
            emrDiagnosisDao.delDiagnosis(emrDiagnosis);
            /**删除转科病人记录**/
            patsInTransferringDao.deleteByPatientId(id);
            /**删除病人住院记录**/
            PatVisit patVisit = new PatVisit();
            patVisit.setPatientId(id);
            patVisit.setAdmissionDateTime(patMasterIndex.getCreateDate());
            patVisitDao.delVisit(patVisit);
            /**更新病人主索引信息**/
            dao.updateInpno(id);
            /**更新住院通知单**/
            //update pat_hospital_notice set visit_id =null where pat_hospital_notice.patient_id ='02000030' and notice_id =NULL
            PatHospitalNotice patHospitalNotice = new PatHospitalNotice();
            patHospitalNotice.setPatientId(id);
            patHospitalNoticeDao.updateNotice(patHospitalNotice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }
    /**
     * 护理-查询待入科室床位病人列表
     * @author CTQ
     * @date 2016-06-06 09:23:31
     * @return
     */
    @Override
    public List<ComeDeptVo> findWaitFrom() {
        return dao.findWaitFrom();
    }

    /**
     * 向在院病人记录写数据
     * @param patMasterIndex
     * @param patsInHospital
     */
    private void copytoInHospital(PatMasterIndex patMasterIndex,PatsInHospital patsInHospital){
        patsInHospital.setPatientId(patMasterIndex.getId());
        patsInHospital.setVisitId(1);
        patsInHospital.setWardCode(null);
        patsInHospital.setDeptCode(null);
        patsInHospital.setAdmissionDateTime(new Date());
        patsInHospital.setDiagnosis(patMasterIndex.getDiagnosis());
        patsInHospital.setPatientCondition(null);
        patsInHospital.setGuarantor(null);
        patsInHospital.setGuarantorOrg(null);
        patsInHospital.setGuarantorPhoneNum(null);
        patsInHospital.setDoctorInCharge("");
        patsInHospital.setPrepayments(Double.valueOf(0));
        patsInHospital.setTotalCharges(Double.valueOf(0));
        patsInHospital.setTotalCosts(Double.valueOf(0));
        patsInHospital.setSettledIndicator(0);

    }

    /**
     * 向诊断信息写数据
     * @param patMasterIndex
     * @param emrDiagnosis
     */
    private void copytoDiagnosis(PatMasterIndex patMasterIndex,EmrDiagnosis emrDiagnosis){
        emrDiagnosis.setPatientId(patMasterIndex.getId());
        emrDiagnosis.setParentId("0");
        emrDiagnosis.setVisitId("1");
        emrDiagnosis.setDiagnosisDate(new Date());
        emrDiagnosis.setType("2");//入院诊断
        emrDiagnosis.setItemNo(1);//诊断序号
        emrDiagnosis.setDiagnosisId(patMasterIndex.getDiagnosisNo());//诊断编号
    }

    /**
     * 向转科表写数据
     * @param patMasterIndex
     * @param patsInTransferring
     */
    private void copytoInTrans(PatMasterIndex patMasterIndex,PatsInTransferring patsInTransferring){
        patsInTransferring.setPatientId(patMasterIndex.getId());
        patsInTransferring.setDeptTransferedFrom(patMasterIndex.getDeptTransferedFrom());//转出科室
        patsInTransferring.setDeptTransferedTo(patMasterIndex.getDeptTransferedTo());// 转向科室
        patsInTransferring.setTransferDateTime(patMasterIndex.getTransferDateTime());// 转出日期及时间
    }

    /**
     * 向病人住院写数据
     * @param patMasterIndex
     * @param patVisit
     */
    private void copytoVisit(PatMasterIndex patMasterIndex,PatVisit patVisit){
        patVisit.setPatientId(patMasterIndex.getId());
        patVisit.setVisitId(1);
        patVisit.setIdentity(patMasterIndex.getIdentity());
        patVisit.setArmedServices(null);
        patVisit.setDuty(null);
        patVisit.setUnitInContract(patMasterIndex.getUnitInContract());
        patVisit.setMailingAddress(patMasterIndex.getMailingAddress());
        patVisit.setZipCode(patMasterIndex.getZipCode());
        patVisit.setNextOfKin(patMasterIndex.getNextOfKin());
        patVisit.setRelationship(patMasterIndex.getRelationship());
        patVisit.setNextOfKinPhone(patMasterIndex.getNextOfKinPhone());
        patVisit.setNextOfKinAddr(patMasterIndex.getNextOfKinAddr());
        patVisit.setNextOfKinZipcode(patMasterIndex.getNextOfKinZipCode());
        patVisit.setDeptDischargeFrom(patMasterIndex.getDdtRoomNo());
        patVisit.setWorkingStatus(null);
        patVisit.setInsuranceNo(patMasterIndex.getInsuranceNo());
        patVisit.setServiceAgency(patMasterIndex.getServiceAgency());
        patVisit.setServiceSystemIndicator(null);
        patVisit.setChargeType(patMasterIndex.getChargeType());
        patVisit.setParityNo(patMasterIndex.getParityNo());
        patVisit.setMaritalStatus(patMasterIndex.getMaritalStatus());
        patVisit.setOccupation(patMasterIndex.getOccupation());
        patVisit.setInsuranceType(patMasterIndex.getInsuranceType());
        patVisit.setInsuranceAera(patMasterIndex.getInsuranceAera());
        patVisit.setAdmissionDateTime(patMasterIndex.getAdmissionDateTime());
        patVisit.setConsultingDate(patMasterIndex.getConsultingDate());
        patVisit.setConsultingDoctor(patMasterIndex.getConsultingDoctor());
        patVisit.setDeptAdmissionTo(patMasterIndex.getDeptAdmissionTo());
        patVisit.setPatientClass(patMasterIndex.getPatientClass());
        patVisit.setAdmissionCause(patMasterIndex.getAdmissionCause());
        patVisit.setPatAdmCondition(patMasterIndex.getPatAdmCondition());
        patVisit.setAdmittedBy(patMasterIndex.getAdmittedBy());
//        patVisit.setDdtRoomNo(patMasterIndex.getDdtRoomNo());
        patVisit.setOnsetDate(patMasterIndex.getOnsetDate());
        patVisit.setNhSerialNo(patMasterIndex.getNhSerialNo());
        patVisit.setFromOtherPlaceIndicator(patMasterIndex.getFromOtherPlaceIndicator());
    }
}