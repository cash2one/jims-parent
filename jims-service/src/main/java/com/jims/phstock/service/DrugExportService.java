package com.jims.phstock.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.phstock.api.DrugExportServiceApi;

import com.jims.phstock.dao.DrugExportMasterDao;
import com.jims.phstock.entity.DrugExportDetail;
import com.jims.phstock.dao.DrugExportDetailDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * 药品出库Service
 * @author lgx
 * @version 2016-05-23
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class DrugExportService extends CrudImplService<DrugExportDetailDao, DrugExportDetail> implements DrugExportServiceApi{

    @Autowired
    private DrugExportMasterDao masterDao;
}