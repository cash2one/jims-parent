package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.SysCompany;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Component
@Produces("application/json")
@Path("dept-dict")
public class DeptDictRest {
    @Reference(version = "1.0.0")
    private DeptDictApi deptDictApi;



    @Path("list")
    @GET
    public List<DeptDict> merge() {

        List<DeptDict> list = deptDictApi.findAllList();
        return list;
    }



    @Path("selectParent")
    @POST
    public List<DeptDict> findParent() {

        List<DeptDict> list = deptDictApi.findParent();
        return list;
    }

    /**
     * 保存修改方法
     *
     * @param
     * @return
     */
    @Path("add")
    @POST
    public StringData save(DeptDict deptDict) {

       /* int num = deptDictApi.add(deptDict);
        if(num!=0)
        {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
           return null;*/
        System.out.print(deptDict.getId());
        String num= deptDictApi.save(deptDict);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }

    @POST
    @Path("update")
    public StringData update(DeptDict deptDict) {
        System.out.print(deptDict.getId());
        int num = deptDictApi.update(deptDict);
        if (num != 0) {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
        return null;

    }

    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = deptDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
