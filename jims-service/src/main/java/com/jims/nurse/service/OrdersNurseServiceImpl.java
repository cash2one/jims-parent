package com.jims.nurse.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.exam.entity.Orders;
import com.jims.nurse.api.BedRecServiceApi;
import com.jims.nurse.api.OrdersNurseServiceApi;
import com.jims.nurse.bo.OrdersNurseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 护理端 - 医嘱
 *
 * @author PangQian
 * @date2016/6/7 0007
 */
@Service(version = "1.0.0")
public class OrdersNurseServiceImpl implements OrdersNurseServiceApi {
@Autowired
private OrdersNurseBo ordersNurseBo;
    /**
     * 护理端 - 查询医嘱
     * @param orders
     * @author pq
     * @return
     */
    public List<Orders> getNurseOrders(Orders orders){
     return ordersNurseBo.getNurseOrders(orders);
    }

    /**
     * 护理端 - 转抄医嘱查询
     * @param orders
     * @author pq
     * @return
     */
    public List<Orders> ordersCopied(Orders orders){
        return  ordersNurseBo.ordersCopied(orders);
    }


}

