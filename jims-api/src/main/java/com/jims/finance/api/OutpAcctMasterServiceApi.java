package com.jims.finance.api;

import com.jims.finance.entity.OutpAcctMaster;
import com.jims.finance.entity.OutpRcptMaster;

/**
 * 门诊收费结帐主记录
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
public interface OutpAcctMasterServiceApi {
    /**
     * 方法 getMaxAcctNo的功能描述
     * 拿到最大的结账序号
     * @param
     * @reurn
     * @thrws
     * @author pq
     * @date 2016/6/1 0001
     */
    public String getMaxAcctNo();


    /**
     * 方法 saveOutpAcct的功能描述
     * 门诊结账 - 收费结账 - 结账确认
     * @param outpRcptMaster
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public String saveOutpAcct(OutpRcptMaster outpRcptMaster);



}
