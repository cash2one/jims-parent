package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ClinicItemNameDictServiceApi;
import com.jims.clinic.dao.ClinicItemNameDictDao;
import com.jims.clinic.entity.ClinicItemNameDict;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 诊疗项目名称表
 * @author xueyx
 * @version 2016-05-04
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ClinicItemNameDictServiceImpl extends CrudImplService<ClinicItemNameDictDao, ClinicItemNameDict> implements ClinicItemNameDictServiceApi {

}
