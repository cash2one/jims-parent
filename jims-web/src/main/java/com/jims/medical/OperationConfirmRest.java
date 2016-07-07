package com.jims.medical;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.web.impl.BaseDto;
import com.jims.operation.api.OperatioinOrderServiceApi;
import com.jims.operation.entity.OperationSchedule;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 门诊手术安排确认
 *
 * @author PangQian
 * @date2016/7/6 0006
 */
@Component
@Produces("application/json")
@Path("operationConfirm")
public class OperationConfirmRest {
    @Reference(version = "1.0.0")
    private OperatioinOrderServiceApi operatioinOrderServiceApi;

    /**
     * 查询手术安排列表
     * @param scheduledDateTime
     * @param operatingRoom
     * @return
     */
    @GET
    @Path("findOperation")
    public List<BaseDto> findOperation(@QueryParam("scheduledDateTime")String scheduledDateTime,@QueryParam("operatingRoom")String operatingRoom){
         operatingRoom = "1613";
       return operatioinOrderServiceApi.findOperation(scheduledDateTime,operatingRoom);
    }

    /**
     * 确认手术安排列表
     * @param operationScheduleList
     * @author pq
     * @return
     */
    @POST
    @Path("confirm")
    public StringData confirmOperation(List<OperationSchedule> operationScheduleList){
        StringData stringData = new StringData();
        String code = operatioinOrderServiceApi.confrimOperation(operationScheduleList);
        stringData.setCode(code);
        if(!"".equals(code)&&!"0".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
      return stringData;
    }
}
