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
import com.jims.register.dao.ClinicAppointsDao;
import com.jims.register.dao.ClinicForRegistDao;
import com.jims.register.dao.ClinicIndexDao;
import com.jims.register.dao.ClinicTypeFeeDao;
import com.jims.register.entity.ClinicAppoints;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicIndex;
import com.jims.register.entity.ClinicTypeFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 预约挂号Service
 * @author zhangyao
 * @version 2016-06-28
 */

@Service
@Transactional(readOnly = false)
public class ClinicAppointsBo extends CrudImplService<ClinicAppointsDao, ClinicAppoints>{
    @Autowired
    private ClinicAppointsDao clinicAppointsDao;
    @Autowired
    private PatMasterIndexDao patMasterIndexDao;
    @Autowired
    private ClinicForRegistDao clinicForRegistDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private ClinicIndexDao clinicIndexDao;
    @Autowired
    private ClinicTypeFeeDao clinicTypeFeeDao;
    /**
     * 保存预约挂号信息
     * @param patMasterIndex
     * @return
     * @author zhaoning
     */
    public String saveAppointsRegis(PatMasterIndex patMasterIndex){
        int num=0;
        long age=DateUtils.getAge(patMasterIndex.getDateOfBirth());
        patMasterIndex.setAge(age+"");
        //更新 patMasterIndex
        if (patMasterIndex.getIsNewRecord()){
            patMasterIndex.preInsert();
            num=patMasterIndexDao.insert(patMasterIndex);
        }else{
            patMasterIndex.preUpdate();
            num= patMasterIndexDao.update(patMasterIndex);
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<ClinicForRegist> registList=patMasterIndex.getClinicForRegistList();
        if(registList!=null && registList.size()>0){
             for(int i=0;i<registList.size();i++){
                 String registId = registList.get(i).getId();
                 ClinicAppoints clinicAppoints = new ClinicAppoints();
                 ClinicForRegist  regist= clinicForRegistDao.get(registId);//获取号表
                 clinicAppoints.setVisitDateAppted(regist.getClinicDate());//预约就诊日期
                 clinicAppoints.setClinicLabel(regist.getClinicLabel());//预约就诊号别
                 clinicAppoints.setVisitTimeAppted(regist.getTimeDesc());//预约就诊时间
                 clinicAppoints.setVisitDept(patMasterIndex.getVisitDept());//就诊科室
                 clinicAppoints.setVisitIndicator(patMasterIndex.getVisitIndicator());
                 try {
                     clinicAppoints.setApptMadeDate(format.parse(DateUtils.getDate()));//何时预约
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
                 //clinicAppoints.setModeCode("");//预约模式
                 clinicAppoints.setPatientId(patMasterIndex.getId());
                 clinicAppoints.setName(patMasterIndex.getName());
                 clinicAppoints.setSex(patMasterIndex.getSex());
                 clinicAppoints.setAge(age);
                 clinicAppoints.setIdNo(patMasterIndex.getIdNo());//身份证号
                 clinicAppoints.setIdentity(patMasterIndex.getIdentity());//身份
                 clinicAppoints.setChargeType(patMasterIndex.getChargeType());//费别
                 clinicAppoints.setUnitInContract(patMasterIndex.getUnitInContract());//合同单位
                 //保存预约信息
                 save(clinicAppoints);
                 //更新 clinicForRegister 号表信息
                 clinicForRegistDao.updateAppoints(registId);
             }
        }
        return num+"";
    }

    /**
     * 保存 预约确认信息
     * @param id
     * @return
     */
    public String saveAppointReg(String id,String orgId) {
        ClinicAppoints clinicAppoints=get(id);
        PatMasterIndex patMasterIndex=patMasterIndexDao.get(clinicAppoints.getPatientId());
        int num=0;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
         List<ClinicAppoints> list=patMasterIndex.getClinicAppointses();
        ClinicMaster clinicMaster=new ClinicMaster();
        clinicMaster.setOrgId(orgId);
        clinicMaster.setPatientId(patMasterIndex.getId());
        clinicMaster.setVisitDate(clinicAppoints.getVisitDateAppted());
        clinicMaster.setClinicLabel(clinicAppoints.getClinicLabel());
        clinicMaster.setVisitDept(clinicAppoints.getVisitDept());  //就诊科室
        clinicMaster.setVisitTimeDesc(clinicAppoints.getVisitTimeAppted());
        clinicMaster.setName(clinicAppoints.getName());
        clinicMaster.setSex(clinicAppoints.getSex());
        clinicMaster.setAge(Integer.valueOf(patMasterIndex.getAge()));
        clinicMaster.setIdentity(clinicAppoints.getIdentity());  //身份
        clinicMaster.setChargeType(clinicAppoints.getChargeType());
        clinicMaster.setInsuranceType(clinicAppoints.getInsuranceType());
        clinicMaster.setInsuranceNo(clinicAppoints.getInsuranceNo());
        clinicMaster.setUnitInContract(clinicAppoints.getUnitInContract());
        clinicMaster.setClinicType(clinicAppoints.getClinicType());
        clinicMaster.setVisitIndicator(clinicAppoints.getVisitIndicator()); //诊别
        clinicMaster.setIdNo(clinicAppoints.getIdNo()); //身份证号
        Integer no= clinicMasterDao.getMaxVisitNO();
        if(no!=null &&!no.equals("")){
            clinicMaster.setVisitNo(no+1);
        }else{
            clinicMaster.setVisitNo(1);//就诊序号
        }
        clinicMaster.setClinicNo(DateUtils.getDate("yyyyMMdd")+clinicMaster.getVisitNo());//就诊号==就诊日期+就诊序号
        if("0".equals(clinicMaster.getVisitIndicator())){
            clinicMaster.setFirstVisitIndicator(0);//初诊标志
        }
        try {
            clinicMaster.setRegisteringDate(format.parse(DateUtils.getDate()));//挂号日期
        } catch (ParseException e) {
            e.printStackTrace();
        }
        clinicMaster.setVisitDept(clinicAppoints.getClinicDept());//就诊科室
        clinicMaster.setRegistrationStatus(1); //挂号状态
        //获取费用
        ClinicIndex clinicIndex=clinicIndexDao.get(clinicAppoints.getClinicLabel());
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
        clinicMaster.setRegistFee(registFee);//挂号费
        clinicMaster.setClinicFee(clinicFee);//诊疗费
        clinicMaster.setOtherFee(0.0);//其他费
        clinicMaster.setClinicCharge(registFee + clinicFee);//实收费用
        clinicMaster.setRegistrationStatus(1); //挂号状态
        clinicMaster.setModeCode("现金");//挂号模式
        clinicMaster.setPayWay("现金");//挂号模式


        //保存 clinic_master
        if (clinicMaster.getIsNewRecord()){
            clinicMaster.preInsert();
            num= clinicMasterDao.insert(clinicMaster);
        }else{
            clinicMaster.preUpdate();
            num=clinicMasterDao.update(clinicMaster);
        }
        //删除预约信息
        delete(id);
        //更新挂号表
        ClinicForRegist clinicForRegist=new ClinicForRegist();
        try {
            clinicForRegist.setClinicDate(format.parse(DateUtils.formatDateTime(clinicAppoints.getVisitDateAppted())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        clinicForRegist.setClinicLabel(clinicAppoints.getClinicLabel());
        clinicForRegist.setTimeDesc(clinicAppoints.getVisitTimeAppted());
        clinicForRegist=clinicForRegistDao.getClinicForRegist(clinicForRegist);
        clinicForRegistDao.updateAppointsConfirm(clinicForRegist.getId());
        return num+"";
    }

    /**
     * 根据条件查询 list
     * @param name
     * @param cardNo
     * @param visitDate
     * @return
     */
    public List<ClinicAppoints> findListAppoints(String name, String cardNo, String visitDate) {
        return clinicAppointsDao.findListAppoints(name,cardNo,visitDate);
    }

    /**
     * 删除预约信息
     * @param id
     * @return
     */
    public String deleteAppoints(String id) {
        //删除预约数据
        //更新clinicForRegister当前号数-1
        ClinicAppoints appoints=get(id);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ClinicForRegist clinicForRegist=new ClinicForRegist();
        try {
            clinicForRegist.setClinicDate(format.parse(DateUtils.formatDateTime(appoints.getVisitDateAppted())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        clinicForRegist.setClinicLabel(appoints.getClinicLabel());
        clinicForRegist.setTimeDesc(appoints.getVisitTimeAppted());
        clinicForRegist=clinicForRegistDao.getClinicForRegist(clinicForRegist);
        //获取当前号表安排
        clinicForRegistDao.updateRegByAppointReturn(clinicForRegist.getId());
        String num=  delete(id);
        return num;
    }

    /**
     * 编辑预约信息
     * @param clinicAppoints
     * @return
     */
    public String editAppoints(ClinicAppoints clinicAppoints) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String num="";
        if(clinicAppoints!=null){
            ClinicAppoints clinicAppointsOld=get(clinicAppoints.getId());
            ClinicForRegist clinicForRegist=new ClinicForRegist();
            //判断预约就诊时间是否与原本的时间相同  更新号表
            if(!org.apache.commons.lang3.time.DateUtils.isSameDay(clinicAppointsOld.getVisitDateAppted(),clinicAppoints.getVisitDateAppted())){
                clinicForRegist.setClinicDate(clinicAppointsOld.getVisitDateAppted());
                clinicForRegist.setClinicLabel(clinicAppointsOld.getClinicLabel());
                clinicForRegist.setTimeDesc(clinicAppointsOld.getVisitTimeAppted());
                //获取当前号表安排
                clinicForRegist=clinicForRegistDao.getClinicForRegist(clinicForRegist);
                clinicForRegistDao.updateRegByAppointReturn(clinicForRegist.getId());
                try {
                    clinicForRegist.setClinicDate(format.parse(DateUtils.formatDateTime(clinicAppoints.getVisitDateAppted())));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                //获取修改后号表安排
                clinicForRegist=clinicForRegistDao.getClinicForRegist(clinicForRegist);
                if(clinicForRegist==null){
                    return "此时间没有坐诊信息";
                }
                clinicForRegistDao.updateAppointReturn(clinicForRegist.getId());
                clinicAppointsOld.setClinicLabel(clinicForRegist.getClinicLabel());
                clinicAppointsOld.setVisitDateAppted(clinicAppoints.getVisitDateAppted());
            }
            //设置修改预约信息的值
            clinicAppointsOld.setName(clinicAppoints.getName());
            clinicAppointsOld.setSex(clinicAppoints.getSex());
            clinicAppointsOld.setAge(Long.valueOf(DateUtils.getAge(clinicAppoints.getDateOfBirth())));
            clinicAppointsOld.setVisitIndicator(clinicAppoints.getVisitIndicator());
            clinicAppointsOld.setIdentity(clinicAppoints.getIdentity());
            clinicAppointsOld.setChargeType(clinicAppoints.getChargeType());
            clinicAppointsOld.setVisitDept(clinicAppoints.getVisitDept());
            clinicAppointsOld.setUnitInContract(clinicAppoints.getUnitInContract());
            clinicAppointsOld.setIdNo(clinicAppoints.getIdNo());

            //保存预约信息
            num= save(clinicAppointsOld);//编辑预约信息
            String masterId=clinicAppointsOld.getMasterId();
            PatMasterIndex patMasterIndex= patMasterIndexDao.get(clinicAppointsOld.getPatientId());
            patMasterIndex.setDateOfBirth(clinicAppoints.getDateOfBirth());
            patMasterIndex.setAge(clinicAppointsOld.getAge()+"");
            if(patMasterIndex!=null){
                if(clinicAppointsOld.getName()!=null && !clinicAppointsOld.getName().equals("")){
                    patMasterIndex.setName(clinicAppointsOld.getName());
                }
                if(clinicAppointsOld.getInsuranceNo()!=null && !clinicAppointsOld.getInsuranceNo().equals("")){
                    patMasterIndex.setInsuranceNo(clinicAppointsOld.getInsuranceNo());
                }
                patMasterIndexDao.update(patMasterIndex);
            }

        }
        return num;
    }
}