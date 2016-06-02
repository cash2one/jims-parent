/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.finance.api.OutpPaymentsMoneyServiceApi;
import com.jims.finance.bo.OutpPaymentsMoneyBo;
import com.jims.finance.dao.OutpPaymentsMoneyDao;
import com.jims.finance.entity.OutpPaymentsMoney;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 门诊病人支付方式记录Service
 * @author zhaoning
 * @version 2016-05-26
 */
@Service(version = "1.0.0")
public class OutpPaymentsMoneyService implements OutpPaymentsMoneyServiceApi {
  @Autowired
  private OutpPaymentsMoneyBo outpPaymentsMoneyBo;
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
       return outpPaymentsMoneyBo.findMaoneyPayment(outpRcptMaster);
    }

}