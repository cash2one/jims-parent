package com.jims.clinic.api;

import com.jims.clinic.entity.CourseRecord;
import com.jims.common.persistence.Page;

import java.util.List;

/**
 * Created by qinlongxin on 2016/4/20.
 */
public interface CourseRecordApi {
    /**
     * 根据病程id查询病程记录主表信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public CourseRecord get(CourseRecord courseRecord);

    /**
     * 根据病程id查询病程记录主表信息
     * @author zhangyao
     * @version 2016/4/23
     */
    public CourseRecord get(String id);
    /**
     * 查询病程记录主表分页信息
     * @author qinlongxin
     * @version 2016/4/20
     */
    public Page<CourseRecord> findPage(Page<CourseRecord> page, CourseRecord courseRecord);
    /**
     * 保存或编辑
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String save(CourseRecord courseRecord);
    /**
     * 删除病程记录主要数据
     * @author qinlongxin
     * @version 2016/4/20
     */
    public String delete(String ids);
}
