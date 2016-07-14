/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.bo;


import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.dao.PatMasterIndexDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.register.dao.ClinicForRegistDao;
import com.jims.register.dao.ClinicIndexDao;
import com.jims.register.dao.ClinicTypeFeeDao;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicIndex;
import com.jims.register.entity.ClinicSchedule;
import com.jims.register.entity.ClinicTypeFee;
import com.jims.register.util.DateWeekUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 生成号表Bo
 * @author zhangyao
 * @version 2016-06-16
 */

@Service
@Transactional(readOnly = false)
public class ClinicForRegistBo extends CrudImplService<ClinicForRegistDao, ClinicForRegist>{
   @Autowired
    private ClinicForRegistDao clinicForRegistDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private PatMasterIndexDao patMasterIndexDao;
    @Autowired
    private ClinicTypeFeeDao clinicTypeFeeDao;
    @Autowired
    private ClinicIndexDao clinicIndexDao;

    /**
     * 保存号表
     * @param clinicScheduleList
     * @param startTime
     * @param endTime
     * @return
     * @author zhaoning+
     */
    public String saveRegister(List<ClinicSchedule> clinicScheduleList, String  startTime, String  endTime)throws Exception{
        String num="1";
        if(clinicScheduleList!=null && clinicScheduleList.size()>0){
            ClinicSchedule clinicSchedule=new ClinicSchedule();
            for(int i=0;i<clinicScheduleList.size();i++){
                List<Integer> listWeek=new ArrayList<Integer>();
                clinicSchedule=clinicScheduleList.get(i);
                int ayOfWeek=Integer.parseInt(clinicSchedule.getDayOfWeek());
                if (ayOfWeek==7){
                    ayOfWeek=0;
                }
                listWeek.add(ayOfWeek);
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
                    clinicForRegist.setAppointmentNum(0);
                    clinicForRegist.setRegistrationNum(0);
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

    /**
     * 查询当前天的号表
     * @return
     */
    public List<ClinicForRegist> findListReg(ClinicForRegist clinicForRegist) {
        List<ClinicForRegist> list=new ArrayList<ClinicForRegist>();
        list= clinicForRegistDao.findListReg(clinicForRegist);
        return list;
    }

    /**
     * 保存挂号信息
     * @param clinicMaster
     * @return
     */
    public String saveClinic(ClinicMaster clinicMaster) throws Exception{
         int i=1;
       //保存就诊信息
        List<ClinicForRegist> list=clinicMaster.getClinicForRegists();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        PatMasterIndex patMasterIndex =new PatMasterIndex();
        //保存主记录信息
        patMasterIndex.setName(clinicMaster.getName());//姓名
        patMasterIndex.setSex(clinicMaster.getSex());//性别
        patMasterIndex.setDateOfBirth(clinicMaster.getBirthDate());//出生日期
        patMasterIndex.setChargeType(clinicMaster.getChargeType());//费别
        patMasterIndex.setAge(DateUtils.getAge(clinicMaster.getBirthDate())+"");
        patMasterIndex.setIdentity(clinicMaster.getIdentity());//身份
        patMasterIndex.setCreateDate(format.parse(DateUtils.getDate()));//记录时间
        patMasterIndex.setVipIndicator(0);//重要任务标志
        patMasterIndex.setOperator("操作人");//操作人 取当前登录的医生
        if (patMasterIndex.getIsNewRecord()){
            patMasterIndex.preInsert();
            i=patMasterIndexDao.insert(patMasterIndex);
        }else{
            patMasterIndex.preUpdate();
            i=patMasterIndexDao.update(patMasterIndex);
        }
        Integer no= clinicMasterDao.getMaxVisitNO();

        if(no!=null &&!no.equals("")){
            no=no+1;
        }else{
            no=1;
        }
        if(list!=null && list.size()>0){
            for(int k=0;k<list.size();k++){
                ClinicMaster master=new ClinicMaster();
                String registId = list.get(k).getId();
                ClinicForRegist clinicForRegist= get(registId);
                String clinicLabel=clinicForRegist.getClinicLabel();
                String timeDesc=clinicForRegist.getTimeDesc();
                master.setPatientId(patMasterIndex.getId());  //病人ID
                master.setName(clinicMaster.getName()); //姓名
                master.setAge(Integer.valueOf(patMasterIndex.getAge())); //获取年龄
                master.setSex(clinicMaster.getSex());//性别
                master.setChargeType(clinicMaster.getChargeType());//费别
                master.setIdentity(clinicMaster.getIdentity());//身份
                master.setUnitInContract(clinicMaster.getUnitInContract());//合同单位
                master.setInsuranceType(clinicMaster.getInsuranceType());//医保类别
                master.setInsuranceNo(clinicMaster.getInsuranceNo());//医保号
                master.setClinicLabel(clinicLabel);//门诊号名称
                master.setVisitTimeDesc(timeDesc);//门诊时间
                master.setVisitDate(format.parse(DateUtils.getDate()));//就诊日期
                master.setVisitDept(clinicMaster.getVisitDept()); //就诊科室
                master.setVisitNo(no);//就诊序号
                master.setClinicNo(DateUtils.getDate("yyyyMMdd")+master.getVisitNo());//就诊号==就诊日期+就诊序号
                if("0".equals(clinicMaster.getVisitIndicator())){
                    master.setFirstVisitIndicator(0);//初诊标志
                }
                no=no+1;
                master.setRegisteringDate(format.parse(DateUtils.getDate()));//挂号日期

                //获取费用
                ClinicIndex clinicIndex=clinicIndexDao.get(clinicForRegist.getClinicLabel());
                ClinicTypeFee clinicTypeFee=new ClinicTypeFee();
                clinicTypeFee.setTypeId(clinicIndex.getClinicType());
                List<ClinicTypeFee> clinicTypeFeeList= clinicTypeFeeDao.findList(clinicTypeFee);
                Double registFee=0.0;
                Double clinicFee=0.0;
                for (int j = 0; j < clinicTypeFeeList.size(); j++) {
                    ClinicTypeFee c=clinicTypeFeeList.get(j);
                    if(c.getChargeItem().equals("1")){
                        registFee+=c.getPrice();
                    }
                    if(c.getChargeItem().equals("2")){
                        clinicFee+=c.getPrice();
                    }
                }
                master.setRegistFee(registFee);//挂号费
                master.setClinicFee(clinicFee);//诊疗费
                master.setOtherFee(0.0);//其他费
                master.setClinicCharge(registFee + clinicFee);//实收费用
                master.setRegistrationStatus(1); //挂号状态
                master.setModeCode("现金");//挂号模式
                master.setPayWay("现金");//挂号模式
                if (master.getIsNewRecord()){
                    master.preInsert();
                   clinicMasterDao.insert(master);
                }else{
                    master.preUpdate();
                    clinicMasterDao.update(master);
                }
                clinicForRegistDao.updateRegister(registId);
            }

        }

       //更新 号表 信息

        return i+"";
    }


    /**
     * 修改号表安排
     * @param clinicForRegistList
     * @return
     */
    public String updateBatch(List<ClinicForRegist> clinicForRegistList){
        int num=0;
        for (int i = 0; i < clinicForRegistList.size(); i++) {
            String record=save(clinicForRegistList.get(i));
            num=num+Integer.parseInt(record);
        }
        return num+"";
    }
}