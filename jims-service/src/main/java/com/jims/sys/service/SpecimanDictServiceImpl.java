package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.SpecimanDictServiceApi;
import com.jims.sys.dao.SpecimanDictDao;
import com.jims.sys.entity.SpecimanDict;
import org.springframework.transaction.annotation.Transactional;

/**
 * 标本字典
 * @author xueyx
 * @version 2016-05-04
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class SpecimanDictServiceImpl extends CrudImplService<SpecimanDictDao, SpecimanDict> implements SpecimanDictServiceApi {


}
