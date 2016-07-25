/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.register.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import com.jims.register.entity.ClinicSchedule;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 号别安排DAO接口
 * @author zhaoning
 * @version 2016-05-17
 */
@MyBatisDao
public interface ClinicScheduleDao extends CrudDao<ClinicSchedule> {
    /**
     * 根据 号别ID 查询 安排信息
     * @param clinicIndexId
     * @return
     * @author zhaoning
     */
    public List<ClinicSchedule> getClinicSchedules(@Param("clinicIndexId")String clinicIndexId);
    public List<BaseDto> findListTable(ClinicSchedule clinicSchedule);

    public  int batchDel(@Param("list")List<ClinicSchedule> list,@Param("clinicTypeId")String clinicTypeId);
	
}