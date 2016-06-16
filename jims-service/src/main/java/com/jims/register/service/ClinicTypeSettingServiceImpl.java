package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicTypeSettingServiceApi;
import com.jims.register.bo.ClinicTypeSettingBo;
import com.jims.register.dao.ClinicTypeSettingDao;
import com.jims.register.entity.ClinicTypeSetting;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 号类字典Service
 * @author 张耀
 * @version 2016-05-16
 */
@Service(version = "1.0.0")
public class ClinicTypeSettingServiceImpl  implements ClinicTypeSettingServiceApi {

    @Autowired
    private ClinicTypeSettingBo clinicTypeSettingBo;
    @Override
    public List<ClinicTypeSetting> findList(ClinicTypeSetting clinicTypeSetting) {
        return clinicTypeSettingBo.findList(clinicTypeSetting);
    }

    @Override
    public String delete(String id) {
        return clinicTypeSettingBo.delete(id);
    }
}