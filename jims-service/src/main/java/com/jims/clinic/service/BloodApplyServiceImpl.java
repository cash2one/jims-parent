/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.BloodApplyServiceApi;
import com.jims.clinic.dao.BloodApplyDao;
import com.jims.clinic.entity.BloodApply;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用血申请Service
 * @author qlx
 * @version 2016-04-28
 */
@Service(version ="1.0.0")
@Transactional(readOnly = true)
public class BloodApplyServiceImpl extends CrudImplService<BloodApplyDao,BloodApply> implements BloodApplyServiceApi {

}