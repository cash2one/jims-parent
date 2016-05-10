package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.AdministrationDictApi;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("AdministrationDict")
public class AdministrationDictRest {

    @Reference(version = "1.0.0")
    private AdministrationDictApi administrationDictApi;

    /**
     * 分页查询给药途径字典列表
     * @return
     *  @author  yangruidong
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<AdministrationDict> page = administrationDictApi.findPage(new Page<AdministrationDict>(request, response), new AdministrationDict());
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }


    /**
     * 获取单条数据
     * @param id
     * @return
     * @author  yangruidong
     */
    @Path("get")
    @POST
    public AdministrationDict get(String id) {
        AdministrationDict administrationDict = administrationDictApi.get(id);
        return administrationDict;
    }

    /**
     * 保存修改方法
     * @param administrationDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(AdministrationDict administrationDict) {
        String num = administrationDictApi.save(administrationDict);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


    /**
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = administrationDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
