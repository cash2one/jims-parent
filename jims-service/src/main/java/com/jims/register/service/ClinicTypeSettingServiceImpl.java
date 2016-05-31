package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicTypeSettingServiceApi;
import com.jims.register.dao.ClinicTypeSettingDao;
import com.jims.register.entity.ClinicTypeSetting;


import java.util.List;

/**
 * 号类字典Service
 * @author 张耀
 * @version 2016-05-16
 */
@Service(version = "1.0.0")

public class ClinicTypeSettingServiceImpl extends CrudImplService<ClinicTypeSettingDao, ClinicTypeSetting> implements ClinicTypeSettingServiceApi {


	
}