/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.entity.OutpOrders;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * 门诊医嘱记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public class OutpOrdersServiceImpl extends CrudImplService<OutpOrdersDao, OutpOrders> {

	
}