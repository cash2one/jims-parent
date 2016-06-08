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
     * @param orders
     * @author pq
     * @return
     */
    public List<Orders> getNurseOrders(Orders orders){
        return  dao.getNurseOrders(orders);
    }


    /**
     * 护理端 - 转抄医嘱
     * @param orders
     * @author pq
     * @return
     */
    public List<Orders> ordersCopied(Orders orders){
        return  dao.ordersCopied(orders);
    }



}
