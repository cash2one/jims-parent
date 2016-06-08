package com.jims.nurse.api;

import com.jims.common.web.impl.BaseDto;
import com.jims.exam.entity.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * OrdersNurseServiceApi
 *
 * @author PangQian
 * @date2016/6/7 0007
 */
public interface OrdersNurseServiceApi {

    /**
     * 护理端 - 查询医嘱
     * @param orders
     * @author pq
     * @return
     */
    public List<Orders> getNurseOrders(Orders orders);

    /**
     * 护理端 - 转抄医嘱
     * @param orders
     * @return
     */
    public List<Orders> ordersCopied(Orders orders);

}
