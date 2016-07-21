/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.persistence.Page;
import com.jims.register.api.ClinicForRegisterSerivceApi;
import com.jims.register.bo.ClinicForRegistBo;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * 生成号表Service
 * @author zhaoning
 * @version 2016-05-18
 */
@Service(version="1.0.0")
public class ClinicForRegistServiceImpl implements ClinicForRegisterSerivceApi{

    @Autowired
    private ClinicForRegistBo clinicForRegistBo;

    @Override
    public List<ClinicForRegist> findList(ClinicForRegist clinicForRegist) {
        return clinicForRegistBo.findList(clinicForRegist);
    }

    @Override
    public Page<ClinicForRegist> findPage(Page<ClinicForRegist> page,ClinicForRegist clinicForRegist) {
        return clinicForRegistBo.findPage(page,clinicForRegist);
    }
    @Override
    public String saveRegister(List<ClinicSchedule> clinicSchedules, String startTime, String endTime,String orgId) throws Exception {
        return clinicForRegistBo.saveRegister(clinicSchedules,startTime,endTime,orgId);
    }

    @Override
    public String delete(String id) {
        return clinicForRegistBo.delete(id);
    }

    @Override
    public List<ClinicForRegist> findListReg(ClinicForRegist clinicForRegist) {
        return clinicForRegistBo.findListReg(clinicForRegist);
    }

    @Override
    public String saveClinic(ClinicMaster clinicMaster) throws Exception {
        return clinicForRegistBo.saveClinic(clinicMaster);
    }

    @Override
    public String updateBatch(List<ClinicForRegist> clinicForRegistList){
        return clinicForRegistBo.updateBatch(clinicForRegistList);
    }
}