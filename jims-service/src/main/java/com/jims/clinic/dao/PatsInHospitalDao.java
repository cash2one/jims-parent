/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.clinic.dao;


import com.jims.clinic.entity.PatsInHospital;
import com.jims.clinic.vo.ComeDeptVo;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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
     * 新建病历时更新 病人在院记录的 经治医生
     * @param doctorInCharge
     * @author zhaoning
     */
    public void updateByMrNew(@Param("id")String id,@Param("doctorInCharge")String doctorInCharge);

    /**
     * 根据病人ID 查询 在院记录信息
     * @param patId
     * @return
     * @author zhaoning
     */
    public PatsInHospital getPatsInfoByMaster(@Param("patId")String patId);
    /**
     * 出院-根据床位和病区查询病人信息
     * @param bedNo
     * @param wardCode
     * @author CTQ
     * @return
     */
    public BaseDto searchInfoByParams(@Param("bedNo")Integer bedNo, @Param("wardCode")String wardCode);
    /**
     * 转出-根据床位和病区查询病人信息
     * @param bedNo
     * @param wardCode
     * @author CTQ
     * @return
     */
    public BaseDto searchTurnOutInfoByParams(@Param("bedNo")Integer bedNo, @Param("wardCode")String wardCode);
    /**
     * 取消转出-待专科病人列表
     * @author CTQ
     * @return
     */
    public List<BaseDto> waitTurnOutList();

    /**
     * 取消入科病人列表
     * @author CTQ
     * @return
     */
    public List<BaseDto> cacelPatientlist(@Param("wardCode")String wardCode);
    /**
     * 可被取消离院的病人列表
     * @param vo
     * @return
     */
    public List<BaseDto> cancelLeavePatientlist(ComeDeptVo vo);


    public String comeDeptVoGet(Map map);


}