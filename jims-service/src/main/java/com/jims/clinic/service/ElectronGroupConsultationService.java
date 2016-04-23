/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.ElectronGroupConsultationDao;
import com.jims.clinic.entity.ElectronGroupConsultation;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 会诊记录Service
 * @author zhaoning
 * @version 2016-04-23
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ElectronGroupConsultationService extends CrudImplService<ElectronGroupConsultationDao, ElectronGroupConsultation> {

	}