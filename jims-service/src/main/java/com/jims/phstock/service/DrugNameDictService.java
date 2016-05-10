/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugNameDictServiceApi;
import com.jims.phstock.dao.DrugNameDictDao;
import com.jims.phstock.entity.DrugNameDict;
import org.springframework.transaction.annotation.Transactional;


/**
 * 药品名称Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugNameDictService extends CrudImplService<DrugNameDictDao, DrugNameDict> implements DrugNameDictServiceApi {

}