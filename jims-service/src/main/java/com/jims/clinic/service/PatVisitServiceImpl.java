package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.PatVisitServiceApi;
import com.jims.clinic.dao.PatVisitDao;
import com.jims.clinic.entity.PatVisit;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by che on 2016/4/20.
 */
@Service
@Transactional(readOnly = true)
public class PatVisitServiceImpl extends CrudImplService<PatVisitDao,PatVisit> implements PatVisitServiceApi {
   @Autowired
    private   PatVisitDao  patVisitDao;


}
