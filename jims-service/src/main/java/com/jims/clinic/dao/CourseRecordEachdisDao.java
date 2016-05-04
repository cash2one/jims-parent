/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.CourseRecordEachdis;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 病程记录---每日病程记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface CourseRecordEachdisDao extends CrudDao<CourseRecordEachdis> {

    /**
     * 根据病程主记录查询每日病程
     * @param courseRecordId
     * @return CourseRecordEachdis
     * @Author zhaoning
     * @version 2016-04-20
     */
    public CourseRecordEachdis getEachdisByCourse(@Param("courseRecordId") String courseRecordId);
}