package com.jims.clinic.api;

import com.jims.clinic.entity.OutpOrdersCosts;
import com.jims.clinic.entity.OutpTreatRec;

import java.util.Date;
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

    public OutpOrdersCosts get(String id);

    public Integer getSerialNo();

    /**
     * 查询出最大的医嘱号
     */
    public Integer getMaxOrderNo(Date visitDate ,Integer visitNo, String serialNo);

    /**
     * 查询出最大的收费序号
     * @param visitDate
     * @param visitNo
     * @param itemClass
     * @return
     */
    public  Integer getMaxItemNo(Date visitDate ,Integer visitNo, String itemClass);
    /**
     * 删除收费明细
     * @param masterId
     */
    public Integer deleteOutpOrders(String masterId,String visitNo);
    /**
     * 删除收费明细治疗
     * @param outpOrdersCosts
     */
    public Integer deleteOutpOrdersTreatRec(OutpOrdersCosts outpOrdersCosts,OutpTreatRec outpTreatRec);

}
