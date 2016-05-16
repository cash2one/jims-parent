/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.operation.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.operation.entity.OperationSchedule;
import org.apache.ibatis.annotations.Param;

/**
 * 手术安排DAO接口
 * @author pq
 * @version 2016-05-12
 */
@MyBatisDao
public interface OperationScheduleDao extends CrudDao<OperationSchedule> {
    /**
     * 通过病人ID，住院ID拿到病人本次住院的手术最大的申请号
     * @param patientId
     * @param visitId
     * @return
     */
    public Integer getScheduleId(@Param(value = "patientId")String patientId,@Param(value = "visitId")String visitId);
	
}