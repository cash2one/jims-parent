/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.dao.OutpOrderDescDao;
import com.jims.finance.entity.OutpOrderDesc;
import org.springframework.transaction.annotation.Transactional;


/**
 * 开单记录Service
 * @author zhaoning
 * @version 2016-05-26
 */
@Transactional(readOnly = true)
public class OutpOrderDescService extends CrudImplService<OutpOrderDescDao, OutpOrderDesc> {

}