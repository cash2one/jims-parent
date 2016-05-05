/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.LabTestMasterServiceApi;
import com.jims.clinic.dao.LabTestMasterDao;
import com.jims.clinic.entity.LabTestMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检验主记录Service
 * @author xueyx
 * @version 2016-04-28
 */
@Service
@Transactional(readOnly = true)
public class LabTestMasterServiceImpl  extends CrudImplService<LabTestMasterDao, LabTestMaster> implements LabTestMasterServiceApi {


}