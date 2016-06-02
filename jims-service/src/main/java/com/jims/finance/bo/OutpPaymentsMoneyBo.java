package com.jims.finance.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.dao.OutpPaymentsMoneyDao;
import com.jims.finance.entity.OutpPaymentsMoney;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 门诊病人支付方式记录
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
@Service
@Component
@Transactional(readOnly = false)
public class OutpPaymentsMoneyBo extends CrudImplService<OutpPaymentsMoneyDao, OutpPaymentsMoney> {

    /**
     * 方法 findMaoneyPayment 的功能描述
     * 门诊结算 - 收费结账
     * @param
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public List<OutpPaymentsMoney> findMaoneyPayment(OutpRcptMaster outpRcptMaster){
        return dao.findMaoneyPayment(outpRcptMaster);
    }
}
