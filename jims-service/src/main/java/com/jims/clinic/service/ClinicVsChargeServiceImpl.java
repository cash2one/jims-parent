/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.jims.clinic.api.ClinicVsChargeServiceApi;
import com.jims.clinic.dao.ClinicVsChargeDao;
import com.jims.clinic.entity.ClinicVsCharge;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * 病人就诊记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service

public class ClinicVsChargeServiceImpl extends CrudImplService<ClinicVsChargeDao, ClinicVsCharge> implements ClinicVsChargeServiceApi {

}