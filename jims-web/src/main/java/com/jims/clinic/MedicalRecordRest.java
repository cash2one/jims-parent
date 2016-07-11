package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.MedicalRecordServiceApi;
import com.jims.clinic.vo.MedicalRecordVo;
import com.jims.common.data.StringData;
import com.jims.patient.entity.PatVisit;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

/**
 * Created by Administrator on 2016/7/7.
 * 病案首页 Rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("medicalRecord")
public class MedicalRecordRest {
    @Reference(version = "1.0.0")
    private MedicalRecordServiceApi medicalRecordServiceApi;

    /**
     * 获取病案首页信息
     * @param patientId
     * @return
     * @author zhaoning
     */
    @GET
    @Path("getMedicalRecInfo")
    public MedicalRecordVo getMedicalRecInfo(@QueryParam("patientId")String patientId){
        return medicalRecordServiceApi.getMedicalInfo(patientId);
    }

    /**
     * 病案首页 其他信息更新
     * @param patVisit
     * @param patientId
     * @return
     * @author zhaoning
     */
    @POST
    @Path("updateOtherInfo")
    public StringData updateOtherInfo(PatVisit patVisit,@QueryParam("patientId")String patientId){
        StringData data = new StringData();
        data.setCode(medicalRecordServiceApi.updateOtherInfo(patVisit,patientId));
        return data;
    }
}
