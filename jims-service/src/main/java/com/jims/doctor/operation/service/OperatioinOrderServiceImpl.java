package com.jims.doctor.operation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.web.impl.BaseDto;
import com.jims.operation.api.OperatioinOrderServiceApi;
import com.jims.doctor.operation.bo.OperationBo;
import com.jims.doctor.operation.bo.OperationHosBo;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * 住院-手术预约
 *
 * @author PangQian
 * @date2016/5/12 0012
 */
@Service(version = "1.0.0")
public class OperatioinOrderServiceImpl extends CrudImplService<PatsInHospitalDao, PatsInHospital> implements OperatioinOrderServiceApi {
    @Autowired
    private OperationBo operationBo;
    @Autowired
    private OperationHosBo operationHosBo;

    /**
     * 通过科室Code拿到医生所负责的病人
     * @param deptCode
     * @return
     */
   public List<PatsInHospital> getOperationin(String deptCode){
      List<PatsInHospital> patsInHospitalList =  operationHosBo.getOperationin(deptCode);
       return patsInHospitalList;
   }

    /**
     * 保存-住院(inOrNot ,1 住院//inOrNot,0 门诊)
     * @param operationSchedule
     * @return
     */
    public String saveOperationIn(OperationSchedule operationSchedule){
       String num = operationHosBo.saveOperationIn(operationSchedule);
        return num;
    }

    /**
     * 保存门诊
     * @param operationSchedule
     * @return
     */

    public String saveOperationOut(OperationSchedule operationSchedule){
      return operationBo.saveOperationOut(operationSchedule);
    }

    /**
     * 找到病人本次住院最大的ScheduleId
     * @param patientId
     * @param visitId
     * @return
     */
    @Override
    public Integer getScheduleId(String patientId, String visitId,String clinicId) {
        return operationBo.getScheduleId(patientId,visitId,clinicId);
    }


    /**
     * 通过patientId、visitId拿到手术安排
     * @param patientId
     * @param visitId
     * @return
     */
    public OperationSchedule getSchedule(String patientId,String visitId,String clinicId){
       return operationBo.getSchedule(patientId,visitId,clinicId);
    }

    /**
     * 查询手术名称
     * @param patientId
     * @param visitId
     * @return
     */
    public List<ScheduledOperationName> getOperationName(String patientId,String visitId,String clinicId,String scheduleId){
       List<ScheduledOperationName> scheduledOperationNameList =operationBo.getOperationName(patientId,visitId,clinicId,scheduleId);
        return scheduledOperationNameList;
    }


    /**
     * 删除手术名称(门诊)
     * @param id
     * @return
     */
    public int deleteOperationName(String id){
      return  operationBo.deleteOperationName(id);
    }


    /**
     * 查询门诊手术确认的列表
     * @param scheduledDateTime
     * @author pq
     * @return
     */
    public List<BaseDto> findOperation(String scheduledDateTime,String operatingRoom){
       return  operationBo.findOperation(scheduledDateTime,operatingRoom);
    }

    /**
     * 确认门诊手术
     * @param operationSchedule
     * @author pq
     * @return
     */
    public String confrimOperation(OperationSchedule operationSchedule){
        String num = "";

              num=  operationBo.confrimOperation(operationSchedule);

      return num;
    }
}
