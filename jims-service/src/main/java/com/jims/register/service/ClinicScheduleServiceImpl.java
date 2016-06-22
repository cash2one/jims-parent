/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.api.ClinicScheduleApi;
import com.jims.register.bo.ClinicScheduleBo;
import com.jims.register.entity.ClinicSchedule;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


/**
 * 号别安排Service
 * @author zhaoning
 * @version 2016-05-17
 */
@Service(version = "1.0.0")
public class ClinicScheduleServiceImpl  implements ClinicScheduleApi {
    @Autowired
    private ClinicScheduleBo clinicScheduleBo;

    @Override
    public Page<ClinicSchedule> findPage(Page<ClinicSchedule> page,ClinicSchedule clinicSchedule) {
        return clinicScheduleBo.findPage(page,clinicSchedule);
    }
    @Override
    public List<ClinicSchedule> findList(ClinicSchedule clinicSchedule) {
        return clinicScheduleBo.findList(clinicSchedule);
    }

    @Override
    public List<BaseDto> findListTable(ClinicSchedule clinicSchedule) {
        return clinicScheduleBo.findListTable(clinicSchedule);
    }

    @Override
    public String saveList(List<ClinicSchedule> list, String clinicIndexId) {
        return clinicScheduleBo.saveList(list,clinicIndexId);
    }

    @Override
    public String delete(String id) {
        return clinicScheduleBo.delete(id);
    }
}