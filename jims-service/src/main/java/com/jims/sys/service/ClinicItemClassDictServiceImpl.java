package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.ClinicItemClassDictServiceApi;
import com.jims.sys.dao.ClinicItemClassDictDao;
import com.jims.sys.entity.ClinicItemClassDict;
import org.springframework.transaction.annotation.Transactional;

/**
 * 诊疗项目分类字典
 * @author xueyx
 * @version 2016-05-04
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ClinicItemClassDictServiceImpl extends CrudImplService<ClinicItemClassDictDao, ClinicItemClassDict> implements ClinicItemClassDictServiceApi {


}