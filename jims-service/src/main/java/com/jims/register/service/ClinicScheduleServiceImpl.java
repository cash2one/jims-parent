/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.api.ClinicScheduleApi;
import com.jims.register.dao.ClinicScheduleDao;
import com.jims.register.entity.ClinicSchedule;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 号别安排Service
 * @author zhaoning
 * @version 2016-05-17
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ClinicScheduleServiceImpl extends CrudImplService<ClinicScheduleDao, ClinicSchedule> implements ClinicScheduleApi {
    @Override
    public List<BaseDto> findListTable(ClinicSchedule clinicSchedule) {
        return dao.findListTable(clinicSchedule);
    }

    /**
     * 保存号别安排
     * @param list
     * @return
     */
    @Override
    public String saveList(List<ClinicSchedule> list,String clinicIndexId) {
        String num="";
        if(list!=null && list.size()>0){
            for(int i=0;i<list.size();i++){
                ClinicSchedule clinicSchedule=list.get(i);
                clinicSchedule.setClinicLabel(clinicIndexId);
                num= save(clinicSchedule);
            }
            dao.batchDel(list,clinicIndexId);
        }
        return num;
    }
}