/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.doctor.useBlood.dao;


import com.jims.blood.entity.BloodApply;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import org.apache.ibatis.annotations.Param;

/**
 * 用血申请DAO接口
 * @author qinlongxin
 * @version 2016-04-28
 */
@MyBatisDao
public interface BloodApplylDao  extends CrudDao<BloodApply> {

//    /**
//     *点击用血申请获取病人信息通过patient_id获得
//     * @param patientId
//     * @param
//     * @return
//     */
//    public BloodApply getPatientInformation(String patientId);


    /**
     * 手术确认
     * @author pq
     * @param bloodApply
     * @return
     */
    public  int confirmBlood(BloodApply bloodApply);

    /**
     * 删除主记录
     * @param id
     * @return
     */
    public int deleteBloodApply(@Param("id")String id);
}