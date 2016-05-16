package com.jims.exam.api;

import com.jims.exam.entity.ExamAppoints;
import com.jims.exam.entity.Orders;

/**
 * Created by Administrator on 2016/5/10.
 */
public interface OrdersServiceApi {
    /**
     * 保存住院医嘱
     * @param examAppoints
     * @return
     */
    public String saveOrders(ExamAppoints examAppoints);

    public String deleteOrders(String ids);

    /**
     * 最大OrderNo
     * @param
     * @parampatient_id
     * @paramvisit_Id
     * @author xueyx
     * @version 2016/5/12
     */
    public String findMaxOrderNo(Orders orders);

    /**
     * 构建最新OrderNo
     * @parampatient_id
     * @paramvisit_Id
     * @author xueyx
     * @version 2016/5/12
     */
    public Long creeatOrderNo(Orders orders);

}
