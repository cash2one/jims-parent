package com.jims.operation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.operation.api.OperatioinOrderServiceApi;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * OperatioinOrderRest
 *
 * @author PangQian
 * @date2016/5/12 0012
 */
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("operatioinOrder")
public class OperatioinOrderRest {
    @Reference(version = "1.0.0")
    private OperatioinOrderServiceApi operatioinOrderServiceApi;

    /**
     * 通过科室Code查找病人列表
     * @param deptCode
     * @return
     */
    @Path("findPat")
    @GET
    public List<PatsInHospital> getOperatioin(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("deptCode") String deptCode){
       // deptCode="140102";
       return operatioinOrderServiceApi.getOperationin(deptCode);
    }

    /**
     * 保存手术名称 手术安排
     * @param operationSchedule
     * @return
     */
    @Path("save")
    @POST
    public String save(OperationSchedule operationSchedule){
      return   operatioinOrderServiceApi.saveOperation(operationSchedule);
    }

    /**
     * 通过病人Id拿到手术安排
     * @param patientId
     * @param visitId
     * @return
     */
    @Path("getSchedule")
    @POST
    public OperationSchedule getSchedule(String patientId,String visitId){
        visitId="1";
        return operatioinOrderServiceApi.getSchedule(patientId,visitId);
    }

    /**
     * 通过patientId、住院Id拿到手术名称
     * @param patientId
     * @param visitId
     * @return
     */
    @Path("getOperationName")
    @POST
    public List<ScheduledOperationName> getOperationName(String patientId,String visitId){
        patientId ="15006135";
        visitId="1";
        OperationSchedule operationSchedule= getSchedule(patientId,visitId);
        List<ScheduledOperationName> scheduledOperationNames= operatioinOrderServiceApi.getOperationName(patientId, visitId, operationSchedule.getScheduleId());
        return  scheduledOperationNames;
    }

    /**
     * 删除手术名称
     * @param id
     * @return
     */
    @Path("delete")
    @POST
    public  int delete(String id){
       return operatioinOrderServiceApi.deleteOperationName(id);
    }

}
