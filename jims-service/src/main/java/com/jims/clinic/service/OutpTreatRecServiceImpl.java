/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OutpTreatRecServiceApi;
import com.jims.clinic.dao.OutpTreatRecDao;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 检查治疗医嘱明细记录Service
 *
 * @author zhaoning
 * @version 2016-04-25
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OutpTreatRecServiceImpl extends CrudImplService<OutpTreatRecDao, OutpTreatRec> implements OutpTreatRecServiceApi {
    @Autowired
    private OutpTreatRecDao outpTreatRecDao;


    @Override
    public Integer loadItmes(Date visitDate, Integer visitNo, String itemClass) {
        return outpTreatRecDao.loadItems(visitDate,visitNo,itemClass);
    }

    @Override
    public void saveTreatRec(OutpTreatRec outpTreatRec) {
         outpTreatRecDao.saveTreatRec(outpTreatRec);
    }

//    @Override
//    public Integer deleteTreatRec(int visitNo) {
//        return outpTreatRecDao.deleteTreatRec(visitNo);
//    }

    @Override
    public Integer getSerialNo() {
        return outpTreatRecDao.getSerialNo();
    }

    @Override
    public List<OutpTreatRec> getPrintLab(String serialNo, Date visitDate, Integer visitNo) {
        return outpTreatRecDao.getPrintLab(serialNo,visitDate,visitNo);
    }
}