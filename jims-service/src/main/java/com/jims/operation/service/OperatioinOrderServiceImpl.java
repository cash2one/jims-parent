package com.jims.operation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.common.service.impl.CrudImplService;
import com.jims.operation.api.OperatioinOrderServiceApi;
import com.jims.operation.dao.OperationScheduleDao;
import com.jims.operation.dao.ScheduledOperationNameDao;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 住院-手术预约
 *
 * @author PangQian
 * @date2016/5/12 0012
 */
@Service(version = "1.0.0")
@Transactional(readOnly = true)
public class OperatioinOrderServiceImpl extends CrudImplService<PatsInHospitalDao, PatsInHospital> implements OperatioinOrderServiceApi {
    @Autowired
    private PatsInHospitalDao patsInHospitalDao;
    @Autowired
    private ScheduledOperationNameDao scheduledOperationNameDao;
    @Autowired
    private OperationScheduleDao operationScheduleDao;

    /**
     * 通过科室Code拿到医生所负责的病人
     * @param deptCode
     * @return
     */
   public List<PatsInHospital> getOperationin(String deptCode){
     return   patsInHospitalDao.getOperationin(deptCode);
   }

    public String saveOperation(OperationSchedule operationSchedule){
        if(operationSchedule!=null) {
            if (operationSchedule.getIsNewRecord()) {
                int scheduleId = getScheduleId(operationSchedule.getPatientId(), operationSchedule.getVisitId());
                    scheduleId = scheduleId+1;
                operationSchedule.setScheduleId( Integer.valueOf(scheduleId));
                operationSchedule.preInsert();
                operationScheduleDao.insert(operationSchedule);
                if (operationSchedule.getScheduledOperationNameList() != null) {
                   List<ScheduledOperationName> scheduledOperationNameList=operationSchedule.getScheduledOperationNameList();
                    for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                        ScheduledOperationName scheduledOperationName = new ScheduledOperationName();
                        scheduledOperationName = scheduledOperationNameList.get(i);
                        if (scheduledOperationName.getIsNewRecord()) {
                            scheduledOperationName.setOperationNo(i+1);
                            scheduledOperationName.preInsert();
                            scheduledOperationName.setScheduleId(scheduleId);
                            scheduledOperationNameDao.insert(scheduledOperationName);
                        } else {
                            scheduledOperationName.preUpdate();
                            scheduledOperationNameDao.update(scheduledOperationName);
                        }
                    }
                }


            } else {
                operationSchedule.preUpdate();
                operationScheduleDao.update(operationSchedule);
                if (operationSchedule.getScheduledOperationNameList() != null) {
                    List<ScheduledOperationName> scheduledOperationNameList=operationSchedule.getScheduledOperationNameList();
                    for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                        ScheduledOperationName scheduledOperationName = new ScheduledOperationName();
                        scheduledOperationName = scheduledOperationNameList.get(i);
                        if (scheduledOperationName.getIsNewRecord()) {
                            scheduledOperationName.setOperationNo(i+1);
                            scheduledOperationName.preInsert();
                            scheduledOperationName.setPatientId(operationSchedule.getPatientId());
                            scheduledOperationName.setVisitId(operationSchedule.getVisitId());
                            scheduledOperationName.setScheduleId(operationSchedule.getScheduleId());
                            scheduledOperationNameDao.insert(scheduledOperationName);
                            return "1";
                        } else {
                            scheduledOperationName.preUpdate();
                            scheduledOperationNameDao.update(scheduledOperationName);
                            return "1";
                        }
                    }
                }
            }

            return "1";


        }else{
            return "0";
        }
    }


    /**
     * 找到病人本次住院最大的ScheduleId
     * @param patientId
     * @param visitId
     * @return
     */
    public int getScheduleId(String patientId,String visitId){
        Integer   scheduleId =operationScheduleDao.getScheduleId(patientId, visitId);
        if(scheduleId==null){
            scheduleId=0;
        }
       return scheduleId;
    }

    /**
     * 通过patientId、visitId拿到手术安排
     * @param patientId
     * @param visitId
     * @return
     */
    public OperationSchedule getSchedule(String patientId,String visitId){
        OperationSchedule operationSchedule=operationScheduleDao.getSchedule(patientId,visitId);
        return operationSchedule;
    }

    /**
     * 通过patientId、visitId拿到手术名称
     * @param patientId
     * @param visitId
     * @param scheduleId
     * @return
     */
    public List<ScheduledOperationName> getOperationName(String patientId,String visitId,Integer scheduleId){
        List<ScheduledOperationName>  operationNameList= scheduledOperationNameDao.getOperationName(patientId,visitId,scheduleId);
      return operationNameList;
    }

    /**
     * 删除手术名称
     * @param id
     * @return
     */
    public int deleteOperationName(String id){
      return   scheduledOperationNameDao.delete(id);
    }
}
