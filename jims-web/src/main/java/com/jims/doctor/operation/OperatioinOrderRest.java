package com.jims.doctor.operation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.operation.api.OperatioinOrderServiceApi;
import com.jims.operation.api.ScheduledOperationNameApi;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * 手术预约（申请）
 *
 * @author PangQian
 * @date2016/5/12 0012
 */
@Component
@Produces("application/json")
@Path("operatioinOrder")
public class OperatioinOrderRest {
    @Reference(version = "1.0.0")
    private OperatioinOrderServiceApi operatioinOrderServiceApi;
    @Reference(version = "1.0.0")
    private ScheduledOperationNameApi scheduledOperationNameApi;

    /**
     * 通过科室Code查找病人列表
     * @param deptCode
     * @return
     */
    @Path("findPat")
    @GET
    public List<PatsInHospital> getOperatioin(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("deptCode") String deptCode){
//       deptCode="140101";
               List<PatsInHospital> patsInHospitalList = operatioinOrderServiceApi.getOperationin(deptCode);
        return patsInHospitalList;
    }

    /**
     * 保存手术名称 手术安排（住院）
     * @param operationSchedule
     * @return
     */
    @Path("saveIn")
    @POST
    public String save(OperationSchedule operationSchedule){
      return   operatioinOrderServiceApi.saveOperationIn(operationSchedule);
    }

    /**
     * 保存手术名称 手术安排（门诊）
     * @param operationSchedule
     * @return
     */
    @Path("saveOut")
    @POST
    public String saveOut(OperationSchedule operationSchedule){
     return  operatioinOrderServiceApi.saveOperationOut(operationSchedule);
    }

//    /**
//     * 通过病人Id拿到手术安排(住院)
//     * @param operationSchedule
//     * @return
//     */
//    @Path("getScheduleIn")
//     @POST
//     public OperationSchedule getScheduleIn(OperationSchedule operationSchedule){
//       /* visitId="1";*/
//        return operatioinOrderServiceApi.getSchedule(operationSchedule.getPatientId(), operationSchedule.getVisitId(),operationSchedule.getClinicId());
//    }

    /**
     * 通过clinicId拿到手术安排
     * @param clinicId
     * @return
     */
    @Path("getScheduleOut")
    @POST
    public List<OperationSchedule> getScheduleOut(String clinicId){
        return operatioinOrderServiceApi.getSchedule("","",clinicId);
    }

    /**
     * 通过visitId拿到手术安排
     * @param visitId
     * @return
     */
    @Path("getScheduleOutHos")
    @POST
    public List<OperationSchedule> getScheduleOutHos(String visitId){
        List<OperationSchedule> operationScheduleList=  operatioinOrderServiceApi.getSchedule("", visitId, "");
        return operationScheduleList;
    }


    /**
     * 通过clinicId 拿到手术主记录（门诊）
     * @return
     */
    @Path("getOperationName")
    @GET
    public List<OperationSchedule> getOperationName(@Context HttpServletRequest request,
                                                      @Context HttpServletResponse response,@QueryParam("clinicId")String clinicId){
        List<OperationSchedule> operationScheduleList = operatioinOrderServiceApi.getSchedule("","",clinicId);
        return operationScheduleList;
    }

    /**
     * 通过id获取单条数据
     * @param id
     * @return
     */
    @Path("get")
    @GET
    public OperationSchedule get(@QueryParam("id")String id){
        OperationSchedule operationSchedule = operatioinOrderServiceApi.getOneOperation(id);
        return operationSchedule;
    }

    /**
     * 通过scheduleId获取手术安排
     * @param scheduleId
     * @return
     */
    @Path("getOperationNameList")
    @GET
    public List<ScheduledOperationName> getOperationNameList(@QueryParam("scheduleId")String scheduleId){
            List<ScheduledOperationName> scheduledOperationNameList =  scheduledOperationNameApi.getOperationNameList(scheduleId);
            return scheduledOperationNameList;
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
