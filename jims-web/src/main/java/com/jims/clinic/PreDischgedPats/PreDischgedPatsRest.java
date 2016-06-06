package com.jims.clinic.PreDischgedPats;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.PreDischgedPatsServiceApi;
import com.jims.clinic.entity.PreDischgedPats;
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
 * Created by qinlongxin on 2016/6/2.
 */
@Component
@Produces("application/json")
@Path("preDischgedPats")
public class PreDischgedPatsRest {
    @Reference(version = "1.0.0")
    private PreDischgedPatsServiceApi preDischgedPatsServiceApi;

    @Path("list")
    @GET
    public Page<PreDischgedPats> list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<PreDischgedPats> page = preDischgedPatsServiceApi.findPage(new Page<PreDischgedPats>(request, response), new PreDischgedPats());
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return page;
    }
}
