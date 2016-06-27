package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.DateUtils;
import com.jims.register.api.ClinicForRegisterSerivceApi;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicSchedule;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/5/18.
 * 号表生成 rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("clinicRegister")
public class ClinicForRegisterRest {
    @Reference(version = "1.0.0")
    private ClinicForRegisterSerivceApi clinicForRegisterSerivceApi;

    /**
     * 查询号表 集合
     * @return
     */
    @GET
    @Path("findList")
    public PageData findList(@Context HttpServletRequest request, @Context HttpServletResponse response,@QueryParam("clinicDateStr")String  clinicDateStr,@QueryParam("timeDesc")String timeDesc,@QueryParam("clinicIndexName")String clinicIndexName){
        Date clinicDate= DateUtils.parseDate(clinicDateStr);
        ClinicForRegist clinicForRegist=new ClinicForRegist();
        clinicForRegist.setClinicLabelName(clinicIndexName);
        clinicForRegist.setTimeDesc(timeDesc);
        clinicForRegist.setClinicDate(clinicDate);
        Page<ClinicForRegist> page = clinicForRegisterSerivceApi.findPage(new Page<ClinicForRegist>(request, response), clinicForRegist);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 保存 号表生成
     * @param clinicSchedules
     * @param startTime
     * @param endTime
     * @return
     * @author zhaoning
     */
    @POST
    @Path("saveRegister")
    public StringData saveRegister (List<ClinicSchedule> clinicSchedules,@QueryParam("startTime")String  startTime,@QueryParam("endTime")String  endTime)throws Exception{
        StringData data= new StringData();
        data.setCode(clinicForRegisterSerivceApi.saveRegister(clinicSchedules,startTime,endTime));
        return data;
    }
    /**
     * 删除 已经生成的号表
     * @param id
     * @return
     * @author zhaoning
     */
    @POST
    @Path("delete")
    public StringData deleteClinicReg(String id){
        StringData data=new StringData();
        data.setCode(clinicForRegisterSerivceApi.delete(id));
        return data;
    }

    /**
     * 查询当前日期的号表
     * @return
     */
    @POST
    @Path("findListReg")
    public List<ClinicForRegist> findListReg(ClinicForRegist clinicForRegist){
        return clinicForRegisterSerivceApi.findListReg(clinicForRegist);
    }

    /**
     * 保存挂号信息
     * @param clinicMaster
     * @return
     */
    @POST
    @Path("saveClinic")
    public StringData saveClinic(ClinicMaster clinicMaster)throws Exception{
        StringData data=new StringData();
        data.setCode(clinicForRegisterSerivceApi.saveClinic(clinicMaster));
        return data;
    }
    /**
     * 保存挂号信息
     * @param
     * @return
     */
    @POST
    @Path("update")
    public StringData update(List<ClinicForRegist> clinicForRegistList){
        StringData data=new StringData();
        clinicForRegisterSerivceApi.updateBatch(clinicForRegistList);
        data.setCode("1");
        return data;
    }

}
