/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.register.api.ClinicAppointsServiceApi;
import com.jims.register.bo.ClinicAppointsBo;
import com.jims.register.entity.ClinicAppoints;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


/**
 * 预约挂号Service
 * @author zhangyao
 * @version 2016-05-20
 */
@Service(version="1.0.0")
public class ClinicAppointsServiceImpl  implements ClinicAppointsServiceApi {
    @Autowired
    private ClinicAppointsBo clinicAppointsBo;

    @Override
    public String saveAppointsRegis(PatMasterIndex patMasterIndex) throws Exception {
        return clinicAppointsBo.saveAppointsRegis(patMasterIndex);
    }

    @Override
    public List<ClinicAppoints> findList(ClinicAppoints clinicAppoints) {
        return clinicAppointsBo.findList(clinicAppoints);
    }

    @Override
    public List<ClinicAppoints> findListAppoints(String name, String cardNo, String visitDate) {
        return clinicAppointsBo.findListAppoints(name,cardNo,visitDate);
    }

    @Override
    public ClinicAppoints get(String id) {
        return clinicAppointsBo.get(id);
    }

    @Override
    public String saveAppointReg(PatMasterIndex patMasterIndex) {
        return clinicAppointsBo.saveAppointReg(patMasterIndex);
    }

    @Override
    public String deleteAppoints(String id) {
        return clinicAppointsBo.deleteAppoints(id);
    }

    @Override
    public String editAppoints(ClinicAppoints clinicAppoints) {
        return clinicAppointsBo.editAppoints(clinicAppoints);
    }
}