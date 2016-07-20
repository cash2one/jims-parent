package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.register.api.ClinicTypeFeeServiceApi;
import com.jims.register.api.ClinicTypeSettingServiceApi;
import com.jims.register.entity.ClinicTypeFee;
import com.jims.register.entity.ClinicTypeSetting;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
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
         List<ClinicTypeFee> list = new ArrayList<ClinicTypeFee>();
        if(typeId!=null && !typeId.equals("")){
            clinicTypeFee.setTypeId(typeId);
            list = clinicTypeFeeServiceApi.findList(clinicTypeFee);
        }
         return list;
    }

    /**
     * 保存号类及收费
     * @param clinicTypeFees
     * @return
     */
    @Path("saveItem")
    @POST
    public StringData saveItem(List<ClinicTypeFee> clinicTypeFees,@QueryParam("type")String type,@QueryParam("clinicTypeId")String clinicTypeId,@Context HttpServletRequest request){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        StringData data=new StringData();
        data.setCode(clinicTypeFeeServiceApi.saveList(clinicTypeFees,type,clinicTypeId,loginInfo));
        return data;
    }

    /**
     * 删除号类子表
     * @param id
     * @return
     */
    @Path("delete")
    @POST
    public StringData delete(String id){
        StringData data=new StringData();
        data.setCode(clinicTypeFeeServiceApi.delete(id));
        return data;
    }

    /**
     * 删除 号类 主
     * @param id
     * @return
     */
    @POST
    @Path("deleteClinicType")
    public StringData deleteClinicType(String id){
        StringData data=new StringData();
        data .setCode(clinicTypeSettingServiceApi.delete(id));
        return data;
    }
}
