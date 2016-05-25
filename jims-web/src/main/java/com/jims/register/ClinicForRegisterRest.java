package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.data.StringData;
import com.jims.register.api.ClinicForRegisterSerivceApi;
import com.jims.register.entity.ClinicForRegist;
import com.jims.register.entity.ClinicSchedule;
import org.springframework.stereotype.Component;
import javax.ws.rs.*;
import java.text.SimpleDateFormat;
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
    public List<ClinicForRegist> findList(){
        ClinicForRegist clinicForRegist = new ClinicForRegist();
        return clinicForRegisterSerivceApi.findList(clinicForRegist);
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
        SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd");
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
    @GET
    @Path("findListReg")
    public List<ClinicForRegist> findListReg(@QueryParam("status")String status){

        return clinicForRegisterSerivceApi.findListReg(status);
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
}
