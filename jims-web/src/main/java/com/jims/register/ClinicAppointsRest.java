package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.DateUtils;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.patient.entity.PatMasterIndex;
import com.jims.register.api.ClinicAppointsServiceApi;
import com.jims.register.entity.ClinicAppoints;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public StringData saveAppoints(PatMasterIndex patMasterIndex,@Context HttpServletRequest request){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        StringData data=new StringData();
        data.setCode(clinicAppointsServiceApi.saveAppointsRegis(patMasterIndex,loginInfo));
        return data;

    }

    /**
     * 条件查询预约的记录
     * @param idNo
     * @param name
     * @param visitDate
     * @return
     */
    @GET
    @Path("searchAppoints")
    public List<ClinicAppoints> searchAppointsList(@QueryParam("idNo")String idNo,@QueryParam("name")String name,@QueryParam("visitDate")String visitDate,@Context HttpServletRequest request)throws Exception{
        ClinicAppoints clinicAppoints=new ClinicAppoints();
        String orgId= LoginInfoUtils.getPersionInfo(request).getOrgId();
        clinicAppoints.setIdNo(idNo);
        clinicAppoints.setName(name);
        clinicAppoints.setOrgId(orgId);
        clinicAppoints.setVisitDateAppted(DateUtils.parseDate(visitDate));
        List<ClinicAppoints>  list=clinicAppointsServiceApi.findList(clinicAppoints);
        return list;
    }

    /**
     * 根据id获取对象
     * @param id
     * @return
     * @author zhaoning
     */
    @GET
    @Path("get")
    public ClinicAppoints get(@QueryParam("id")String id){
     return clinicAppointsServiceApi.get(id);
    }

    /**
     * 预约确认
     * @param clinicAppoints
     * @return
     * @author zhaoning
     */
    @POST
    @Path("saveAppointReg")
    public StringData saveAppointReg(ClinicAppoints clinicAppoints,@Context HttpServletRequest request){
        String orgId= LoginInfoUtils.getPersionInfo(request).getOrgId();
        StringData data=new StringData();
        data.setCode(clinicAppointsServiceApi.saveAppointReg(clinicAppoints.getId(),orgId));
        return data;
    }

    /**
     * 删除预约信息
     * @param id
     * @return
     * @author zhaoning
     */
    @POST
    @Path("deleteAppoints")
    public StringData deleteAppoints(String id){
        StringData data =new StringData();
        data.setCode(clinicAppointsServiceApi.deleteAppoints(id));
        return data;
    }

    /**
     * 编辑预约信息
     * @param clinicAppoints
     * @return
     * @author zhaoning
     */
    @POST
    @Path("editAppoints")
    public StringData editAppoints(ClinicAppoints clinicAppoints){
        StringData data=new StringData();
        data.setCode(clinicAppointsServiceApi.editAppoints(clinicAppoints));
        return data;
    }
 }
