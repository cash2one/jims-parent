/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.jims.clinic.api.OutpOrdersServiceApi;
import com.jims.clinic.dao.OutpOrdersDao;
import com.jims.clinic.entity.OutpOrders;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊医嘱记录Service
 *
 * @author zhaoning
 * @version 2016-04-20
 */
@Service
@Transactional(readOnly = true)
public class OutpOrdersServiceImpl extends CrudImplService<OutpOrdersDao, OutpOrders> implements OutpOrdersServiceApi {
    @Autowired
    private OutpOrdersDao outpOrdersDao;


    @Override
    public void saveOutpOrders(OutpOrders outpOrders) {
         outpOrdersDao.saveOutpOrders(outpOrders);
    }

    @Override
    public String getSerialNo() {
        return outpOrdersDao.getSerialNo();
    }

    @Override
    public List<OutpOrders> findListFy(OutpOrders outpOrders) {
        return outpOrdersDao.findAllList(outpOrders);
    }

    @Override
    public int deleteOutpOrders(String  visitNo) {
        return outpOrdersDao.deleteOutpOrders(visitNo);
    }
}