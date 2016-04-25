package com.jims.clinic.course;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.clinic.api.CourseRecordSuperiorDocrecorApi;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordSuperiordocrecor;
import com.jims.common.data.StringData;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by Administrator on 2016/4/22.
 */
@Component
@Produces("application/json")
@Path("courseRecordSuperiorDocrecor")
public class CourseRecordSuperiordocrecorRest {
    @Reference(version = "1.0.0")
    private CourseRecordSuperiorDocrecorApi courseRecordSuperiordocrecordApi;

    /**
     * 保存上级医师查房记录
     * @param courseRecordSuperiordocrecor
     * @return
     */
    @POST
    @Path("save")
    public StringData save(CourseRecordSuperiordocrecor courseRecordSuperiordocrecor){
        CourseRecord courseRecord=new CourseRecord();
        courseRecord.setType(courseRecordSuperiordocrecor.getType());
        courseRecord.setLuruShijian(courseRecordSuperiordocrecor.getLuruShijian());
        courseRecord.setPatientId("16013020");
        courseRecord.setZhuyuanId("c1a84181-c0e0-11e5-8417-0894ef010b21");
        courseRecord.setType("2");
        courseRecordSuperiordocrecor.setCourseRecord(courseRecord);
        String num=courseRecordSuperiordocrecordApi.saveSuperior(courseRecordSuperiordocrecor);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 通过病程Id查询上级医师查房记录
     * @param courseRecordId
     * @return
     */
    @POST
    @Path("get")
    public CourseRecordSuperiordocrecor get(String courseRecordId){
        CourseRecordSuperiordocrecor courseRecordSuperiordocrecor= courseRecordSuperiordocrecordApi.getDocrecorByCourseRecordId(courseRecordId);
        courseRecordSuperiordocrecor.setLuruShijian(courseRecordSuperiordocrecor.getCourseRecord().getLuruShijian());
        courseRecordSuperiordocrecor.setType(courseRecordSuperiordocrecor.getCourseRecord().getType());
        return courseRecordSuperiordocrecor;

    }
}
