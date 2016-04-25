package com.jims.clinic.api;

import com.jims.clinic.entity.CourseRecordSuperiordocrecor;

/**
 * Created by qinlongxin on 2016/4/20.
 */
public interface CourseRecordSuperiorDocrecorApi {
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
    public String saveSuperior(CourseRecordSuperiordocrecor courseRecordSuperiordocrecor);
    /**
     * 通过病程记录主要表bingcheng_id查询上级医师查房
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordSuperiordocrecor getDocrecorByCourseRecordId(String  courseRecordId);
}
