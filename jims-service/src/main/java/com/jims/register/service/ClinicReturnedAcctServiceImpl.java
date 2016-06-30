package com.jims.register.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.register.api.ClinicReturnedAcctServiceApi;
import com.jims.register.bo.ClinicReturnedAcctBo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 退号Service
 * @author zhangyao
 * @version 2016-05-19
 */
@Service(version = "1.0.0")
public class ClinicReturnedAcctServiceImpl implements ClinicReturnedAcctServiceApi {

    @Autowired
    private ClinicReturnedAcctBo clinicReturnedAcctBo;
    @Override
    public ClinicMaster getClinicMaster(String visitDate, Integer clinicNo) {
        return clinicReturnedAcctBo.getClinicMaster(visitDate,clinicNo);
    }

    @Override
    public String returnedAcctInfo(String id){
        return clinicReturnedAcctBo.returnedAcctInfo(id);
    }
}