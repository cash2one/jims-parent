package com.jims.medical;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.common.web.impl.BaseDto;
import com.jims.operation.api.OperatioinOrderServiceApi;
import com.jims.operation.entity.OperationSchedule;
import org.eclipse.persistence.sessions.Login;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
    public List<BaseDto> findOperation(@Context HttpServletRequest request, @QueryParam("scheduledDateTime")String scheduledDateTime,@QueryParam("operatingRoom")String operatingRoom){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        OperationSchedule operationSchedule = new OperationSchedule();
        operationSchedule.setOperatingRoom("1613");
        operationSchedule.setScheduledDateTime(DateUtils.parseDate(scheduledDateTime));
        operationSchedule.setOrgId(loginInfo.getOrgId());
       return operatioinOrderServiceApi.findOperation(operationSchedule);
    }

    /**
     * 确认手术安排列表
     * @param id
     * @author pq
     * @return
     */
    @POST
    @Path("confirm")
    public StringData confirmOperation(String id){
        OperationSchedule operationSchedule = new OperationSchedule();
        operationSchedule.setId(id);
        StringData stringData = new StringData();
        String code = operatioinOrderServiceApi.confrimOperation(operationSchedule);
        stringData.setCode(code);
        if(!"".equals(code)&&!"0".equals(code)){
            stringData.setData("success");
        }else{
            stringData.setData("error");
        }
      return stringData;
    }
}
