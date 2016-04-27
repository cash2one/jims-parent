/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.PriceItemNameDictApi;
import com.jims.sys.dao.PriceItemNameDictDao;
import com.jims.sys.entity.PriceItemNameDict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 价表项目名称Service
 * @author 罗海昆
 * @version 2016-04-26
 */
@Service
@Transactional(readOnly = true)
public class PriceItemNameDictImpl extends CrudImplService<PriceItemNameDictDao, PriceItemNameDict> implements PriceItemNameDictApi {
	
}