/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

/**
 * 病人就诊记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface ClinicMasterDao extends CrudDao<ClinicMaster> {

    /**
     * 查询病人就诊记录
     * @param visitDate
     * @param visitNo
     * @return
     */
    ClinicMaster getMasterInfo(@Param("visitDate") Date visitDate, @Param("visitNo") Integer visitNo);
}