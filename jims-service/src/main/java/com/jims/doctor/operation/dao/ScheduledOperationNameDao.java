/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.operation.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.operation.entity.ScheduledOperationName;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 手术安排名称DAO接口
 * @author pq
 * @version 2016-05-12
 */
@MyBatisDao
public interface ScheduledOperationNameDao extends CrudDao<ScheduledOperationName> {
   /**
    * 查询手术名称
    * @param patientId
    * @param visitId
    * @param clinicId
    * @return
    */
   public List<ScheduledOperationName> getOperationName(@Param(value = "patientId")String patientId,@Param(value = "visitId")String visitId,@Param("clinicId")String clinicId,@Param("scheduleId")String scheduleId);

   /**
    *通过scheduleId获取手术安排
    * @param scheduleId
    * @return
    */
   public List<ScheduledOperationName> getOperationNameList(@Param("scheduleId")String scheduleId);

   /**
    * 删除手术名
    * @param scheduleId
    * @return
    */
   public int deleteSchedule(@Param("scheduleId")String scheduleId);

   /**
    * 删除手术名称（子表）
    * @param id
    * @return
    */
   public int deleteScheduledOperationName(@Param("id")String id);
}