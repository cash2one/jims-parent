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
import java.util.ArrayList;
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

    /**
     * 通过病人Id拿到手术安排(住院)
     * @param operationSchedule
     * @return
     */
    @Path("getScheduleIn")
    @POST
    public OperationSchedule getScheduleIn(OperationSchedule operationSchedule){
       /* visitId="1";*/
        return operatioinOrderServiceApi.getSchedule(operationSchedule.getPatientId(), operationSchedule.getVisitId(),operationSchedule.getClinicId());
    }

    /**
     * 通过clinicId拿到手术安排
     * @param clinicId
     * @return
     */
    @Path("getScheduleOut")
    @POST
    public OperationSchedule getScheduleOut(String clinicId){
        return operatioinOrderServiceApi.getSchedule("","",clinicId);
    }


    /**
     * 通过patientId、住院Id拿到手术名称(住院)
     * 通过clinicId 拿到手术名称（门诊）
     * @param patientId
     * @param visitId
     * @return
     */
    @Path("getOperationName")
    @POST
    public List<ScheduledOperationName> getOperationNameIn(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("patientId")String patientId,@QueryParam("visitId")String visitId,@QueryParam("clinicId")String clinicId){
     /*   patientId ="15006135";
        visitId="1";*/
        OperationSchedule operationSchedule=new OperationSchedule();
        if(clinicId!=null && !"".equals(clinicId)){
             operationSchedule= getScheduleOut(clinicId);
        }else{
            operationSchedule= operatioinOrderServiceApi.getSchedule(patientId,visitId, clinicId);
        }
        List<ScheduledOperationName> scheduledOperationNames = new ArrayList<ScheduledOperationName>();
        if(operationSchedule!=null){

                scheduledOperationNames= operatioinOrderServiceApi.getOperationName(patientId, visitId,clinicId, operationSchedule.getId());



        }

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
