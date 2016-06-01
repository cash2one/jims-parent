package com.jims.clinic.notice;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.PatHospitalNoticeServiceApi;
import com.jims.clinic.entity.DocOperationApply;
import com.jims.clinic.entity.PatHospitalNotice;
import com.jims.common.data.PageData;
import com.jims.common.persistence.Page;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**住院通知单
 * Created by qinlongxin on 2016/6/1.
 */
@Component
@Produces("application/json")
@Path("patHospitalNotice")
public class PatHospitalNoticeRest {
    @Reference(version = "1.0.0")
    private PatHospitalNoticeServiceApi patHospitalNoticeServiceApi;
    /**
     * 异步加载表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<PatHospitalNotice> page = patHospitalNoticeServiceApi.findPage(new Page<PatHospitalNotice>(request,response), new PatHospitalNotice());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
}
