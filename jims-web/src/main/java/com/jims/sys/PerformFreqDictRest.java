package com.jims.sys;

import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.PerformFreqDictApi;
import com.jims.sys.entity.PerformFreqDict;
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
@Path("PerformFreqDict")
public class PerformFreqDictRest {
    @Autowired
    private PerformFreqDictApi performFreqDictApi;

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<PerformFreqDict> page=performFreqDictApi.findPage(new Page<PerformFreqDict>(request,response),new PerformFreqDict());
        PageData<PerformFreqDict> pageData=new PageData<PerformFreqDict>();
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
    public PerformFreqDict get(String id) {
        PerformFreqDict performFreqDict = performFreqDictApi.get(id);
        return performFreqDict;
    }

    /**
     * 保存修改方法
     * @param performFreqDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(PerformFreqDict performFreqDict){
        String num=performFreqDictApi.save(performFreqDict);
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
        String num=performFreqDictApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
