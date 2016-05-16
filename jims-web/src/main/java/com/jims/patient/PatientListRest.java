package com.jims.patient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.api.PatVisitServiceApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Administrator on 2016/5/13.
 * 病人列表Rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("patList")
public class PatientListRest {
    @Reference(version = "1.0.0")
    private PatVisitServiceApi patVisitServiceApi;

    /**
     * 根据当前登录医生的 所在科室  查询 住院病人列表
     * @return
     */
    @Path("patVistList")
    @GET
    public List<PatientListDto>  getPatientList(){
        //获取当前登录人的 科室编码
        String deptCode="140101";
       List<PatientListDto> patientList = patVisitServiceApi.getPatientList(deptCode);
       return patientList;
    }
}
