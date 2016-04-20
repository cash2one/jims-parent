/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;


import com.jims.clinic.api.PatMasterIndexApi;
import com.jims.clinic.entity.PatMasterIndex;
import com.jims.clinic.dao.PatMasterIndexDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 病人主索引Service
 * @author zhaoning
 * @version 2016-04-19
 */
@Service
@Transactional(readOnly = true)
public  class PatMasterIndexService extends CrudImplService<PatMasterIndexDao, PatMasterIndex> implements PatMasterIndexApi {

}