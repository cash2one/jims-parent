/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.lab.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.lab.api.LabTestItemsServiceApi;
import com.jims.lab.dao.LabTestItemsDao;
import com.jims.lab.entity.LabTestItems;
import com.jims.common.service.impl.CrudImplService;


/**
 * 检验项目Service
 * @author xueyx
 * @version 2016-04-28
 */
@Service

public class LabTestItemsServiceImpl  extends CrudImplService<LabTestItemsDao, LabTestItems> implements LabTestItemsServiceApi {



}