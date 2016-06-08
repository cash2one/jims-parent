package com.jims.finance.api;


import com.jims.common.data.BaseData;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import com.jims.patient.entity.PatMasterIndex;

import java.util.List;

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

    /**
     * 根据 门诊号，获取病人退费信息
     * @param clinicNo
     * @param orgId
     * @return
     * @author zhaoning
     */
    public List<OutpRcptMaster> getBackChargeInfo(String clinicNo,String orgId);

    /**
     * 根据收据号 加载 收费项目
     * @param rcptNo
     * @return
     * @author zhaoning
     */
    public List<OutpBillItems> getBackChargeItems(String rcptNo);

    /**
     * 确认退费
     * @param rcptNo
     * @return
     * @author zhaoning
     */
    public String confirmBackChar(String rcptNo);

}
