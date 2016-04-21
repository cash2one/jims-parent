/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OutpPrescServiceApi;
import com.jims.clinic.dao.OutpPrescDao;
import com.jims.clinic.entity.OutpPresc;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 处方医嘱明细记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OutpPrescServiceImpl extends CrudImplService<OutpPrescDao, OutpPresc> implements OutpPrescServiceApi{

    @Autowired
    private OutpPrescDao outpPrescDao;

    /**
     * 根据病人诊断记录查询处方主记录
     * @param clinicMasterId
     * @return
     */
    @Override
    public List<OutpPresc> getOutpPresc(String clinicMasterId) {
        return outpPrescDao.getOutpPresc(clinicMasterId);
    }
}