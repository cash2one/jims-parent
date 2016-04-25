package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.SysCompany;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    @POST
    public List<DeptDict> merge() {

        List<DeptDict> list = deptDictApi.findAllList();
        return list;
    }

    @Path("selectProperty")
    @POST
    public List<DeptDict> findProperty() {

        List<DeptDict> list = deptDictApi.findProperty();
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
        int num = deptDictApi.add(deptDict);
        StringData stringData = new StringData();
       // stringData.setCode(num);
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
}
