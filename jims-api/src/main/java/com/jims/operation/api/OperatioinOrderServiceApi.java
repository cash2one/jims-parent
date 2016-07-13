package com.jims.operation.api;

import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.service.CrudService;
import com.jims.common.web.impl.BaseDto;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;

import java.util.List;

/**
 * 住院-手术预约
 *
 * @author PangQian
 * @date2016/5/12 0012
 */
public interface OperatioinOrderServiceApi{
    /**
     * 通过科室ID拿到病人列表
     * @param deptCode
     * @return
     * pq
     */
    public List<PatsInHospital> getOperationin(String deptCode);

    /**
     * 保存手术安排(住院)
     * @param operationSchedule
     * @return
     */
    public String saveOperationIn(OperationSchedule operationSchedule);

    /**
     * 保存手术安排（门诊）
     * @param operationSchedule
     * @return
     */
    public String saveOperationOut(OperationSchedule operationSchedule);
    /**
     * 通过病人ID，住院ID拿到病人本次住院的手术最大的申请号
     * @param patientId
     * @param visitId
     * @return
     */
     public Integer  getScheduleId(String patientId,String visitId,String clinicId);

    /**
     * 通过主表Id拿到手术名称的列表
     * @param patientId
     * @param visitId
     * @param clinicId
     * @return
     */
     public  List<ScheduledOperationName> getOperationName(String patientId,String visitId,String clinicId,String scheduleId);

    /**
     * 通过病人Id、visitId拿到手术安排
     * @param patientId
     * @return
     */
     public List<OperationSchedule> getSchedule(String patientId,String visitId,String clinicId);

    /**
     * 删除手术名称
     * @param id
     * @return
     */
     public  int deleteOperationName(String id);

    /**
     * 查询门诊手术确认的列表
     * @param scheduledDateTime
     * @param operatingRoom
     * @author pq
     * @return
     */
    public List<BaseDto> findOperation(String scheduledDateTime,String operatingRoom);

    /**
     * 确认门诊手术
     * @author pq
     * @return
     */
    public String confrimOperation(OperationSchedule operationSchedule);

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public OperationSchedule getOneOperation(String id);

}
