package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.sys.api.OrgRoleApi;
import com.jims.sys.api.PersionServiceListApi;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.SysService;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.util.List;


/**
 * Created by yangruidong on 2016/5/31 0024.
 */
@Component
@Produces("application/json")
@Path("persion-service-list")
public class PersionServiceListRest {
    @Reference(version = "1.0.0")
    private PersionServiceListApi persionServiceListApi;

    @GET
    @Path("findListByFlag")
    public List<SysService> findAllListByOrgId(@QueryParam("persionId") String persionId,@QueryParam("flag") String flag)
    {
        return persionServiceListApi.findListByFlag(persionId, flag);
    }
}
