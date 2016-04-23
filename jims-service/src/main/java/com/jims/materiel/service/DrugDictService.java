/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.materiel.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.materiel.api.DrugDictServiceApi;
import com.jims.materiel.dao.DrugDictDao;
import com.jims.materiel.entity.DrugDict;
import org.springframework.transaction.annotation.Transactional;


/**
 * 药品字典Service
 * @author zhaoning
 * @version 2016-04-22
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugDictService extends CrudImplService<DrugDictDao, DrugDict> implements DrugDictServiceApi {

	
}