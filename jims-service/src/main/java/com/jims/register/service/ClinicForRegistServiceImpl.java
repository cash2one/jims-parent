/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.PatMasterIndexDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.register.api.ClinicForRegisterSerivceApi;
import com.jims.register.bo.ClinicForRegistBo;
import com.jims.register.dao.ClinicForRegistDao;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicSchedule;
import com.jims.register.util.DateWeekUtil;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public String saveRegister(List<ClinicSchedule> clinicSchedules, String startTime, String endTime) throws Exception {
        return clinicForRegistBo.saveRegister(clinicSchedules,startTime,endTime);
    }

    @Override
    public String delete(String id) {
        return clinicForRegistBo.delete(id);
    }

    @Override
    public List<ClinicForRegist> findListReg(String status) {
        return clinicForRegistBo.findListReg(status);
    }

    @Override
    public String saveClinic(ClinicMaster clinicMaster) throws Exception {
        return clinicForRegistBo.saveClinic(clinicMaster);
    }
}