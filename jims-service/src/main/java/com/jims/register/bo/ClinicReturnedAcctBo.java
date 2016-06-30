package com.jims.register.bo;

import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.DateUtils;
import com.jims.register.dao.ClinicForRegistDao;
import com.jims.register.dao.ClinicReturnedAcctDao;
import com.jims.register.entity.ClinicReturnedAcct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 退号Bo
 * @author zhangyao
 * @version 2016-06-30
 */
@Service
@Transactional(readOnly = false)
public class ClinicReturnedAcctBo extends CrudImplService<ClinicReturnedAcctDao, ClinicReturnedAcct> {
    @Autowired
    private ClinicReturnedAcctDao clinicReturnedAcctDao;
    @Autowired
    private ClinicMasterDao clinicMasterDao;
    @Autowired
    private ClinicForRegistDao clinicForRegistDao;
    /**
     * 根据 就诊日期和就诊序号 查询 有关退号的基本信息
     * @param visitDate
     * @param clinicNo
     * @return
     * @author zhaoning
     */
    public ClinicMaster getClinicMaster(String visitDate, Integer clinicNo) {
        return clinicMasterDao.getClinicMaster(visitDate,clinicNo);
    }

    /**
     * 退号
     * @param id
     * @return
     */
    public String returnedAcctInfo(String id){
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
        int num=0;
        ClinicMaster clinicMaster=  clinicMasterDao.get(id);
        if(clinicMaster==null){
            return "已经退号，请勿重新退号";
        }
        //更新号表
        String clinicLabel=clinicMaster.getClinicLabel();
        String timeDesc=clinicMaster.getVisitTimeDesc();
        Date clinicDate=clinicMaster.getVisitDate();
        clinicForRegistDao.updateRegisterByReturn(clinicLabel,timeDesc,format.format(clinicDate));

        //更新 病人就诊信息
        Integer visitNo=clinicMaster.getVisitNo();
      //退号操作者获取 当前登录人
        try {
            clinicMasterDao.updateClinicMasterByReturn(format.format(clinicDate),visitNo,format.parse(DateUtils.getDate()) ,"退号员");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        clinicMasterDao.delete(id);
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
        try {
            clinicReturnedAcct.setReturnedDate(format.parse(DateUtils.getDate()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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