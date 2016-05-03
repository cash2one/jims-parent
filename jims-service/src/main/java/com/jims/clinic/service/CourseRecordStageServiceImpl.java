package com.jims.clinic.service;


import com.jims.clinic.dao.CourseRecordDao;
import com.jims.clinic.dao.CourseRecordStageDao;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordStage;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by qinlongxin on 2016/4/21.
 */
public class CourseRecordStageServiceImpl extends CrudImplService<CourseRecordStageDao,CourseRecordStage> {


    @Autowired
    private CourseRecordStageDao courseRecordStageDao;
    @Autowired
    private CourseRecordDao courseRecordDao;

    /**
     * 保存阶段小结
     * @param courseRecordStage
     * @return String
     * @Author zhaoning
     * @version 2016-04-21
     */
    public String save( CourseRecordStage courseRecordStage){
        CourseRecord courseRecord =  courseRecordStage.getCourseRecord();
        courseRecord.setId(courseRecordStage.getBingchengId());
        if(courseRecord!=null){
            if (courseRecord.getIsNewRecord()){
                courseRecord.preInsert();
                courseRecordDao.insert(courseRecord);
            }else{
                courseRecord.preUpdate();
                courseRecordDao.update(courseRecord);
            }
        }//保存阶段小结
        if(courseRecord!=null && courseRecord.getId()!=null){
            courseRecordStage.setBingchengId(courseRecord.getId());//设置病程ID
        }
        return super.save(courseRecordStage);//保存阶段小结
    }
}
