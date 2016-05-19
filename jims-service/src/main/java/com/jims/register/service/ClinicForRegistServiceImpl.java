/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.api.ClinicForRegisterSerivceApi;
import com.jims.register.dao.ClinicForRegistDao;
import com.jims.register.entity.ClinicForRegist;
import org.springframework.transaction.annotation.Transactional;

/**
 * 生成号表Service
 * @author zhaoning
 * @version 2016-05-18
 */
@Service(version="1.0.0")
@Transactional(readOnly = true)
public class ClinicForRegistServiceImpl extends CrudImplService<ClinicForRegistDao, ClinicForRegist> implements ClinicForRegisterSerivceApi{

}