package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.entity.SysCompany;
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
@Path("sys-company")
public class CreateCompanyRest {


    @Reference(version = "1.0.0")
    private SysCompanyApi sysCompanyApi;

    /**
     *创建组织机构
     * @param sysCompany
     * @return
     */
    @POST
    @Path("add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createCompany(SysCompany sysCompany) {

        int company = sysCompanyApi.createCompany(sysCompany);
        if (company == 1) {
            return Response.status(Response.Status.OK).entity(sysCompany).build();
        }
        return Response.status(Response.Status.OK).entity(null).build();

    }

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<SysCompany> page = sysCompanyApi.findPage(new Page<SysCompany>(request, response), new SysCompany());
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
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

}
