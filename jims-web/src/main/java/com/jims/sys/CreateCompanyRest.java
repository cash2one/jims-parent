package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jims.common.data.StringData;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.entity.SysCompany;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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


    private static final ObjectMapper MAPPER = new ObjectMapper();

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
     * 查询父机构
     *
     * @return
     */
    @POST
    @Path("select")
    public List<SysCompany> findAllByName() {
        return sysCompanyApi.findListByName();
    }

    /**
     * 查询组织机构信息
     * @param
     * @return
     */
    @GET
    @Path("get")
    public SysCompany get()
    {
        return sysCompanyApi.get("d4850942-9a61-4c89-958b-1ba36e5bd21f");
    }

    /**
     * 查询组织机构信息
     *
     * @param
     * @return
     */
    @POST
    @Path("update")
    public StringData update(SysCompany sysCompany) {
        System.out.print(sysCompany.getId());
        int num=sysCompanyApi.update(sysCompany);
        if(num!=0)
        {
            StringData stringData = new StringData();
            stringData.setData("success");
            return stringData;
        }
        return null;

    }
}
