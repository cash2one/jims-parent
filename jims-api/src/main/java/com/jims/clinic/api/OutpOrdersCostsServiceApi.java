package com.jims.clinic.api;

import com.jims.clinic.entity.OutpOrdersCosts;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 * 门诊收费明细Api接口
 * @author zhaoning
 * @version 2016-04-21
 */
public interface OutpOrdersCostsServiceApi {

    /**
     * 根据病人就诊ID和主记录ID查询明细信息
     * @param masterId
     * @param clinicId
     * @return
     */
     public List<OutpOrdersCosts> getOutpCosts(String masterId ,String clinicId);
}
