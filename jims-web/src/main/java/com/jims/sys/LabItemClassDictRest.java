package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.LabItemClassDictServiceApi;
import com.jims.sys.entity.LabItemClassDict;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by xueyx on 2016/5/5 0024.
 */
@Component
@Produces("application/json")
@Path("labitemclass")
public class LabItemClassDictRest {

    @Reference(version = "1.0.0")
    private LabItemClassDictServiceApi labItemClassDictServiceApi;
    /**
     * 查询科室代码下的检验标本
     * @param检验科室编码 code
     * @return
     */
    @Path("findListByDeptCode")
    @POST
    public List<LabItemClassDict> findListByDeptCode(String code) {
        /*int index = code.indexOf("=");
        code =code.substring(index+1);*/
        List<LabItemClassDict> list = labItemClassDictServiceApi.findListByDeptCode(code);
        return list;
    }

    @Path("list")
    @GET
    public List<LabItemClassDict> list() {
        List<LabItemClassDict> list = labItemClassDictServiceApi.findAllList();
        return list;
    }

    @Path("del")
    @POST
    public StringData del(@QueryParam("ids") String ids){
        String num=labItemClassDictServiceApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    @Path("save")
    @POST
    public StringData save(LabItemClassDict labItemClassDict){;
        String num=labItemClassDictServiceApi.save(labItemClassDict);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    @Path("findList")
    @POST
    public List<LabItemClassDict> findList(LabItemClassDict labItemClassDict){;
        return labItemClassDictServiceApi.findList(labItemClassDict);
    }
}
