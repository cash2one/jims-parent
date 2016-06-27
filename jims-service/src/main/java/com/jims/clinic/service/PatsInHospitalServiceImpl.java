package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.PatsInHospitalServiceApi;
import com.jims.clinic.bo.PatsInHospitalBo;
import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.web.impl.BaseDto;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 病人在院记录
 * @author CTQ
 * @date 2016-06-06 09:36:49
 */
@Service(version = "1.0.0")
public class PatsInHospitalServiceImpl implements PatsInHospitalServiceApi {
    @Autowired
    PatsInHospitalBo patsInHospitalBo;
    @Override
    public String saveHospInfo(ComeDeptVo comeDeptVo) {
        return patsInHospitalBo.saveHospInfo(comeDeptVo);
    }

    @Override
    public String turnOutDept(ComeDeptVo comeDeptVo) {
        return patsInHospitalBo.turnOutDept(comeDeptVo);
    }
}
