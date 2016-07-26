/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.bo;

import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.PatMasterIndexDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.NumberUtils;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.register.dao.ClinicReturnedAcctDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 病人就诊记录bo
 * @author zhangyao
 * @version 2016-07-25
 */
@Service
@Transactional(readOnly = false)
public class ClinicMasterBo extends CrudImplService<ClinicMasterDao, ClinicMaster> {
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private ClinicReturnedAcctDao clinicReturnedDao;
    @Autowired
    private PatMasterIndexDao patMasterIndexDao;



    /**
     * 查询 病人列表 （待诊）
     * @return
     * @author zhaoning
     */
    public List<ClinicMaster> getClinicMasterList(String visitDept,String orgId) {
      List<ClinicMaster> list= clinicMasterDao.getClinicBydoctor(visitDept,orgId);
        return list;

    }

    /**
     * 查询病人列表 （已诊）
     * @return
     * @author zhaoning
     */
    public List<ClinicMaster> getClinicMasterDiagnosed(String visitDept,String orgId) {
        return clinicMasterDao.getClinicMasterDiagnosed(visitDept,orgId);
    }

    public ClinicMaster findFeeForm(String operator, String date) {
        ClinicMaster clinic =  clinicMasterDao.getTotalAccount(operator,date);
        Double registerNum = clinicMasterDao.getRegiNum(operator,date);
        Double returnNum = clinicReturnedDao.getRetuNum(operator, date);
        if (clinic!=null){
            clinic.setRegistNum(registerNum);
            clinic.setRefundNum(returnNum);
        }
        return clinic;
    }
    /**
     * 根据操作员和挂号时间查询并按照支付方式分组数据
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public List<ClinicMaster> getGroupData(String operator, String registeringDate) {
        return clinicMasterDao.getGroupData(operator, registeringDate);
    }
    /**
     * 根据参数获取检查费用项目信息
     * @param operator
     * @param registeringDate
     * @author CTQ
     * @return
     */
    public ClinicMaster getCheckItem(String operator, String registeringDate) {
        return clinicMasterDao.getCheckItem(operator, registeringDate);
    }

    public ClinicMaster getPatInfo(String id) {
        return clinicMasterDao.getPatInfo(id);
    }

    /**
     * 病人 信息保存
     * @param clinicMaster
     * @return
     */
    public String updatePatInfo(ClinicMaster clinicMaster) {
        String i="1";
        clinicMasterDao.updateMasterInfo(clinicMaster);
        String patientId=clinicMaster.getPatientId();
        //根据patientId 查询 patMasterIndex
        PatMasterIndex patMasterIndex=patMasterIndexDao.get(patientId);
        patMasterIndex.setName(clinicMaster.getName());
        patMasterIndex.setSex(clinicMaster.getSex());
        patMasterIndex.setDateOfBirth(clinicMaster.getBirthDate());
        patMasterIndex.setIdentity(clinicMaster.getIdentity());
        patMasterIndex.setPhoneNumberHome(clinicMaster.getPhoneNumberHome());
        patMasterIndex.setUnitInContract(clinicMaster.getUnitInContract());
        patMasterIndex.setMailingAddress(clinicMaster.getMailingAddress());
        patMasterIndex.setAddressNow(clinicMaster.getAddressNow());
        patMasterIndex.setIdNo(clinicMaster.getIdNo());
        //更新 patMasterIndex
        patMasterIndexDao.updatePatInfo(patMasterIndex);
        return i;
    }

    /**
     * 用血获取病人信息
     * @param id
     * @return
     */
    public ClinicMaster getPatient(String id){
        ClinicMaster clinicMaster = clinicMasterDao.getPatient(id);
        return clinicMaster;
    }

    public String getPrescNo(String clinicId) {
        return NumberUtils.getClinicPrescription(clinicId);
    }


    /**
     * 拿出最大的收据单号
     * @author pq
     * @return
     */
    public String getMaxAcctNo(){
        String accptNo =  dao.getMaxAcctNo();
        if("".equals(accptNo) || accptNo == null){
            accptNo= ""+28000;

        }else{
            int no= Integer.parseInt(accptNo)+1;
            accptNo = String.valueOf(no);
        }
        return accptNo;
    }
}