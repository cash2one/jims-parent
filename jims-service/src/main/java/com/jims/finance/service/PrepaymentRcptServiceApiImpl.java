package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.api.PrepaymentRcptServiceApi;
import com.jims.finance.dao.PrepaymentRcptDao;
import com.jims.finance.entity.PrepaymentRcpt;
import org.springframework.transaction.annotation.Transactional;

/**
 * 预交金记录ServiceImpl
 * @author CTQ
 * @version 2016-05-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class PrepaymentRcptServiceApiImpl extends CrudImplService<PrepaymentRcptDao,PrepaymentRcpt> implements PrepaymentRcptServiceApi{

    
    @Override
    public PrepaymentRcpt findByPatientId(String patientId) {
        return dao.findByPatientId(patientId);
    }
}