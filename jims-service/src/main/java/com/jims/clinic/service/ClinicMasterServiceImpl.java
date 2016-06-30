/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.ClinicMasterServiceApi;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.PatMasterIndexDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.register.dao.ClinicReturnedAcctDao;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;


import java.util.Date;
import java.util.List;

/**
 * 病人就诊记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")

public class ClinicMasterServiceImpl extends CrudImplService<ClinicMasterDao, ClinicMaster> implements ClinicMasterServiceApi {
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private ClinicReturnedAcctDao clinicReturnedDao;
    @Autowired
    private PatMasterIndexDao patMasterIndexDao;



    /**
     * 查询 病人列表 （待诊）
     * @param doctorID
     * @return
     * @author zhaoning
     */
    @Override
    public List<ClinicMaster> getClinicMasterList(String doctorID,String visitDept) {
      List<ClinicMaster> list= clinicMasterDao.getClinicBydoctor(doctorID,visitDept);
        return list;

    }

    /**
     * 查询病人列表 （已诊）
     * @param doctorID
     * @return
     * @author zhaoning
     */
    @Override
    public List<ClinicMaster> getClinicMasterDiagnosed(String doctorID,String visitDept) {
        return clinicMasterDao.getClinicMasterDiagnosed(doctorID,visitDept);
    }

    @Override
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
    @Override
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
    @Override
    public ClinicMaster getCheckItem(String operator, String registeringDate) {
        return clinicMasterDao.getCheckItem(operator,registeringDate);
    }

    @Override
    public ClinicMaster getPatInfo(String id) {
        return clinicMasterDao.getPatInfo(id);
    }

    /**
     * 病人 信息保存
     * @param clinicMaster
     * @return
     */
    @Override
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
}