package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.OrgRoleApi;
import com.jims.sys.api.PersionServiceListApi;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.PersionServiceList;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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
    public List<SysService> findAllListByOrgId(@QueryParam("persionId") String persionId)
    {
        return persionServiceListApi.findListByFlag(persionId);
    }

    /**
     * 保存注册信息以及选择的服务
     * @param persionServiceList
     * @return 1 成功 ,0 失败
     */
    @Path("saveService")
    @POST
    public String saveCompanyAndService(PersionServiceList persionServiceList) {
        if (persionServiceList!=null) {
            persionServiceListApi.saveService(persionServiceList);
            return "1";
        }
        return "0";
    }

    /**
     * 根据组织机构名称查询组织机构id
     * @param orgName
     * @return
     */
    @GET
    @Path("getOrgName")
    public SysCompany getOrgName(@QueryParam("orgName") String orgName)
    {
        return persionServiceListApi.getOrgName(orgName);
    }
}
