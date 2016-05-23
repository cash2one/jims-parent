package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.data.StringData;
import com.jims.register.api.ClinicReturnedAcctServiceApi;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

/**
 * Created by Administrator on 2016/5/23.
 * 退号rest 类
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("clinicReturned")
public class ClinicReturnedAcctRest {
    @Reference(version="1.0.0")
    private ClinicReturnedAcctServiceApi clinicReturnedAcctServiceApi;

    /**
     * 根据就诊日期 和就诊序号 查询退号的基本信息
     * @param visitDate
     * @param visitNo
     * @return
     * @author zhaoning
     */
    @GET
    @Path("getClinicMaster")
    public ClinicMaster getClinicMaster(@QueryParam("visitDate")String visitDate,@QueryParam("visitNo")String visitNo){
        Integer no=Integer.parseInt(visitNo);
        return clinicReturnedAcctServiceApi.getClinicMaster(visitDate,no);
    }

    /**
     * 退号
     * @param id
     * @return
     * @author zhaoning
     */
    @POST
    @Path("returnedAcct")
    public StringData returnedAcct(@QueryParam("id")String id)throws Exception{
        StringData data = new StringData();
        data.setCode(clinicReturnedAcctServiceApi.returnedAcctInfo(id));
        return data;
    }
}
