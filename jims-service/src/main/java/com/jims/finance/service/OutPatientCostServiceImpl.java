package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.api.OutPatientCostServiceApi;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 划价收费
 *
 * @author zhangyao
 * @date2016/5/25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OutPatientCostServiceImpl implements OutPatientCostServiceApi {

    public List<BaseDto> list(String orgId, String clinicNo){
        List<BaseDto> list = null;
        return list;
    };

}
