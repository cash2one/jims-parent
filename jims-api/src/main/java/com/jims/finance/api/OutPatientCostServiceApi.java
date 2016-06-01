package com.jims.finance.api;


import com.jims.common.data.BaseData;
import com.jims.common.web.impl.BaseDto;

/**
 * 划价收费
 *
 * @author zhangyao
 * @date2016/5/25
 */
public interface OutPatientCostServiceApi {

    public BaseData<BaseDto> list(String orgId,String clinicNo);

    /**
     * 确认收费
     * @param ids
     * @return
     * @author zhaoning
     */
    public String confirmPay(String ids);

}
