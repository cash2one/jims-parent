package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.SysCompanyApi;
import com.jims.sys.entity.SysCompany;
import org.apache.commons.fileupload.util.LimitedInputStream;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
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

    /**
     * 根据申请状态查询组织机构列表
     * @param applyStatus 申请状态
     * @return 组织机构list集合
     * @author fengyuguang
     */
    @GET
    @Path("find-list-by-status")
    public List<SysCompany> findListByApplyStatus(@QueryParam("applyStatus")String applyStatus){
        return  sysCompanyApi.findListByApplyStatus(applyStatus);
    }

    /**
     * 根据ID获取组织机构信息
     * @return 组织机构
     * @author fengyuguang
     */
    @GET
    @Path("get-sysCompany-by-id")
    public SysCompany getSysCompanyById(@QueryParam("id")String id){
        return sysCompanyApi.get(id);
    }

    /**
     * 更改组织机构审核状态
     * @param id 组织机构ID
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("update-pass-status")
    public StringData updatePathStatus(@QueryParam("id")String id) {
        System.out.println(id);
        SysCompany sysCompany = sysCompanyApi.get(id);
        sysCompany.setApplyStatus("2");
        int i = sysCompanyApi.update(sysCompany);
        String num = String.valueOf(i);
        System.out.println("num:" + num);
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
     * @param id  组织机构ID
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("update-fail-status")
    public StringData updateFailStatus(@QueryParam("id") String id) {
        System.out.println(id);
        SysCompany sysCompany = sysCompanyApi.get(id);
        sysCompany.setApplyStatus("-1");
        int i = sysCompanyApi.update(sysCompany);
        String num = String.valueOf(i);
        System.out.println("num:" + num);
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
