/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.ClinicMasterServiceApi;
import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.bo.ClinicMasterBo;
import com.jims.clinic.entity.ClinicMaster;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 病人就诊记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")
public class ClinicMasterServiceImpl  implements ClinicMasterServiceApi {


    @Autowired
    private ClinicMasterBo clinicMasterBo;
    @Override
    public ClinicMaster get(String id) {
        return clinicMasterBo.get(id);
    }

    @Override
    public ClinicMaster getPatInfo(String id) {
        return clinicMasterBo.getPatInfo(id);
    }

    @Override
    public List<ClinicMaster> getClinicMasterList(String visitDept, String orgId) {
        return clinicMasterBo.getClinicMasterList(visitDept,orgId);
    }

    @Override
    public List<ClinicMaster> getClinicMasterDiagnosed(String visitDept, String orgId) {
        return clinicMasterBo.getClinicMasterDiagnosed(visitDept,orgId);
    }

    @Override
    public ClinicMaster findFeeForm(String operator, String date) {
        return clinicMasterBo.findFeeForm(operator,date);
    }

    @Override
    public List<ClinicMaster> getGroupData(String operator, String registeringDate) {
        return clinicMasterBo.getGroupData(operator,registeringDate);
    }

    @Override
    public ClinicMaster getCheckItem(String operator, String registeringDate) {
        return clinicMasterBo.getCheckItem(operator,registeringDate);
    }

    @Override
    public String updatePatInfo(ClinicMaster clinicMaster) {
        return clinicMasterBo.updatePatInfo(clinicMaster);
    }

    @Override
    public ClinicMaster getPatient(String id) {
        return clinicMasterBo.getPatient(id);
    }

    @Override
    public String getPrescNo(String clinicId) {
        return clinicMasterBo.getPrescNo(clinicId);
    }

    @Override
    public String getMaxAcctNo() {
        return clinicMasterBo.getMaxAcctNo();
    }
}