package com.jims.finance.outpAccounts.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.finance.outpAccounts.api.RegistAcctMasterServiceApi;
import com.jims.finance.outpAccounts.bo.RegistAcctMasterBo;
import com.jims.finance.outpAccounts.entity.RegistAcctMaster;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 挂号结帐主记录ServiceImpl
 * @author CTQ
 * @version 2016-06-01
 */
@Service(version = "1.0.0")
public class RegistAcctMasterServiceImpl implements RegistAcctMasterServiceApi {

    @Autowired
    RegistAcctMasterBo registAcctMasterBo;
    @Override
    public String saveMaster(RegistAcctMaster registAcctMaster) {
        return registAcctMasterBo.saveMaster(registAcctMaster);
    }
}