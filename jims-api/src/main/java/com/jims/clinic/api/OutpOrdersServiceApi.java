package com.jims.clinic.api;

import com.jims.clinic.entity.OutpOrders;

/**
 * CTQ
 * 2016年4月25日13:36:59
 * 门诊医嘱API
 */
public interface OutpOrdersServiceApi {
    /**
     * 保存处方信息
     * @param outpOrders
     * @return
     */
    public String save(OutpOrders outpOrders);
}
