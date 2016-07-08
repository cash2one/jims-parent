package com.jims.doctor.diagnosis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.diagnosis.api.EmrDiagnosisServiceApi;
import com.jims.diagnosis.entity.EmrDiagnosis;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * EmrDiagnosisRest
 *
 * @author PangQian
 * @date2016/4/29 0029
 */
@Component
@Produces("application/json")
@Path("diagnosis")
public class EmrDiagnosisRest {
    @Reference(version = "1.0.0")
    private EmrDiagnosisServiceApi emrDiagnosisServiceApi;


    @Path("findListOfOut")
    @GET
    public  List<EmrDiagnosis> list(@Context HttpServletRequest request,@Context HttpServletResponse response,@QueryParam("clinicId")String clinicId){
       EmrDiagnosis emrDiagnosis=new EmrDiagnosis();
        emrDiagnosis.setClinicId(clinicId);
        List<EmrDiagnosis> page = emrDiagnosisServiceApi.findList(emrDiagnosis);
        return page;
    }
    @Path("findListOfIn")
    @GET
    public  List<EmrDiagnosis> listIn(@Context HttpServletRequest request,@Context HttpServletResponse response,@QueryParam("patientId")String patientId,@QueryParam("visitId")String visitId){
        EmrDiagnosis emrDiagnosis=new EmrDiagnosis();
        emrDiagnosis.setPatientId(patientId);
        emrDiagnosis.setVisitId(visitId);
        List<EmrDiagnosis> page = emrDiagnosisServiceApi.findList(emrDiagnosis);
        return page;
    }
    /**
     * 保存门诊诊断
     * @param emrDiagnosisList
     * @return
     */
    @Path("saveOut")
    @POST
    public StringData saveDiagnosis(List<EmrDiagnosis> emrDiagnosisList) {
        StringData data=new StringData();
        data.setCode(emrDiagnosisServiceApi.saveDiagnosis(emrDiagnosisList));
        return data;
    }

    /**
     * 保存住院诊断
     * @param emrDiagnosis
     * @return
     */
    @Path("saveIn")
    @POST
    public StringData saveIn(EmrDiagnosis emrDiagnosis) {
        StringData data=new StringData();
        emrDiagnosis.setInOrOutFlag("1");
        data.setCode(emrDiagnosisServiceApi.save(emrDiagnosis));
        return data;
    }
   @Path("delete")
   @POST
    public StringData delete(String id){
       StringData data=new StringData();
       data.setCode(emrDiagnosisServiceApi.delete(id));
       return data;
   }
}
