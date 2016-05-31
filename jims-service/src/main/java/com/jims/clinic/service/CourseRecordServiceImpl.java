/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.CourseRecordApi;
import com.jims.clinic.dao.CourseRecordDao;
import com.jims.clinic.entity.CourseRecord;
import com.jims.common.service.impl.CrudImplService;




/**
 * 病程主记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")

public class CourseRecordServiceImpl extends CrudImplService<CourseRecordDao, CourseRecord> implements CourseRecordApi {

}