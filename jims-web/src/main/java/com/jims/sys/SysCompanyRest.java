package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.StaffDict;
import com.jims.common.data.StringData;
import com.jims.common.vo.LoginInfo;
import com.jims.oauth2.integration.utils.Cache;
import com.jims.oauth2.integration.utils.CacheManager;
import com.jims.register.api.OrgServiceManagerApi;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysService;
import org.apache.commons.fileupload.util.LimitedInputStream;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;

/**
 * Created by fyg on 2016/6/2.
 */
@Component
@Produces("application/json")
@Path("sys-sompany")
public class SysCompanyRest {
    @Reference(version = "1.0.0")
    private SysCompanyApi sysCompanyApi;

    @Reference(version = "1.0.0")
    private OrgStaffApi orgStaffApi;

    @Reference(version = "1.0.0")
    private DeptDictApi deptDictApi ;

    /**
     * 根据申请状态查询组织机构列表
     *
     * @param applyStatus 申请状态
     * @return 组织机构list集合
     * @author fengyuguang
     */
    @GET
    @Path("find-list-by-status")
    public List<SysCompany> findListByApplyStatus(@QueryParam("applyStatus") String applyStatus) {
        return sysCompanyApi.findListByApplyStatus(applyStatus);
    }

    /**
     * 查询orgId和其子机构对应的机构信息
     * @param orgId
     * @return 组织机构list集合
     * @author zhuqi
     */
    @GET
    @Path("find-list-by-parent-id")
    public List<SysCompany> findListByParentId(@QueryParam("orgId") String orgId) {
        return sysCompanyApi.findListByParentId(orgId);
    }

    /**
     * 查询用户注册的DEL_FLAG='0'的所有机构List
     * @param owner
     * @return 组织机构list集合
     * @author 娄会丽
     */
    @GET
    @Path("findAllByOwner")
    public List<SysCompany> findAllByOwner(@QueryParam("owner") String owner) {
//        return sysCompanyApi.findAllByOwner(owner);
        List<SysCompany> list=sysCompanyApi.findAllByOwner(owner);
        if(list!=null&&!list.isEmpty()){
            for(SysCompany sysCompany:list){
                List<SysService> ls = sysCompany.getSysServiceList();
                if(list!=null&&!list.isEmpty()){
                    for(SysService sysService:ls){
                        try {
                            if(sysService.getServiceDescription()!=null){
                                sysService.setTranServiceDescription(new String(sysService.getServiceDescription(), "utf-8"));
                            }
                        } catch (Exception e) { e.printStackTrace(); }
                    }
                }
            }
        }
        return list;
    }

    /**
     * 根据机构所属者和组织机构名称查询信息
     * @param owner
     * @param orgName
     * @author 娄会丽
     * @return
     */
    @GET
    @Path("findIsNoByOwner")
    public SysCompany findIsNoByOwner(@QueryParam("owner") String owner,@QueryParam("orgName") String orgName,@QueryParam("orgCode") String orgCode) {

        SysCompany sysCompany = new SysCompany();
        sysCompany.setOwner(owner);
        sysCompany.setOrgCode(orgCode);
        sysCompany.setOrgName(orgName);
        sysCompany = sysCompanyApi.findIsNoByOwner(sysCompany);
        return sysCompany;
    }
    /**
     * 根据ID获取组织机构信息
     *
     * @return 组织机构
     * @author fengyuguang
     */
    @GET
    @Path("get-sysCompany-by-id")
    public SysCompany getSysCompanyById(@QueryParam("id") String id, @Context HttpServletRequest request){
        HttpSession session = request.getSession();
        Cache cache = CacheManager.getCacheInfo(session.getId());
        if(cache==null) return null;
        LoginInfo loginInfo = (LoginInfo) cache.getValue();
        loginInfo.setOrgId(id);
        OrgStaff orgStaff = orgStaffApi.findStaffByPersonIdOrgId(loginInfo.getPersionId(), id);
        if(orgStaff!=null){
            loginInfo.setStaffId(orgStaff.getId());
            loginInfo.setDeptId(orgStaff.getDeptId());
            DeptDict deptDict = deptDictApi.get(orgStaff.getDeptId());
            if(deptDict!=null){
                loginInfo.setDeptCode(deptDict.getDeptCode());
                loginInfo.setDeptName(deptDict.getDeptName());
            }
            cache.setValue(loginInfo);
            CacheManager.putCache(session.getId(),cache);
        }
        return sysCompanyApi.get(id);
    }

    /**
     * 更改组织机构审核状态
     *
     * @param id 组织机构ID
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("update-pass-status")
    public StringData updatePathStatus(@QueryParam("id") String id) {
        SysCompany sysCompany = sysCompanyApi.get(id);
        sysCompany.setApplyStatus("2");
        int i = sysCompanyApi.update(sysCompany);
        String num = String.valueOf(i);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 更改组织机构审核状态
     *
     * @param id 组织机构ID
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("update-fail-status")
    public StringData updateFailStatus(@QueryParam("id") String id) {
        SysCompany sysCompany = sysCompanyApi.get(id);
        sysCompany.setApplyStatus("-1");
        int i = sysCompanyApi.failPass(sysCompany);
        String num = String.valueOf(i);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

}
