package com.jims.clinic.course;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.CourseRecordApi;
import com.jims.clinic.entity.CourseRecord;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.entity.Dict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by qinlongxin on 2016/4/21.
 */
@Component
@Produces("application/json")
@Path("courseRecord")
public class CourseRecordRest {
    @Reference(version = "1.0.0")
    private CourseRecordApi courseRecordApi ;
    /**
     * 异步加载表格
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<CourseRecord> page = courseRecordApi.findPage(new Page<CourseRecord>(request,response), new CourseRecord());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        String num=courseRecordApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}

