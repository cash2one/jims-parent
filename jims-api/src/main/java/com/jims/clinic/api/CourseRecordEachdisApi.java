package com.jims.clinic.api;

import com.jims.clinic.entity.CourseRecordEachdis;

/**
 * Created by qinlongxin on 2016/4/20.
 */
public interface CourseRecordEachdisApi {
    /**
     * 根据每日病程id查询每日病程记录信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordEachdis get(CourseRecordEachdis courseRecordEachdis);
    /**
     * 根据每日病程id查询每日病程记录信息
     * @author zhangyao
     * @version 2016/4/23
     */
    public CourseRecordEachdis get(String id);
    /**
     *
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public  String  saveEachdis(CourseRecordEachdis courseRecordEachdis);
    /**
     * 通过病程记录主要表bingcheng_id查询每日病程
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecordEachdis getEachdisByCourseRecordId(String courseRecordId);
}
