package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.api.PrepaymentRcptServiceApi;
import com.jims.finance.dao.PrepaymentRcptDao;
import com.jims.finance.entity.PrepaymentRcpt;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 预交金记录ServiceImpl
 * @author CTQ
 * @version 2016-05-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class PrepaymentRcptServiceApiImpl extends CrudImplService<PrepaymentRcptDao,PrepaymentRcpt> implements PrepaymentRcptServiceApi{

    /**
     * 根据参数查询预交金列表
     * @param patientId
     * @author CTQ
     * @date 2016-05-30 14:49:19
     * @return
     */
    @Override
    public List<PrepaymentRcpt> findByPatientId(String patientId) {
        return dao.findByPatientId(patientId);
    }


}