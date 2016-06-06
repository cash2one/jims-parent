package com.jims.clinic.bo;

import com.jims.clinic.dao.PreDischgedPatsDao;
import com.jims.clinic.entity.PreDischgedPats;
import com.jims.common.service.impl.CrudImplService;
import com.jims.exam.dao.OrdersDao;
import com.jims.exam.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 出院通知单Service
 *
 * @author qinlongxin
 * @version 2016-06-02
 */
@Service
@Transactional(readOnly = false)
public class PreDischgedPatsBo extends CrudImplService<PreDischgedPatsDao, PreDischgedPats> {
    @Autowired
    private OrdersDao ordersDao;
    @Transactional(readOnly = false)
    public String savePreDischPat(PreDischgedPats preDischgedPats) {
        if (preDischgedPats != null) {
            Orders orders = preDischgedPats.getOrders();
            if (orders != null) {
                if (orders.getIsNewRecord()) {
                    orders.preInsert();
                    ordersDao.insert(orders);
                } else {
                    orders.preUpdate();
                    ordersDao.update(orders);
                }
            }
        }
        return super.save(preDischgedPats);
    }
}