package com.jims.exam.api;

import com.jims.exam.entity.ExamAppoints;

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
}
