/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OutpTreatRecServiceApi;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 检查治疗医嘱明细记录Service
 * @author zhaoning
 * @version 2016-04-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OutpTreatRecService extends CrudImplService<OutpTreatRecDao, OutpTreatRec> implements OutpTreatRecServiceApi {

}