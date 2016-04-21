package com.jims.clinic.api;

import com.jims.clinic.entity.CourseRecordStage;

/**
 * Created by qinlongxin on 2016/4/20.
 */
public interface CourseRecordStageApi {
    /**
     * 通过id查询阶段小结主要表信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordStage get(CourseRecordStage courseRecordStage);
    /**
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public void save(CourseRecordStage courseRecordStage);
    /**
     * 通过病程记录主要表bingcheng_id查询阶段小结
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordStage getRecordByCourseRecordId(String courseRecordId);
}
