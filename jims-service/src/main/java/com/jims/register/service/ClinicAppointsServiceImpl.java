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
import com.jims.register.api.ClinicAppointsServiceApi;
import com.jims.register.dao.ClinicAppointsDao;
import com.jims.register.dao.ClinicForRegistDao;
import com.jims.register.entity.ClinicAppoints;
import com.jims.register.entity.ClinicForRegist;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.SimpleDateFormat;
import java.util.List;


/**
 * 预约挂号Service
 * @author zhangyao
 * @version 2016-05-20
 */
@Service(version="1.0.0")

public class ClinicAppointsServiceImpl extends CrudImplService<ClinicAppointsDao, ClinicAppoints> implements ClinicAppointsServiceApi {
    @Autowired
    private ClinicAppointsDao clinicAppointsDao;
    @Autowired
    private PatMasterIndexDao patMasterIndexDao;
    @Autowired
    private ClinicForRegistDao clinicForRegistDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    /**
     * 保存预约挂号信息
     * @param patMasterIndex
     * @return
     * @author zhaoning
     */
    @Override
    public String saveAppointsRegis(PatMasterIndex patMasterIndex) throws Exception{
        int num=0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<ClinicForRegist> registList=patMasterIndex.getClinicForRegistList();
        if(registList!=null && registList.size()>0){
             for(int i=0;i<registList.size();i++){
                 ClinicAppoints clinicAppoints = new ClinicAppoints();
                 ClinicForRegist  regist= clinicForRegistDao.get(registList.get(i).getId());//获取号表
                 clinicAppoints.setVisitDateAppted(regist.getClinicDate());//预约就诊日期
                 clinicAppoints.setClinicLabel(regist.getClinicLabel());//预约就诊号别
                 clinicAppoints.setVisitTimeAppted(regist.getTimeDesc());//预约就诊时间
                 clinicAppoints.setApptMadeDate(format.parse(DateUtils.getDate()));//何时预约
                 //clinicAppoints.setModeCode("");//预约模式
                 clinicAppoints.setName(patMasterIndex.getName());
                 clinicAppoints.setSex(patMasterIndex.getSex());
                 String age=DateUtils.getBirthDate(patMasterIndex.getDateOfBirth());
                 if(age!=null && !age.equals("")){
                     clinicAppoints.setAge(Long.parseLong(age));
                 }
                 clinicAppoints.setIdNo(patMasterIndex.getIdNo());//身份证号
                 clinicAppoints.setIdentity(patMasterIndex.getIdentity());//身份
                 clinicAppoints.setChargeType(patMasterIndex.getChargeType());//费别
                 clinicAppoints.setUnitInContract(patMasterIndex.getUnitInContract());//合同单位
                 //保存预约信息
                 save(clinicAppoints);
                 //更新 clinicForRegister 号表信息
                 String clinicDate=format.format(regist.getClinicDate());
                 String clinicLabel=regist.getClinicLabel();
                 String timeDesc=regist.getTimeDesc();
                 clinicForRegistDao.updateRegisterByAppoint(clinicDate,clinicLabel,timeDesc);
             }
            //更新 patMasterIndex
            if (patMasterIndex.getIsNewRecord()){
                patMasterIndex.preInsert();
                num=patMasterIndexDao.insert(patMasterIndex);
            }else{
                patMasterIndex.preUpdate();
                num= patMasterIndexDao.update(patMasterIndex);
            }

        }
        return num+"";
    }

    /**
     * 保存 预约确认信息
     * @param patMasterIndex
     * @return
     */
    @Override
    public String saveAppointReg(PatMasterIndex patMasterIndex) {
        int num=0;
         List<ClinicAppoints> list=patMasterIndex.getClinicAppointses();
         if(list!=null && list.size()>0){
              for(int i=0;i<list.size();i++){
                  ClinicMaster clinicMaster=new ClinicMaster();
                  clinicMaster.setVisitDate(list.get(i).getVisitDateAppted());
                  clinicMaster.setClinicLabel(list.get(i).getClinicLabel());
                  clinicMaster.setVisitTimeDesc(list.get(i).getVisitTimeAppted());
                  clinicMaster.setSerialNo(2);
                  clinicMaster.setName(list.get(i).getName());
                  clinicMaster.setSex(list.get(i).getSex());
                  clinicMaster.setAge(Integer.valueOf(list.get(i).getAge().toString()));
                  clinicMaster.setIdentity(list.get(i).getIdentity());
                  clinicMaster.setChargeType(list.get(i).getChargeType());
                  clinicMaster.setInsuranceType(list.get(i).getInsuranceType());
                  clinicMaster.setInsuranceNo(list.get(i).getInsuranceNo());
                  clinicMaster.setUnitInContract(list.get(i).getUnitInContract());
                  clinicMaster.setClinicType(list.get(i).getClinicType());
                  clinicMaster.setFirstVisitIndicator(0);//初诊标志
                  clinicMaster.setVisitDept(list.get(i).getClinicDept());
                  clinicMaster.setDoctor(list.get(i).getDoctor());
                  clinicMaster.setRegistrationStatus(0);
                  clinicMaster.setRegisteringDate(list.get(i).getVisitDateAppted());
                  clinicMaster.setRegistFee(2.0);//挂号费
                  clinicMaster.setClinicFee(3.0);//诊疗费
                  clinicMaster.setOtherFee(0.0);//其他费
                  clinicMaster.setOperator("登录人");//取当前登录人
                  clinicMaster.setClinicCharge(5.0);//实收费用
                  clinicMaster.setModeCode(list.get(i).getModeCode());
                  clinicMaster.setCardName(list.get(i).getCardName());
                  clinicMaster.setCardNo(list.get(i).getCardNo());
                  clinicMaster.setPayWay("现金");
                  //clinicMaster.setClinicNo();
                  clinicMaster.setPrintOperator("登录人");//取当前登录人
                  //保存 clinic_master
                  if (clinicMaster.getIsNewRecord()){
                      clinicMaster.preInsert();
                      num= clinicMasterDao.insert(clinicMaster);
                  }else{
                      clinicMaster.preUpdate();
                      num=clinicMasterDao.update(clinicMaster);
                  }
                  //删除预约信息
                  delete(list.get(i).getId());
              }
         }
        return num+"";
    }

    /**
     * 根据条件查询 list
     * @param name
     * @param cardNo
     * @param visitDate
     * @return
     */
    @Override
    public List<ClinicAppoints> findListAppoints(String name, String cardNo, String visitDate) {
        return clinicAppointsDao.findListAppoints(name,cardNo,visitDate);
    }

    /**
     * 删除预约信息
     * @param id
     * @return
     */
    @Override
    public String deleteAppoints(String id) {
        //删除预约数据
        String num=  delete(id);
        //更新clinicForRegister当前号数-1
        ClinicAppoints appoints=get(id);
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        String clinicDate=format.format(appoints.getVisitDateAppted());
        String clinicLabel=appoints.getClinicLabel();
        String timeDesc=appoints.getVisitTimeAppted();
        clinicForRegistDao.updateRegByAppointReturn(clinicDate,clinicLabel,timeDesc);
        return num;
    }

    /**
     * 编辑预约信息
     * @param clinicAppoints
     * @return
     */
    @Override
    public String editAppoints(ClinicAppoints clinicAppoints) {
        String num="";
        if(clinicAppoints!=null){
            num= save(clinicAppoints);//编辑预约信息
            String masterId=clinicAppoints.getMasterId();
            PatMasterIndex patMasterIndex= patMasterIndexDao.get(masterId);
            if(patMasterIndex!=null){
                if(clinicAppoints.getName()!=null && !clinicAppoints.getName().equals("")){
                    patMasterIndex.setName(clinicAppoints.getName());
                }
                if(clinicAppoints.getInsuranceNo()!=null && !clinicAppoints.getInsuranceNo().equals("")){
                    patMasterIndex.setInsuranceNo(clinicAppoints.getInsuranceNo());
                }
                patMasterIndexDao.update(patMasterIndex);
            }

        }
        return num;
    }
}