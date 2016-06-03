package com.jims.finance;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.DateUtils;
import com.jims.finance.api.OutPatientCostServiceApi;
import com.jims.finance.api.OutpRcptMasterServiceApi;
import com.jims.finance.entity.OutpRcptMaster;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.text.DateFormat;
import java.util.List;

/**
 * 门诊医疗收据记录
 *
 * @author PangQian
 * @date2016/5/31 0031
 */
@Component
@Produces("application/json")
@Path("oupRcptMaster")
public class OupRcptMasterRest {
    @Reference(version = "1.0.0")
    private OutpRcptMasterServiceApi outpRcptMasterServiceApi;

    /**
     * 方法 findCharge 的能描述
     * 查询收费结账的收据
     * @param
     * @reurn
     * @thows
     * @author pq
     * @date 2016/5/31 0031
     */
    @Path("findCharge")
    @POST
    public OutpRcptMaster findCharge(OutpRcptMaster outpRcptMaster){
       try{
           if(outpRcptMaster.getAcctDate()!=null){
               outpRcptMaster.setVisitDate(DateUtils.parseDate(outpRcptMaster.getAcctDate(),"yyyy-MM-dd HH:mm:ss"));
           }
       }catch (Exception e){
           e.printStackTrace();
       }
       return outpRcptMasterServiceApi.findCharge(outpRcptMaster);
    }


}
