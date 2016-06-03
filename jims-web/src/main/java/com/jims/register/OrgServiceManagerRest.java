package com.jims.register;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.register.api.OrgServiceManagerApi;
import com.jims.register.entity.OrgSelfServiceList;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.register.entity.OrgServiceList;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 机构服务Rest层
 * @author lgx
 * @version 2016-05-31
 */
@Component
@Produces("application/json")
@Path("org-service")
public class OrgServiceManagerRest {

    @Reference(version = "1.0.0")
    private OrgServiceManagerApi api;

    @POST
    @Path("save-service")
    public String saveService(List<OrgServiceList> serviceList){
        return api.saveService(serviceList);
    }

    @POST
    @Path("save-self-service")
    public String saveSelfService(List<OrgSelfServiceList> selfServiceList){
        return null;
    }

    /**
     * 检索机构购买的服务、菜单
     * @param orgId 机构Id
     * @return
     */
    @GET
    @Path("find-service")
    public List<OrgServiceList> findService(@QueryParam("orgId")String orgId){
        return api.findService(orgId);
    }

    @GET
    @Path("find-self-service")
    public List<OrgSelfServiceList> findSelfService(@QueryParam("orgId") String orgId){
        return api.findSelfService(orgId);
    }

    @GET
    @Path("find-self-service-menu")
    public List<OrgSelfServiceVsMenu> findSelfServiceMenu(@QueryParam("selfServiceId") String selfServiceId){
        return api.findSelfServiceVsMenu(selfServiceId);
    }

    @GET
    @Path("find-menu")
    public List<OrgSelfServiceVsMenuVo> findSelfServiceVsMenu(@QueryParam("orgId") String orgId){
        return api.findSelfServiceMenu(orgId);
    }
}
