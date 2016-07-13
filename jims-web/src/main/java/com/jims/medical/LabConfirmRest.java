package com.jims.medical;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.lab.api.LabConfirmServiceApi;
import com.jims.lab.entity.LabTestMaster;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 * 检验确认  rest
 * @author zhaoning
 */
@Component
@Produces("application/json")
@Path("labConfirm")
public class LabConfirmRest {
    @Reference(version = "1.0.0")
    private LabConfirmServiceApi labConfirmServiceApi;

    /**
     * 检查确认 列表
     * 根据当前登录人所在的执行科室 查询
     * @return
     * @author zhaoning
     */
    @GET
    @Path("getLabMaster")
    public List<LabTestMaster> getLabMaster(@QueryParam("inOrOut") String inOrOut){
        String performedBy="";
        return   labConfirmServiceApi.getLabMaster(performedBy,inOrOut);
    }

    /**
     * 确认检验
     * @param labTestMaster
     * @return
     * @author zhaoning
     */
    @POST
    @Path("confirmLab")
    public StringData confirmLab(LabTestMaster labTestMaster)throws Exception{
     StringData data=new StringData();
        data.setCode(labConfirmServiceApi.confrimLab(labTestMaster));
        return data;
    }
}
