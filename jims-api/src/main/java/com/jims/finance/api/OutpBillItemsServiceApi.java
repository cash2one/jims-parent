package com.jims.finance.api;

import com.jims.finance.entity.OutpBillItems;
import com.jims.finance.entity.OutpRcptMaster;

import java.util.List;

/**
 * 门诊病人诊疗收费项目明细
 *
 * @author PangQian
 * @date2016/6/1 0001
 */
public interface OutpBillItemsServiceApi {
    /**
     * 方法 findItems的功能描述
     * 门诊-收费结账-项目
     * @param outpRcptMaster
     * @return
     * @throws
     * @author pq
     * @date 2016/6/1 0001
     */
    public List<OutpBillItems> findItems(OutpRcptMaster outpRcptMaster);


}
