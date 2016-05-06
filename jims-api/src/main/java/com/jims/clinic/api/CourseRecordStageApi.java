package com.jims.clinic.api;

import com.jims.clinic.entity.CourseRecordStage;

/**
 * Created by qinlongxin on 2016/4/20.
 * 阶段小结
 */
public interface CourseRecordStageApi {
    /**
     * 通过id查询阶段小结主要表信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordStage get(CourseRecordStage courseRecordStage);
    /**
     * 通过id查询阶段小结主要表信息
     * @author zhangyao
     * @version 2016/4/23
     */
    public CourseRecordStage get(String id);


    /**
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String saveStage(CourseRecordStage courseRecordStage);
    /**
     * 通过病程记录主要表bingcheng_id查询阶段小结
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordStage getByCourseId(String courseRecordId);
}
