package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.register.api.ClinicAppointsServiceApi;
import com.jims.register.entity.ClinicAppoints;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/23.
 * 预约挂号 Rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("clinicAppoints")
public class ClinicAppointsRest {
    @Reference(version = "1.0.0")
    private ClinicAppointsServiceApi clinicAppointsServiceApi;

    /**
     * 保存 挂号预约信息
     * @param patMasterIndex
     * @return
     * @author zhaoning
     * @throws Exception
     */
    @POST
    @Path("saveAppoints")
    public StringData saveAppoints(PatMasterIndex patMasterIndex) throws Exception{
        StringData data=new StringData();
        data.setCode(clinicAppointsServiceApi.saveAppointsRegis(patMasterIndex));
        return data;

    }

    /**
     * 条件查询预约的记录
     * @param clinicNo
     * @param name
     * @param cardNo
     * @param visitDate
     * @return
     */
    @GET
    @Path("searchAppoints")
    public List<ClinicAppoints> searchAppointsList(@QueryParam("clinicNo")String clinicNo,@QueryParam("name")String name,
                                                   @QueryParam("cardNo")String cardNo,@QueryParam("visitDate")String visitDate)throws Exception{
        ClinicAppoints clinicAppoints = new ClinicAppoints();
        List<ClinicAppoints> list=new ArrayList<ClinicAppoints>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        clinicAppoints.setName(name);
        clinicAppoints.setCardNo(cardNo);
        if(visitDate!=null && !visitDate.equals("")){
            clinicAppoints.setVisitDateAppted(format.parse(visitDate));
        }
        list=clinicAppointsServiceApi.findList(clinicAppoints);
        return list;
    }
    @GET
    @Path("get")
    public ClinicAppoints get(@QueryParam("id")String id){
     return clinicAppointsServiceApi.get(id);
    }

    /**
     * 保存预约收费 确认信息
     * @param patMasterIndex
     * @return
     */
    @POST
    @Path("saveAppointReg")
    public StringData saveAppointReg(PatMasterIndex patMasterIndex){
        StringData data=new StringData();
        data.setCode(clinicAppointsServiceApi.saveAppointReg(patMasterIndex));
        return data;
    }
 }
