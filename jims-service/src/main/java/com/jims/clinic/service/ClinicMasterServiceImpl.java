/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.ClinicMasterServiceApi;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 病人就诊记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public class ClinicMasterServiceImpl extends CrudImplService<ClinicMasterDao, ClinicMaster> implements ClinicMasterServiceApi {
    @Override
    public List<ClinicMaster> getClinicMaster(String visitDept) {
        return null;
    }
}