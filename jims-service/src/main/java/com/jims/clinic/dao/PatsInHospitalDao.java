/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;


import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 在院病人记录DAO接口
 * @author pq
 * @version 2016-05-12
 */
@MyBatisDao
public interface PatsInHospitalDao extends CrudDao<PatsInHospital> {
    /**
     * 通过科室ID拿到在院病人的集合(住院-手术预约-病人列表)
     * @param deptCode
     * @return
     * pq
     */
    public List<PatsInHospital> getOperationin(@Param(value = "deptCode")String deptCode);

    /**
     * 根据patientId删除数据
     * @author CTQ
     * @date 2016-05-26 11:01:39
     * @param patientId
     * @return
     */
    public Integer deleteByPatientId(@Param("patientId")String patientId);

    /**
     * 病人出科更新信息
     * @param patientId
     * @param visitId
     * @return
     */
    public int updateByParam(@Param("patientId")String patientId,@Param("visitId")String visitId);

    /**
     * 根据id更新病区，床号等信息
     * @param id
     * @return
     */
    public int updateInfo(@Param("id")String id);
}