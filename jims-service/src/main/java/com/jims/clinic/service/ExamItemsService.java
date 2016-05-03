/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.ExamItemsServiceApi;
import com.jims.clinic.dao.ExamItemsDao;
import com.jims.clinic.entity.ExamItems;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 检查项目记录Service
 * @author zhaoning
 * @version 2016-04-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class ExamItemsService extends CrudImplService<ExamItemsDao, ExamItems> implements ExamItemsServiceApi {

}