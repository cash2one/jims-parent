package com.jims.clinic.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.api.CourseRecordStageApi;
import com.jims.clinic.dao.CourseRecordDao;
import com.jims.clinic.dao.CourseRecordStageDao;
import com.jims.clinic.entity.CourseRecord;
import com.jims.clinic.entity.CourseRecordStage;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qinlongxin on 2016/4/21.
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class CourseRecordStageServiceImpl extends CrudImplService<CourseRecordStageDao,CourseRecordStage> implements CourseRecordStageApi{


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
    public String saveStage( CourseRecordStage courseRecordStage){
        CourseRecord courseRecord =  courseRecordStage.getCourseRecord();
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
            courseRecordStage.setSetBingchengId(courseRecord.getId());//设置病程ID
        }
        return save(courseRecordStage);//保存阶段小结
    }

    /**
     * 阶段小结
     * @param courseRecordId
     * @return
     */
    public CourseRecordStage getByCourseId(String courseRecordId){
        return courseRecordStageDao.getByCourseId(courseRecordId);
    }


}
