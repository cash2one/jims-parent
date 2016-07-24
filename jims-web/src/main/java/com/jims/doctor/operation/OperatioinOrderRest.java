package com.jims.doctor.operation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
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
    public String save(OperationSchedule operationSchedule,@Context HttpServletRequest request){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        operationSchedule.setEnteredBy(loginInfo.getPersionId());
        operationSchedule.setDoctorUser(loginInfo.getPersionId());
        operationSchedule.setOperatingDept(loginInfo.getDeptCode());
        operationSchedule.setOrgId(loginInfo.getOrgId());
      return   operatioinOrderServiceApi.saveOperationIn(operationSchedule);
    }

    /**
     * 保存手术名称 手术安排（门诊）
     * @param operationSchedule
     * @return
     */
    @Path("saveOut")
    @POST
    public String saveOut(OperationSchedule operationSchedule,@Context HttpServletRequest request, @Context HttpServletResponse response){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        operationSchedule.setEnteredBy(loginInfo.getPersionId());
        operationSchedule.setDoctorUser(loginInfo.getPersionId());
        operationSchedule.setOperatingDept(loginInfo.getDeptCode());
        operationSchedule.setOrgId(loginInfo.getOrgId());
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
     * 通过clinicId拿到门诊手术安排
     * @param clinicId
     * @return
     */
    @Path("getScheduleOut")
    @POST
    public List<OperationSchedule> getScheduleOut(String clinicId){
        return operatioinOrderServiceApi.getSchedule("","",clinicId);
    }

    /**
     * 通过visitId，patientId拿到住院手术安排
     * @param visitId
     * @return
     */
    @Path("getScheduleOutHos")
    @POST
    public List<OperationSchedule> getScheduleOutHos(@QueryParam("visitId")String visitId,@QueryParam("patientId")String patientId){
        List<OperationSchedule> operationScheduleList=  operatioinOrderServiceApi.getScheduleHos(patientId, visitId);
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
     * 删除门诊手术名称
     * @param id
     * @return
     */
    @Path("delete")
    @POST
    public  int delete(String id){
       return operatioinOrderServiceApi.deleteOperationName(id);
    }
    /**
     * 删除住院手术名称
     * @param id
     * @return
     */
    @Path("deleteHos")
    @POST
    public  String deleteHos(String id){
        String num = operatioinOrderServiceApi.deleteOperationHos(id);
        return num;
    }

    /**
     * 删除手术名称（子表）
     * @param id
     * @return
     */
    @Path("deleteScheduledOperationName")
    @GET
    public String deleteScheduledOperationName(@QueryParam("id")String id){
        String num = operatioinOrderServiceApi.deleteScheduledOperationName(id);
        return num;
    }

}
