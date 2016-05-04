/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.LabTestItemsServiceApi;
import com.jims.clinic.dao.LabTestItemsDao;
import com.jims.clinic.entity.LabTestItems;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检验项目Service
 * @author xueyx
 * @version 2016-04-28
 */
@Service
@Transactional(readOnly = true)
public class LabTestItemsServiceImpl  extends CrudImplService<LabTestItemsDao, LabTestItems> implements LabTestItemsServiceApi {


	
}