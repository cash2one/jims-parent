/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 门诊医生收费明细Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public class OutpOrdersCostsService extends CrudImplService<OutpOrdersCostsDao, OutpOrdersCosts> {

}