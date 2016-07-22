package com.jims.finance.outpAccounts.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.vo.LoginInfo;
import com.jims.finance.api.OutpAcctMasterServiceApi;
import com.jims.finance.entity.OutpAcctMaster;
import com.jims.finance.entity.OutpRcptMaster;
import com.jims.finance.outpAccounts.bo.OutpAcctMasterBo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * OutpAcctMasterServiceImpl
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
@Service(version = "1.0.0")
public class OutpAcctMasterServiceImpl implements OutpAcctMasterServiceApi{
    @Autowired
    private OutpAcctMasterBo outpAcctMasterBo;

    /**
     * 方法 getMaxAcctNo的功能描述
     * 拿到最大的结账序号
     * @param
     * @reurn
     * @thrws
     * @author pq
     * @date 2016/6/1 0001
     */
    public String getMaxAcctNo(){
       return outpAcctMasterBo.getMaxAcctNo();
    }


    /**
     * 方法 saveOutpAcct的功能描述
     * 门诊结账 - 收费结账 - 结账确认
     * @param outpRcptMaster
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public String saveOutpAcct(OutpRcptMaster outpRcptMaster,LoginInfo loginInfo){
        return  outpAcctMasterBo.saveOutpAcct(outpRcptMaster,loginInfo);
    }





}
