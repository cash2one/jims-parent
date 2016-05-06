package com.jims.clinic.dao;

import com.jims.clinic.entity.CourseRecordStage;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * Created by qinlongxin on 2016/4/21.
 */
@MyBatisDao
public interface CourseRecordStageDao extends CrudDao<CourseRecordStage> {

    /**
     *通过病程主表Id
     * 拿到阶段小结
     */
  public CourseRecordStage getByCourseId(@Param("courseRecordId") String courseRecordId);
}
