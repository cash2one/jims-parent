/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.orders.service;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.orders.entity.OrdersCosts;
import com.jims.common.web.impl.BaseDto;
import com.jims.orders.api.OrdersServiceApi;
import com.jims.orders.bo.OrdersServiceBo;
import com.jims.exam.entity.ExamAppoints;
import com.jims.orders.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;



/**
 * 住院医嘱Service
 * @author zhangpeng
 * @version 2016-05-09
 */
@Service(version = "1.0.0")
public class OrdersServiceImpl implements OrdersServiceApi{

    @Autowired
    private OrdersServiceBo ordersServiceBo;


    @Override
    public String saveOrders(ExamAppoints examAppoints) {
        return ordersServiceBo.saveOrders(examAppoints);
    }

    @Override
    public String deleteOrders(String ids) {
        return ordersServiceBo.deleteOrders(ids);
    }

    @Override
    public String findMaxOrderNo(Orders orders) {
        return ordersServiceBo.findMaxOrderNo(orders);
    }

    @Override
    public Long creeatOrderNo(Orders orders) {
        return ordersServiceBo.creeatOrderNo(orders);
    }

    @Override
    public Page<Orders> getPatientOrders(Page<Orders> page,Orders orders) {
        return ordersServiceBo.getPatientOrders(page,orders);
    }

    @Override
    public String saveOrdersNew(List<Orders> ordersList,LoginInfo loginInfo) {
        return ordersServiceBo.saveOrdersNew(ordersList,loginInfo);
    }

    @Override
    public String saveSubOrder(Orders orders) {
        return ordersServiceBo.saveSubOrder(orders);
    }

    /**
     * 下达医嘱
     * @param orders
     * @return
     * pq
     */
    @Override
    public String issuedOrders(Orders orders) {
        return ordersServiceBo.issuedOrders(orders);
    }

    @Override
    public String deleteOrdersNew(String ids) {
        return ordersServiceBo.deleteOrdersNew(ids);
    }

    @Override
    public Integer getMaxOrderNo(String patientId, String visitId) {
        return ordersServiceBo.getMaxOrderNo(patientId, visitId);
    }

    @Override
    public Integer getOrderSubNo(String patientId, String visitId, Integer orderNo) {
        return ordersServiceBo.getOrderSubNo(patientId, visitId, orderNo);
    }

    @Override
    public Orders get(String id) {
        return ordersServiceBo.get(id);
    }

    @Override
    public List<Orders> getSubOrders(Orders orders) {
        return ordersServiceBo.getSubOrders(orders);
    }

    @Override
    public List<OrdersCosts> getById(String orderId) {
        return ordersServiceBo.getById(orderId);
    }

    @Override
    public List<BaseDto> getClinicPrice(String itemCode) {
        return ordersServiceBo.getClinicPrice(itemCode);
    }

    @Override
    public List<OrdersCosts> getOrdersCost(String patientId,String visitId) {
        return ordersServiceBo.getOrdersCost(patientId,visitId);
    }

    /**
     * 停止医嘱(长期医嘱，停止时间是空)
     * @param orders
     * @author pq
     * @return
     */
    public String stopOrders(Orders orders){
        return  ordersServiceBo.stopOrders(orders);
    }

    /**
     * 作废医嘱
     * @param orders
     * @author pq
     * @return
     */
    public String cancelOrders(Orders orders){
        return ordersServiceBo.cancelOrders(orders);
    }
}