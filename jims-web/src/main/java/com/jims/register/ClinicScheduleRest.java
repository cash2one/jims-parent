package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.register.api.ClinicScheduleApi;
import com.jims.register.entity.ClinicSchedule;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 * 号别 安排录入 Rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("clinicSchedule")
public class ClinicScheduleRest {
    @Reference(version = "1.0.0")
    private ClinicScheduleApi clinicScheduleApi;

    /**
     * 根据号别ID 查询 该号别的安排录入信息
     * @param clinicIndexId
     * @return List<ClinicSchedule>
     * @author zhaoning
     */
    @GET
    @Path("findList")
    public List<ClinicSchedule> findList(@QueryParam("clinicIndexId")String clinicIndexId){
        List<ClinicSchedule> list=new ArrayList<ClinicSchedule>();
        ClinicSchedule clinicSchedule = new ClinicSchedule();
        clinicSchedule.setClinicLabel(clinicIndexId);
        list=clinicScheduleApi.findList(clinicSchedule);
        return list;
    }

    /**
     * 保存 安排录入信息
     * @return
     * @author zhaoning
     */
    @POST
    @Path("save")
    public StringData saveSchedule(List<ClinicSchedule> clinicSchedules,@QueryParam("clinicIndexId") String clinicIndexId){
        StringData data =new StringData();
        data.setCode(clinicScheduleApi.saveList(clinicSchedules,clinicIndexId));
        return data;
    }

    /**
     * 删除 安排录入
     * @param id
     * @return
     * @author zhaoning
     */
    @POST
    @Path("delete")
    public StringData deleteSchedule(String id){
        StringData data=new StringData();
        data.setCode(clinicScheduleApi.delete(id));
        return data;
    }
}
