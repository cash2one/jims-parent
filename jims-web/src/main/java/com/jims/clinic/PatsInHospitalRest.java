package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Lists;
import com.jims.clinic.api.PatsInHospitalServiceApi;
import com.jims.common.data.StringData;
import com.jims.common.web.impl.BaseDto;
import com.jims.patient.api.PatMasterIndexServiceApi;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * 病人在院记录
 * @author CTQ
 * @date 2016-06-06 09:36:49
 */
@Component
@Produces("application/json")
@Path("patsInHospital")
public class PatsInHospitalRest {
    @Reference(version = "1.0.0")
    PatMasterIndexServiceApi patMasterIndexServiceApi;
    @Reference(version = "1.0.0")
    PatsInHospitalServiceApi patsInHospitalServiceApi;
    @Path("patientlist")
    @GET
    public List<BaseDto> patientlist(){
        List<BaseDto> list = Lists.newArrayList();
        try {
            list = patMasterIndexServiceApi.findWaitFrom();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }


    @Path("save")
    @POST
    public StringData save(HttpServletRequest request,BaseDto baseDto){
        StringData stringData=new StringData();
        try {
            String data = patsInHospitalServiceApi.saveHospInfo(baseDto);
            stringData.setCode(data);
            stringData.setData(data.compareTo("0") > 0 ? "success":"error");
        }catch (Exception e){
            e.printStackTrace();
        }
        return stringData;
    }

}
