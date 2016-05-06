/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.CourseRecordSuperiordocrecor;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 病程记录--上级医师查房记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface CourseRecordSuperiordocrecorDao extends CrudDao<CourseRecordSuperiordocrecor> {

    /**
     * 根据病程主记录查询上级医师查房信息
     * @param courseRecordId
     * @return
     * @author zhaoning
     * @version 2016-04-21
     */
    public CourseRecordSuperiordocrecor getSuperiordByCourse(@Param("courseRecordId") String courseRecordId);
}