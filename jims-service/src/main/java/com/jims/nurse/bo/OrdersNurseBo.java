package com.jims.nurse.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.dao.OrdersDao;
import com.jims.exam.entity.Orders;
import com.jims.nurse.dao.BedRecDao;
import com.jims.nurse.entity.BedRec;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 护理端-医嘱
 *
 * @author PangQian
 * @date2016/6/7 0007
 */
@Service
@Component
@Transactional(readOnly = false)
public class OrdersNurseBo extends CrudImplService<OrdersDao, Orders> {

    /**
     * 护理端 - 查询医嘱
     *
     * @param orders
     * @return
     * @author pq
     */
    public List<Orders> getNurseOrders(Orders orders) {
        return dao.getNurseOrders(orders);
    }


    /**
     * 护理端 - 转抄医嘱
     *
     * @param orders
     * @return
     * @author pq
     */
    public List<Orders> ordersCopied(Orders orders) {
        return dao.ordersCopied(orders);
    }

    /**
     * 护理端 - 医嘱转抄
     *
     * @param ordersList
     * @return
     * @author pq
     */
    public String operationCopied(List<Orders> ordersList) {
        int num = 0;
        if (ordersList != null && ordersList.size() > 0) {
            for (int i = 0; i < ordersList.size(); i++) {
                Orders orders = new Orders();
                orders = ordersList.get(i);
                dao.operationCopied(orders);
                num++;
            }
        }
        return num + "";
    }


    /**
     * 护理端 - 医嘱校验
     *
     * @param ordersList
     * @return
     * @author pq
     */
    public String proofOrders(List<Orders> ordersList) {
        int num = 0;
        if (ordersList != null && ordersList.size() > 0) {
            for (int i = 0; i < ordersList.size(); i++) {
                Orders orders = new Orders();
                orders = ordersList.get(i);
                dao.proofOrders(orders);
                num++;
            }
        }
        return num + "";
    }



    /**
     * 护理端 - 医嘱执行
     *
     * @param ordersList
     * @return
     * @author pq
     */
    public String executeOrders(List<Orders> ordersList) {
        int num = 0;
        if (ordersList != null && ordersList.size() > 0) {
            for (int i = 0; i < ordersList.size(); i++) {
                Orders orders = new Orders();
                orders = ordersList.get(i);
                dao.executeOrders(orders);
                num++;
            }
        }
        return num + "";
    }
}

