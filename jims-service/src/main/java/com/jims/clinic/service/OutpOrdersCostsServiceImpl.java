/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OutpOrdersCostsServiceApi;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊医生收费明细Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OutpOrdersCostsServiceImpl extends CrudImplService<OutpOrdersCostsDao, OutpOrdersCosts> implements OutpOrdersCostsServiceApi{
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;

    /**
     * 根据就诊ID和主记录信息查询明细信息
     * @param masterId
     * @param clinicId
     * @return
     */
    @Override
    public List<OutpOrdersCosts> getOutpCosts(String masterId, String clinicId) {
        return outpOrdersCostsDao.getOutpCosts(masterId,clinicId);
    }
}