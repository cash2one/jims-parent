package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.register.api.ClinicTypeFeeServiceApi;
import com.jims.register.api.ClinicTypeSettingServiceApi;
import com.jims.register.entity.ClinicTypeFee;
import com.jims.register.entity.ClinicTypeSetting;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;

/**
 * Created by Administrator on 2016/5/16.
 * 号类Rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("clinicType")
public class ClinicTypesSettingRest {
    @Reference(version = "1.0.0")
    private ClinicTypeSettingServiceApi clinicTypeSettingServiceApi;
    @Reference(version = "1.0.0")
    private ClinicTypeFeeServiceApi clinicTypeFeeServiceApi;

    /**
     * 获取号类集合
     * @return
     * @author zhaoning
     */
    @Path("findList")
    @GET
    public List<ClinicTypeSetting> findList(){
         List<ClinicTypeSetting> list=clinicTypeSettingServiceApi.findList(new ClinicTypeSetting());
        return list;
    }

    /**
     * 根据号类ID 获取，号类项目
     * @param typeId
     * @return
     * @author zhaoning
     */
    @Path("itemList")
    @GET
    public List<ClinicTypeFee> findListItem(@QueryParam("typeId")String typeId){
         ClinicTypeFee clinicTypeFee = new ClinicTypeFee();
         clinicTypeFee.setTypeId(typeId);
         List<ClinicTypeFee> list= clinicTypeFeeServiceApi.findList(clinicTypeFee);
         return list;
    }
}
