package com.jims.patient;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.support.Parameter;
import com.jims.common.data.StringData;
import com.jims.patient.Dto.PatientListDto;
import com.jims.patient.api.PatVisitServiceApi;
import com.jims.patient.entity.PatMasterIndex;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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
     * @author zhaoning
     */
    @GET
    @Path("getPatMaster")
    public List<PatMasterIndex> getPatMaster(){
        String deptCode="140101";//当前登录人所在科室
        List<PatMasterIndex> patMasterIndexes=patVisitServiceApi.getPatMaster(deptCode);
        return patMasterIndexes;
    }

    /**
     * 确认新建病历
     * @param patId
     * @return
     * @author zhaoning
     */
    @POST
    @Path("confirmNewMr")
    public StringData confirmNewMr(String patId){
        StringData data = new StringData();
        data.setCode(patVisitServiceApi.confirmNewMr(patId));
        return data;
    }

    /**
     * 移入病历 病人列表
     * @param
     * @return
     * @author zhaoning
     */
    @GET
    @Path("getPatMasterByIn")
    public List<PatMasterIndex> getPatMasterByIn(){
        String deptCode="140101";
        List<PatMasterIndex> patMasterIndexes=patVisitServiceApi.getPatMasterByIn(deptCode);
        return patMasterIndexes;
    }

    /**
     * 确认移入病历
     * @param patId
     * @return
     * @author zhaoning
     */
    @POST
    @Path("confirmMoveIn")
    public StringData confirmMoveIn(String patId){
     StringData data = new StringData();
        data.setCode(patVisitServiceApi.confirmMoveIn(patId));
        return data;
    }

    /**
     * 移除病历
     * @param patId
     * @return
     * @author zhaoning
     */
    @POST
    @Path("removeMr")
    public StringData removeMr(@QueryParam("patId")String patId){
        StringData data=new StringData();
        data.setCode(patVisitServiceApi.removMr(patId));
        return data;
    }

    /**
     * 获取病人信息 -- 住院
     * @param patientId
     * @return
     * @author zhaoning
     */
    @GET
    @Path("getPatMasterIndex")
    public PatMasterIndex getPatMasterIndex(@QueryParam("patientId")String patientId){
        PatMasterIndex patMasterIndex=patVisitServiceApi.getPatMasterIndex(patientId);
        return patMasterIndex;
    }

    /**
     * 保存编辑的病人信息---住院
     * @param patMasterIndex
     * @return
     * @author zhaoning
     */
    @POST
    @Path("savePatInfo")
    public StringData savePatInfo(PatMasterIndex patMasterIndex){
        StringData data=new StringData();
        data.setCode(patVisitServiceApi.savePatInfo(patMasterIndex));
        return data;
    }

}
