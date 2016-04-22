package com.jims.clinic;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.CourseRecordApi;
import com.jims.clinic.api.CourseRecordSuperiordocrecordApi;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordSuperiordocrecor;
import com.jims.common.data.StringData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Administrator on 2016/4/22.
 */
@Component
@Produces("application/json")
@Path("courseRecordSuperiordocrecor")
public class CourseRecordSuperiordocrecorRest {
    @Reference(version = "1.0.0")
    private CourseRecordSuperiordocrecordApi courseRecordSuperiordocrecordApi;
    @Reference(version = "1.0.0")
    private CourseRecordApi courseRecordApi;

    @GET
    @Path("list")
    public CourseRecordSuperiordocrecor list(){

        CourseRecordSuperiordocrecor courseRecordSuperiordocrecor=new CourseRecordSuperiordocrecor();
        courseRecordSuperiordocrecordApi.save(courseRecordSuperiordocrecor);
        return courseRecordSuperiordocrecor;
    }

    @POST
    @Path("save")
    public StringData save(CourseRecordSuperiordocrecor courseRecordSuperiordocrecor){
        CourseRecord courseRecord=new CourseRecord();
        courseRecord.setType("1221");
     //   courseRecordApi.save(courseRecord);
        String num=courseRecordSuperiordocrecordApi.save(courseRecordSuperiordocrecor);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
