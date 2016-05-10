/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;

import com.jims.clinic.entity.ClinicMaster;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 病人就诊记录DAO接口
 * @author zhaoning
 * @version 2016-04-20
 */
@MyBatisDao
public interface ClinicMasterDao extends CrudDao<ClinicMaster> {
    /**
     * 根据当前登录人 医生ID查询 门诊病人(待诊病人)
     * 查询的病人列表是  -----我的病人 列表   非  全科病人列表
     * @param doctorId
     * @return
     * @author zhaoning
     */
    public List<ClinicMaster>  getClinicBydoctor(@Param("doctorId")String doctorId);

    /**
     * 根据当前登录人 医生ID 查询 门诊病人（已诊病人）
     * @param doctorId
     * @return
     * @author zhaoning
     */
    public List<ClinicMaster> getClinicMasterDiagnosed(@Param("doctorId")String doctorId);

    /**
     * 查询病人就诊记录
     * @param visitDate
     * @param visitNo
     * @return
     */
    public ClinicMaster getMasterInfo(@Param("visitDate") Date visitDate, @Param("visitNo") Integer visitNo);
}