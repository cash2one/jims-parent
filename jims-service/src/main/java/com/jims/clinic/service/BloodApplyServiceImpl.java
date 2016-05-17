/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.blood.api.BloodApplyServiceApi;
import com.jims.blood.entity.BloodComponent;
import com.jims.clinic.dao.BloodApplylDao;
import com.jims.clinic.dao.BloodCapacityDao;
import com.jims.blood.entity.BloodApply;
import com.jims.blood.entity.BloodCapacity;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.dao.OrdersDao;
import com.jims.exam.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用血申请Service
 *
 * @author qlx
 * @version 2016-04-28
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class BloodApplyServiceImpl extends CrudImplService<BloodApplylDao, BloodApply> implements BloodApplyServiceApi {
    @Autowired
    private BloodCapacityDao bloodCapacityDao;
    @Autowired
    private OrdersDao ordersDao;

    /**
     * 保存用血申请和用血量申请
     *
     * @author qinlongx
     * @version 2016-04-28
     */
    @Transactional(readOnly = false)
    public String saveBloodApply(BloodApply bloodApply) {
        String strState = super.save(bloodApply);
        bloodCapacityDao.delBloodCapacity(bloodApply.getApplyNum());
        List<BloodCapacity> bloodCapacityList = bloodApply.getBloodCapacityList();
        for (int i = 0; i < bloodApply.getBloodCapacityList().size(); i++) {
            BloodCapacity bloodCapacity = bloodCapacityList.get(i);
            bloodCapacity.setApplyNum(bloodApply.getApplyNum());
            bloodCapacity.preInsert();
            bloodCapacityDao.insert(bloodCapacity);
            Orders orders = new Orders();
            orders.setOrderSubNo(i + 1);
            orders.preInsert();
            if (bloodApply.getVisitId() != null) {
                if (ordersDao.getOrderNo(bloodApply.getPatientId(), bloodApply.getVisitId()) != null) {
                    orders.setOrderNo(ordersDao.getOrderNo(bloodApply.getPatientId(), bloodApply.getVisitId()));
                    orders.setPatientId(bloodApply.getPatientId());
                    orders.setVisitId(bloodApply.getVisitId());
                    orders.setStartDateTime(bloodApply.getApplyDate());
                    orders.setRepeatIndicator("1"); // 长期医嘱标志
                    orders.setOrderClass("1");//医嘱类型
                    orders.setOrderText(bloodCapacity.getBloodType());//申请用血成分
                    ordersDao.insert(orders);
                } else {
                    orders.setOrderNo(ordersDao.getOrderNo(orders.getPatientId(), orders.getVisitId()) + 1);
                    orders.setPatientId(bloodApply.getPatientId());
                    orders.setVisitId(bloodApply.getVisitId());
                    orders.setStartDateTime(bloodApply.getApplyDate());
                    orders.setRepeatIndicator("1"); // 长期医嘱标志
                    orders.setOrderClass("1");//医嘱类型
                    orders.setOrderText(bloodCapacity.getBloodType());//申请用血成分
                    ordersDao.insert(orders);
                }
            } else {
                if (ordersDao.getOrderNo(bloodApply.getPatientId(), bloodApply.getVisitId()) != null) {
                    orders.setOrderNo(ordersDao.getOrderNo(bloodApply.getPatientId(), bloodApply.getVisitId()));
                    orders.setPatientId(bloodApply.getPatientId());
                    orders.setVisitId(bloodApply.getVisitId());
                    orders.setStartDateTime(bloodApply.getApplyDate());
                    orders.setRepeatIndicator("1"); // 长期医嘱标志
                    orders.setOrderClass("1");//医嘱类型
                    orders.setOrderText(bloodCapacity.getBloodType());//申请用血成分
                    ordersDao.insert(orders);
                } else {
                    orders.setOrderNo(ordersDao.getOrderNo(orders.getPatientId(), orders.getVisitId()) + 1);
                    orders.setPatientId(bloodApply.getPatientId());
                    orders.setVisitId(bloodApply.getVisitId());
                    orders.setStartDateTime(bloodApply.getApplyDate());
                    orders.setRepeatIndicator("1"); // 长期医嘱标志
                    orders.setOrderClass("1");//医嘱类型
                    orders.setOrderText(bloodCapacity.getBloodType());//申请用血成分
                    ordersDao.insert(orders);
                }
            }
        }
        return strState;
    }
}
