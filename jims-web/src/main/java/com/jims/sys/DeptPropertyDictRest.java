package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.DeptPropertyDictApi;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.entity.*;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20 0020.
 */
@Component
@Produces("application/json")
@Path("dept-property")
public class DeptPropertyDictRest {

    @Reference(version = "1.0.0")
    private DeptPropertyDictApi  deptPropertyDictApi;

    @Reference(version = "1.0.0")
    private SysCompanyApi sysCompanyApi;

    @GET
    @Path("list")
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<OrgDeptPropertyDict> page = deptPropertyDictApi.findPage(new Page<OrgDeptPropertyDict>(request, response), new OrgDeptPropertyDict());
        PageData<OrgDeptPropertyDict> pageData = new PageData<OrgDeptPropertyDict>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    @Path("selectProperty")
    @POST
    public List<OrgDeptPropertyDict> findProperty() {

        List<OrgDeptPropertyDict> list = deptPropertyDictApi.findProperty();
        return list;
    }
    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public OrgDeptPropertyDict get(String id) {
        OrgDeptPropertyDict orgDeptPropertyDict = deptPropertyDictApi.get(id);
        return orgDeptPropertyDict;
    }

    /**
     * 查询父机构
     *
     * @return
     */
    @GET
    @Path("select")
    public List<SysCompany> findAllByName() {
        return sysCompanyApi.findListByName();
    }

    /**
     * 查询属性名称
     *
     * @return
     */
    @POST
    @Path("selectName")
    public List<OrgDeptPropertyDict> findNameByType() {
        return deptPropertyDictApi.findNameByType();
    }



    /**
     * 保存修改方法
     *
     * @param
     * @return
     */
    @Path("add")
    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData save(OrgDeptPropertyDict orgDeptPropertyDict) {
        int num = deptPropertyDictApi.add(orgDeptPropertyDict);
        if(num!=0)
        {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
        return null;

    }

    /**
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = deptPropertyDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
