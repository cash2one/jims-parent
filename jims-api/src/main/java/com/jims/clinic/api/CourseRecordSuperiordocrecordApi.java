package com.jims.clinic.api;

import com.jims.clinic.entity.CourseRecordSuperiordocrecor;

/**
 * Created by qinlongxin on 2016/4/20.
 */
public interface CourseRecordSuperiordocrecordApi {
    /**
     * id查询上级医师查房信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordSuperiordocrecor get(CourseRecordSuperiordocrecor courseRecordSuperiordocrecor);
    /**
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public void saveCourseRecordSuperiordoc(CourseRecordSuperiordocrecor courseRecordSuperiordocrecor);
    /**
     * 通过病程记录主要表bingcheng_id查询每日病程
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordSuperiordocrecor getDocrecorByCourseRecordId(CourseRecordSuperiordocrecor courseRecordSuperiordocrecor);
}
