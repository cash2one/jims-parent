package com.jims.patient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.support.Parameter;
import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.api.PatVisitServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.ArrayList;
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
    public List<PatientListDto>  getPatientList(@QueryParam("status") String status,@QueryParam("patName")String patName,
                                                @QueryParam("startTime")String startTime,@QueryParam("endTime")String endTime){
        List<PatientListDto> patientList = new ArrayList<PatientListDto>();
        if(status!=null && status.equals("0")){//在院中
            //获取当前登录人的 科室编码
            String deptCode="140101";
             patientList = patVisitServiceApi.getPatientList(deptCode,status,patName,startTime,endTime);
        }else  if(status!=null && status.equals("1")){//已出院
            //获取当前登录人的 科室编码
             String deptCode="140101";
             patientList = patVisitServiceApi.getPatientList(deptCode,status,patName,startTime,endTime);
        }
        return patientList;
    }

    /**
     * 查询所有 需要新建病历的病人信息
     * @return
     */
    @GET
    @Path("getPatMaster")
    public List<PatMasterIndex> getPatMaster(){
        String deptCode="140101";//当前登录人所在科室
        List<PatMasterIndex> patMasterIndexes=patVisitServiceApi.getPatMaster(deptCode);
        return patMasterIndexes;
    }
}
