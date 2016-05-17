package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.register.api.ClinicIndexServiceApi;
import com.jims.register.entity.ClinicIndex;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;

/**
 * Created by Administrator on 2016/5/17.
 * 号别Rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("clinicIndex")
public class ClinicIndexRest {
    @Reference(version = "1.0.0")
    private ClinicIndexServiceApi clinicIndexServiceApi;

    /**
     * 查询号别list
     * @return
     */
    @GET
    @Path("findList")
    public List<ClinicIndex> findClinicIndexs(){
     ClinicIndex clinicIndex=new ClinicIndex();
       return clinicIndexServiceApi.findList(clinicIndex);
    }

    /**
     * 保存 号别数据
     * @param clinicIndexList
     * @return
     */
    @POST
    @Path("saveClinicIndex")
    public StringData saveClinicIndex(List<ClinicIndex> clinicIndexList){
        StringData data=new StringData();
        data.setCode(clinicIndexServiceApi.saveList(clinicIndexList));
        return data;
    }

    /**
     * 删除 号别
     * @param id
     * @return
     */
    @Path("delete")
    @POST
    public StringData deleteClinicIndex(String id){
     StringData data = new StringData();
        data.setCode(clinicIndexServiceApi.delete(id));
        return data;
    }
}
