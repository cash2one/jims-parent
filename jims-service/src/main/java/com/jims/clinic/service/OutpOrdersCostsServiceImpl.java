/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.OutpOrdersCostsServiceApi;
import com.jims.clinic.dao.OutpOrdersCostsDao;
import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpTreatRec;
import com.jims.common.service.impl.CrudImplService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;



import java.util.Date;
import java.util.List;

/**
 * 门诊医生收费明细Service
 *
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")

public class OutpOrdersCostsServiceImpl extends CrudImplService<OutpOrdersCostsDao, OutpOrdersCosts> implements OutpOrdersCostsServiceApi {
    @Autowired
    private OutpOrdersCostsDao outpOrdersCostsDao;

    /**
     * 根据就诊ID和医嘱号信息查询明细信息
     *
     * @param orderNo
     * @param clinicId
     * @return
     */
    @Override
    public List<OutpOrdersCosts> getOutpCosts(String orderNo, String clinicId) {
        return outpOrdersCostsDao.getOutpCosts(orderNo, clinicId);
    }

    public OutpOrdersCosts get(String id) {
        return super.get(id);
    }

    public Integer getSerialNo() {
        return outpOrdersCostsDao.getSerialNo();
    }


    /**
     * 查询门诊信息
     *
     * @param visitDate
     * @param visitNo
     * @return
     */
    public List<OutpOrdersCosts> getOutpOrders(Date visitDate, Integer visitNo, String itemClass) {
        return outpOrdersCostsDao.loadOutpOrders(visitDate, visitNo, itemClass);
    }

    /**
     * 查询出最大的医嘱号
     */
    public Integer getMaxOrderNo(String clinicId,String orgId) {
        return outpOrdersCostsDao.getMaxOrderNo(clinicId,orgId);
    }


    /**
     * 删除收费明细
     *
     * @param clinicId
     */
    public int deleteOutpOrders(String clinicId) {
        return outpOrdersCostsDao.deleteOutpOrders(clinicId);
    }


    /**
     * 删除收费明细治疗
     *
     * @param outpOrdersCosts
     */
    @Override
    public Integer deleteOutpOrdersTreatRec(OutpOrdersCosts outpOrdersCosts, OutpTreatRec outpTreatRec) {
        return null;
    }



}