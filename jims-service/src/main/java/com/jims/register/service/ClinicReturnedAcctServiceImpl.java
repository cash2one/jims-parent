package com.jims.register.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.register.api.ClinicReturnedAcctServiceApi;
import com.jims.register.dao.ClinicForRegistDao;
import com.jims.register.dao.ClinicReturnedAcctDao;
import com.jims.register.entity.ClinicReturnedAcct;
import org.springframework.beans.factory.annotation.Autowired;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 退号Service
 * @author zhangyao
 * @version 2016-05-19
 */
@Service(version = "1.0.0")

public class ClinicReturnedAcctServiceImpl extends CrudImplService<ClinicReturnedAcctDao, ClinicReturnedAcct> implements ClinicReturnedAcctServiceApi {
    @Autowired
    private ClinicReturnedAcctDao clinicReturnedAcctDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private ClinicForRegistDao clinicForRegistDao;
    /**
     * 根据 就诊日期和就诊序号 查询 有关退号的基本信息
     * @param visitDate
     * @param visitNo
     * @return
     * @author zhaoning
     */
    @Override
    public ClinicMaster getClinicMaster(String visitDate, Integer visitNo) {
        return clinicMasterDao.getClinicMaster(visitDate,visitNo);
    }

    /**
     * 退号
     * @param id
     * @return
     */
    @Override
    public String returnedAcctInfo(String id) throws Exception{
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        int num=0;
        ClinicMaster clinicMaster=  clinicMasterDao.get(id);
        //更新号表
        String clinicLabel=clinicMaster.getClinicLabel();
        String timeDesc=clinicMaster.getVisitTimeDesc();
        Date clinicDate=clinicMaster.getVisitDate();
        clinicForRegistDao.updateRegisterByReturn(clinicLabel,timeDesc,format.format(clinicDate));

        //更新 病人就诊信息
        Integer visitNo=clinicMaster.getVisitNo();
      //退号操作者获取 当前登录人
        clinicMasterDao.updateClinicMasterByReturn(format.format(clinicDate),visitNo,format.parse(DateUtils.getDate()) ,"退号员");

        //插入退号
        ClinicReturnedAcct clinicReturnedAcct = new ClinicReturnedAcct();
        clinicReturnedAcct.setVisitDate(clinicMaster.getVisitDate());
        clinicReturnedAcct.setVisitNo(clinicMaster.getVisitNo());
        clinicReturnedAcct.setClinicLabel(clinicMaster.getClinicLabel());
        clinicReturnedAcct.setTimeDesc(clinicMaster.getVisitTimeDesc());
        clinicReturnedAcct.setPatientName(clinicMaster.getName());
        clinicReturnedAcct.setRegistFee(clinicMaster.getRegistFee());
        clinicReturnedAcct.setClinicFee(clinicMaster.getClinicFee());
        clinicReturnedAcct.setOtherFee(clinicMaster.getOtherFee());
        clinicReturnedAcct.setClinicCharge(clinicMaster.getClinicCharge());
        clinicReturnedAcct.setOperator(clinicMaster.getOperator());
        clinicReturnedAcct.setReturnedDate(format.parse(DateUtils.getDate()));
        clinicReturnedAcct.setReturnedOperator("退号操作者");//获取当前登录人
        clinicReturnedAcct.setPayWay(clinicMaster.getPayWay());
        if (clinicReturnedAcct.getIsNewRecord()){
            clinicReturnedAcct.preInsert();
             num=dao.insert(clinicReturnedAcct);
        }else{
            clinicReturnedAcct.preUpdate();
            num=dao.update(clinicReturnedAcct);
        }
        return num+"";
    }
}