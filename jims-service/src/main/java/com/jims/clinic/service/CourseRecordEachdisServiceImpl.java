/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.CourseRecordEachdisApi;
import com.jims.clinic.dao.CourseRecordDao;
import com.jims.clinic.dao.CourseRecordEachdisDao;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordEachdis;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 病程记录---每日病程记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version ="1.0.0")

public class CourseRecordEachdisServiceImpl extends CrudImplService<CourseRecordEachdisDao, CourseRecordEachdis> implements CourseRecordEachdisApi {

    @Autowired
    private CourseRecordEachdisDao courseRecordEachdisDao;
    @Autowired
    private CourseRecordDao courseRecordDao;

    /**
     * 保存每日病程记录
     * @param courseRecordEachdis
     * @return String
     * @Author zhaoning
     * @version 2016-04-21
     */
    public String saveEachdis(CourseRecordEachdis courseRecordEachdis){
        CourseRecord courseRecord =  courseRecordEachdis.getCourseRecord();
        courseRecord.setId(courseRecordEachdis.getBingchengId());

        if(courseRecord!=null){
            if (courseRecord.getIsNewRecord()){
                courseRecord.preInsert();
                courseRecordDao.insert(courseRecord);
            }else{
                courseRecord.preUpdate();
                courseRecordDao.update(courseRecord);
            }
        }//保存病程主记录
        if(courseRecord!=null && courseRecord.getId()!=null){
            courseRecordEachdis.setBingchengId(courseRecord.getId());//设置病程ID
        }
        return save(courseRecordEachdis);//保存每日病程
    }
    /**
     * 根据病程主记录查询 每日病程
     * @param courseRecordId
     * @return CourseRecordEachdis
     * @Author zhaoning
     * @version 2016-04-21
     */
    @Override
    public CourseRecordEachdis getEachdisByCourseRecordId(String  courseRecordId) {
        return  courseRecordEachdisDao.getEachdisByCourse(courseRecordId);
    }
}