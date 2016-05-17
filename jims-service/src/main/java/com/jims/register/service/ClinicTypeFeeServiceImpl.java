/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicTypeFeeServiceApi;
import com.jims.register.dao.ClinicTypeFeeDao;
import com.jims.register.entity.ClinicTypeFee;
import org.springframework.transaction.annotation.Transactional;


/**
 * 号类字典Service
 * @author zhaoning
 * @version 2016-05-16
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ClinicTypeFeeServiceImpl extends CrudImplService<ClinicTypeFeeDao, ClinicTypeFee> implements ClinicTypeFeeServiceApi {

}