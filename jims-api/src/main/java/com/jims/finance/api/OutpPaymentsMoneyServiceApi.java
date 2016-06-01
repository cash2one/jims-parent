package com.jims.finance.api;

import com.jims.finance.entity.OutpPaymentsMoney;
import com.jims.finance.entity.OutpRcptMaster;

import java.util.List;

/**
 * 门诊病人支付方式记录
 *
 * @author PangQian
 * @date2016/5/31 0031
 */
public interface OutpPaymentsMoneyServiceApi {
    /**
     * 方法 findMaoneyPayment 的功能描述
     * 门诊结算 - 收费结账
     * @param
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public List<OutpPaymentsMoney> findMaoneyPayment(OutpRcptMaster outpRcptMaster);

}
