package com.jims.clinic.api;

import com.jims.clinic.entity.OutpOrders;

import java.util.List;

/**
 * Created by Administrator on 2016/4/28.
 */
public interface OutpOrdersServiceApi {
    /**
     * 保存门诊医嘱主记录
     * @param outpOrders
     */
    public void saveOutpOrders(OutpOrders outpOrders);

    /**
     * 查询序列号
     * @return
     */
    public String getSerialNo();

    /**
     * 根据就诊序号就诊日期查询门诊记录
     * @param outpOrders
     * @return
     */
    public List<OutpOrders> findListFy(OutpOrders outpOrders);
}
