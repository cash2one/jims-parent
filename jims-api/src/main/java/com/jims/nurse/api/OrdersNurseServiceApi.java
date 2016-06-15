package com.jims.nurse.api;

import com.jims.common.web.impl.BaseDto;
import com.jims.exam.entity.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;

/**
 * 护理端 - 医嘱管理
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
     * @author pq
     * @return
     */
    public List<Orders> ordersCopied(Orders orders);

    /**
     * 护理端 - 转抄
     * @param ordersList
     * @author pq
     * @return
     */
    public  String operationCopied(List<Orders> ordersList);

    /**
     * 护理端 - 医嘱校验
     * @param ordersList
     * @author pq
     * @return
     */
    public String proofOrders(List<Orders> ordersList);

    /**
     * 护理端 - 医嘱执行
     * @param ordersList
     * @return
     */
    public String executeOrders(List<Orders> ordersList);
}
