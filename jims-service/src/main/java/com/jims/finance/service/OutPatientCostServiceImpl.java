package com.jims.finance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.data.BaseData;
import com.jims.common.web.impl.BaseDto;
import com.jims.finance.api.OutPatientCostServiceApi;

import com.jims.finance.bo.OutPatientCostBo;
import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * 划价收费
 *
 * @author zhangyao
 * @date2016/5/25
 */
@Service(version = "1.0.0")
public class OutPatientCostServiceImpl  implements OutPatientCostServiceApi {


    @Autowired
    private OutPatientCostBo outPatientCostBo;

    @Override
    public BaseData<BaseDto> list(String orgId, String clinicNo) {
        return outPatientCostBo.list(orgId,clinicNo);
    }

    /**
     * 确认收费
     * @param ids
     * @return
     */
    @Override
    public String confirmPay(String ids) {
        return outPatientCostBo.confirmPay(ids);
    }

    @Override
    public List<OutpRcptMaster> getBackChargeInfo(String clinicNo, String orgId) {
        return outPatientCostBo.getBackChargeInfo(clinicNo,orgId);
    }

    @Override
    public List<OutpBillItems> getBackChargeItems(String rcptNo) {
        return outPatientCostBo.getBackChargeItems(rcptNo);
    }

    @Override
    public String confirmBackChar(String rcptNo) {
        return outPatientCostBo.confirmBackChar(rcptNo);
    }
}
