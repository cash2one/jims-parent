/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.operation.api.OperatioinGradeSerivceApi;
import com.jims.clinic.dao.OperationGradeDao;
import com.jims.operation.entity.OperationGrade;
import com.jims.common.service.impl.CrudImplService;


/**
 * 手术等级Service
 * @author qlx
 * @version 2016-04-26
 */
@Service(version = "1.0.0")

public class OperationGradeServiceImpl extends CrudImplService<OperationGradeDao, OperationGrade> implements OperatioinGradeSerivceApi {

}