package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.api.ClinicScheduleApi;
import com.jims.register.entity.ClinicSchedule;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
    public PageData findList(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("clinicIndexId")String clinicIndexId,@QueryParam("clinicLabelName")String clinicLabelName,@QueryParam("dayOfWeek")String dayOfWeek,@QueryParam("timeDesc")String timeDesc){
        ClinicSchedule clinicSchedule = new ClinicSchedule();
        clinicSchedule.setClinicLabel(clinicIndexId);
        clinicSchedule.setClinicLabelName(clinicLabelName);
        clinicSchedule.setDayOfWeek(dayOfWeek);
        clinicSchedule.setTimeDesc(timeDesc);
        Page<ClinicSchedule> page = clinicScheduleApi.findPage(new Page<ClinicSchedule>(request, response),clinicSchedule);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    @POST
    @Path("findListTable")
    public List<BaseDto> findListTable(String clinicIndexId){
        List<BaseDto> list=new ArrayList<BaseDto>();
        ClinicSchedule clinicSchedule = new ClinicSchedule();
        clinicSchedule.setClinicLabel(clinicIndexId);
        list=clinicScheduleApi.findListTable(clinicSchedule);
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
