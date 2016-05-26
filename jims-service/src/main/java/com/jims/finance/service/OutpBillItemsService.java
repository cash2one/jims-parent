/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.dao.OutpBillItemsDao;
import com.jims.finance.entity.OutpBillItems;
import org.springframework.transaction.annotation.Transactional;

/**
 * 门诊病人诊疗收费项目明细Service
 * @author zhaoning
 * @version 2016-05-26
 */
@Transactional(readOnly = true)
public class OutpBillItemsService extends CrudImplService<OutpBillItemsDao, OutpBillItems> {

	
}