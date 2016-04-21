package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.UnitInContractApi;
import com.jims.sys.entity.UnitInContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by Administrator on 2016/4/20.
 */

@Component
@Produces("application/json")
@Path("UnitInContract")
public class UnitInContractRest {

    @Reference(version = "1.0.0")
    private UnitInContractApi unitInContractApi;

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<UnitInContract> page=unitInContractApi.findPage(new Page<UnitInContract>(request,response),new UnitInContract());
        PageData<UnitInContract> pageData=new PageData<UnitInContract>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public UnitInContract get(String id) {
        UnitInContract unitInContract = unitInContractApi.get(id);
        return unitInContract;
    }

    /**
     * 保存修改方法
     * @param unitInContract
     * @return
     */
    @Path("save")
    @POST
    public StringData save(UnitInContract unitInContract){
        String num=unitInContractApi.save(unitInContract);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        String num=unitInContractApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
