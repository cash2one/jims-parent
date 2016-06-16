package com.jims.register.bo;


import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicTypeSettingServiceApi;
import com.jims.register.dao.ClinicTypeSettingDao;
import com.jims.register.entity.ClinicTypeSetting;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 号类字典Bo
 * @author 张耀
 * @version 2016-06-16
 */

@Service
@Transactional(readOnly = false)
public class ClinicTypeSettingBo extends CrudImplService<ClinicTypeSettingDao, ClinicTypeSetting>{


	
}