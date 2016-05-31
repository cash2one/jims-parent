/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.ClinicMasterServiceApi;
import com.jims.clinic.dao.ClinicMasterDao;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;


import java.util.Date;
import java.util.List;

/**
 * 病人就诊记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")

public class ClinicMasterServiceImpl extends CrudImplService<ClinicMasterDao, ClinicMaster> implements ClinicMasterServiceApi {
    @Autowired
    private ClinicMasterDao clinicMasterDao;

    /**
     * 查询 病人列表 （待诊）
     * @param doctorID
     * @return
     * @author zhaoning
     */
    @Override
    public List<ClinicMaster> getClinicMasterList(String doctorID) {
      List<ClinicMaster> list= clinicMasterDao.getClinicBydoctor(doctorID);
        return list;

    }

    /**
     * 查询病人列表 （已诊）
     * @param doctorID
     * @return
     * @author zhaoning
     */
    @Override
    public List<ClinicMaster> getClinicMasterDiagnosed(String doctorID) {
        return clinicMasterDao.getClinicMasterDiagnosed(doctorID);
    }

}