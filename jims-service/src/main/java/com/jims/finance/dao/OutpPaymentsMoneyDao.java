/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.finance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.finance.entity.OutpPaymentsMoney;
import com.jims.finance.entity.OutpRcptMaster;

import java.util.List;

/**
 * 门诊病人支付方式记录DAO接口
 * @author zhaoning
 * @version 2016-05-26
 */
@MyBatisDao
public interface OutpPaymentsMoneyDao extends CrudDao<OutpPaymentsMoney> {

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