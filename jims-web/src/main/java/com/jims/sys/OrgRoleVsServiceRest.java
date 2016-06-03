package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.OrgRoleVsServiceApi;
import com.jims.sys.entity.OrgRoleVsService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.OrgSelfServiceVsMenuVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2016/5/31.
 */
@Component
@Produces("application/json")
@Path("roleVs")
public class OrgRoleVsServiceRest {

    @Reference(version = "1.0.0")
    private OrgRoleVsServiceApi orgRoleVsServiceApi;

    @Path("save")
    @POST
    public String save(List<OrgRoleVsService> orgRoleVsServices){
       return orgRoleVsServiceApi.OrgRoleVsServiceSave(orgRoleVsServices);
    }

    @Path("find")
    @GET
    public List<RoleServiceMenu> find(@QueryParam("id") String id){
        List<RoleServiceMenu>  orgSelfServiceVsMenuVos= orgRoleVsServiceApi.find(id);
        return orgSelfServiceVsMenuVos;
    }
}
