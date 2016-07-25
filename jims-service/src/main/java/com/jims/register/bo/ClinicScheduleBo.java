/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.dao.ClinicScheduleDao;
import com.jims.register.entity.ClinicSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 号别安排Bo
 * @author zhangyao
 * @version 2016-06-16
 */

@Service
@Transactional(readOnly = false)
public class ClinicScheduleBo extends CrudImplService<ClinicScheduleDao, ClinicSchedule> {
    @Autowired
    private ClinicScheduleDao clinicScheduleDao;

    /**
     * 根据 号别ID 查询 安排信息
     * @param clinicIndexId
     * @return
     * @author zhaoning
     */
    public List<ClinicSchedule> getClinicSchedules(String clinicIndexId){
       return  clinicScheduleDao.getClinicSchedules(clinicIndexId);
    }
    public List<BaseDto> findListTable(ClinicSchedule clinicSchedule) {
        return dao.findListTable(clinicSchedule);
    }

    /**
     * 保存号别安排
     * @param list
     * @return
     */
    public String saveList(List<ClinicSchedule> list,String clinicIndexId,String orgId) {
        String num="";
        if(list!=null && list.size()>0){
            for(int i=0;i<list.size();i++){
                ClinicSchedule clinicSchedule=list.get(i);
                clinicSchedule.setClinicLabel(clinicIndexId);
                clinicSchedule.setOrgId(orgId);
                num= save(clinicSchedule);
            }
            dao.batchDel(list,clinicIndexId);
        }
        return num;
    }
}