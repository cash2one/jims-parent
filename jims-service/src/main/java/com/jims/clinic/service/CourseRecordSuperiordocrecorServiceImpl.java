/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.CourseRecordSuperiorDocrecorApi;
import com.jims.clinic.dao.CourseRecordDao;
import com.jims.clinic.dao.CourseRecordSuperiordocrecorDao;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordSuperiordocrecor;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 * 病程记录--上级医师查房记录Service
 * @author zhaoning
 * @version 2016-04-20
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class CourseRecordSuperiordocrecorServiceImpl extends CrudImplService<CourseRecordSuperiordocrecorDao, CourseRecordSuperiordocrecor> implements CourseRecordSuperiorDocrecorApi {

    @Autowired
    private CourseRecordSuperiordocrecorDao courseRecordSuperiordocrecorDao;
    @Autowired
    private CourseRecordDao courseRecordDao;

    /**
     * 保存上级医师查房
     * @param courseRecordSuperiordocrecor
     * @return
     */
    public String save(CourseRecordSuperiordocrecor courseRecordSuperiordocrecor){
        CourseRecord courseRecord =courseRecordSuperiordocrecor.getCourseRecord();
        courseRecord.setId(courseRecordSuperiordocrecor.getBingchengId());
        if(courseRecord!=null){
            if(courseRecord!=null){
                if (courseRecord.getIsNewRecord()){
                    courseRecord.preInsert();
                    courseRecordDao.insert(courseRecord);
                }else{
                    courseRecord.preUpdate();
                    courseRecordDao.update(courseRecord);
                }
            }//保存病程主记录
        }
        if(courseRecord!=null && courseRecord.getId()!=null){
            courseRecordSuperiordocrecor.setBingchengId(courseRecord.getId());//设置病程ID
        }
        return save(courseRecordSuperiordocrecor);//保存上级医师查房
    }

    /**
     * 根据病程主记录查询上级医师查房信息
     * @param courseRecordId
     * @return
     * @author zhaoning
     * @version 2016-04-21
     */
    @Override
    public CourseRecordSuperiordocrecor getDocrecorByCourseRecordId(String courseRecordId) {
        return courseRecordSuperiordocrecorDao.getSuperiordByCourse(courseRecordId);
    }
}