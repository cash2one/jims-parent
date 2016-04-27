/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ExamAppointsServiceApi;
import com.jims.clinic.dao.ExamAppointsDao;
import com.jims.clinic.entity.ExamAppoints;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;
/**
 * 检查预约记录Service
 * @author zhaoning
 * @version 2016-04-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ExamAppointsService extends CrudImplService<ExamAppointsDao, ExamAppoints> implements ExamAppointsServiceApi {

}