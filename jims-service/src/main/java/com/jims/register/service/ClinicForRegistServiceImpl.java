/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicForRegisterSerivceApi;
import com.jims.register.dao.ClinicForRegistDao;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicSchedule;
import com.jims.register.util.DateWeekUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成号表Service
 * @author zhaoning
 * @version 2016-05-18
 */
@Service(version="1.0.0")
@Transactional(readOnly = true)
public class ClinicForRegistServiceImpl extends CrudImplService<ClinicForRegistDao, ClinicForRegist> implements ClinicForRegisterSerivceApi{
   @Autowired
    private ClinicForRegistDao clinicForRegistDao;
    /**
     * 保存号表
     * @param clinicScheduleList
     * @param startTime
     * @param endTime
     * @return
     * @author zhaoning+
     */
    @Override
    public String saveRegister(List<ClinicSchedule> clinicScheduleList, String  startTime, String  endTime)throws Exception{
        String num="1";
        if(clinicScheduleList!=null && clinicScheduleList.size()>0){
            ClinicSchedule clinicSchedule=new ClinicSchedule();
            for(int i=0;i<clinicScheduleList.size();i++){
                List<Integer> listWeek=new ArrayList<Integer>();
                clinicSchedule=clinicScheduleList.get(i);
                listWeek.add(Integer.parseInt(clinicSchedule.getDayOfWeek()));
                List<String> daysNeedBookList = DateWeekUtil.getDates(startTime, endTime, listWeek);//根据门诊日期时间段 以及出诊时间查询出门诊日期
                String clinicDate="";
                for(int j=0;j<daysNeedBookList.size();j++){
                    ClinicForRegist clinicForRegist=new ClinicForRegist();
                    clinicDate=daysNeedBookList.get(j);
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    clinicForRegist.setClinicDate(format.parse(clinicDate));//门诊日期
                    clinicForRegist.setClinicLabel(clinicSchedule.getClinicLabel());//门诊号名称
                    clinicForRegist.setTimeDesc(clinicSchedule.getTimeDesc());//出诊时间==门诊时间描述
                    clinicForRegist.setRegistrationLimits(clinicSchedule.getRegistrationLimits());//限号数
                    clinicForRegist.setAppointmentLimits(clinicSchedule.getAppointmentLimits());//限约号数
                    Integer currentNo=clinicForRegistDao.currentNoMax(clinicDate,clinicSchedule.getClinicLabel(),clinicSchedule.getTimeDesc());
                    if(currentNo!=null){
                        clinicForRegist.setCurrentNo(currentNo+1);  //当前号(根据，门诊日期，门诊号名称，出诊时间 查询出最大的当前号数+1)
                    }else{
                        clinicForRegist.setCurrentNo(1);
                    }
                    //保存前先判断 数据是否存在 （门诊日期，门诊号名称，出诊时间）
                   List<ClinicForRegist> regist= clinicForRegistDao.getClinicForReg(clinicDate, clinicSchedule.getClinicLabel(), clinicSchedule.getTimeDesc());
                    if(regist==null || regist.size()<=0){//
                        num=save(clinicForRegist);//
                    }
                }
            }
        }
        return num;
    }
}