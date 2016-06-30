package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.api.PrepaymentRcptServiceApi;
import com.jims.finance.dao.PrepaymentRcptDao;
import com.jims.finance.entity.PrepaymentRcpt;


import java.util.List;

/**
 * 预交金记录ServiceImpl
 * @author CTQ
 * @version 2016-05-25
 */
@Service(version = "1.0.0")

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
    /**
     * @return BaseDto   返回类型
     * @Descripion: (根据参数查询预交金交费记录列表)
     * @author CTQ
     * @date 2016/6/29
     */
    @Override
    public List<BaseDto> findRecordList(PrepaymentRcpt prepaymentRcpt) {
        return dao.findRecordList(prepaymentRcpt);
    }


}