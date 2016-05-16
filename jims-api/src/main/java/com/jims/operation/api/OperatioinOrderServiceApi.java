package com.jims.operation.api;

import com.jims.clinic.entity.ClinicItemDict;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;

import java.util.List;

/**
 * 住院-手术预约
 *
 * @author PangQian
 * @date2016/5/12 0012
 */
public interface OperatioinOrderServiceApi {
    /**
     * 通过科室ID拿到病人列表
     * @param deptCode
     * @return
     * pq
     */
    public List<PatsInHospital> getOperationin(String deptCode);

    /**
     * 保存手术安排
     * @param operationSchedule
     * @return
     */
    public String saveOperation(OperationSchedule operationSchedule);

    /**
     * 通过病人ID，住院ID拿到病人本次住院的手术最大的申请号
     * @param patientId
     * @param visitId
     * @return
     */
    public int  getScheduleId(String patientId,String visitId);




}
