package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.PriceListApi;
import com.jims.sys.dao.PriceListDao;
import com.jims.sys.entity.PriceList;
import org.springframework.transaction.annotation.Transactional;



/**
 * 价格表Service
 * @author 罗海昆
 * @version 2016-04-26
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class PriceListImpl extends CrudImplService<PriceListDao, PriceList> implements PriceListApi {
	
}