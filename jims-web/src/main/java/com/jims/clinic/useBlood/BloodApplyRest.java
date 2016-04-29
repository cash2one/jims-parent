package com.jims.clinic.useBlood;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.BloodApplyServiceApi;
import com.jims.clinic.entity.BloodApply;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by qinlongxin on 2016/4/29.
 */
@Component
@Produces("application/json")
@Path("bloodApply")
public class BloodApplyRest {
    @Reference(version = "1.0.0")
    private BloodApplyServiceApi bloodApplyServiceApi ;
    /**
     * 异步加载表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page< BloodApply> page = bloodApplyServiceApi.findPage(new Page<BloodApply>(request,response), new  BloodApply());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return null;
    }
}
