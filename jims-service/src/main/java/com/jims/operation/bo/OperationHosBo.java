package com.jims.operation.bo;

import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.PatsInHospital;
import com.jims.operation.dao.OperationScheduleDao;
import com.jims.operation.dao.ScheduledOperationNameDao;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 * 住院手术申请bo
 */
@Service
@Transactional(readOnly = false)
public class OperationHosBo {
    @Autowired
    private PatsInHospitalDao patsInHospitalDao;
    @Autowired
    private ScheduledOperationNameDao scheduledOperationNameDao;
    @Autowired
    private OperationScheduleDao operationScheduleDao;

    public String saveOperationIn(OperationSchedule operationSchedule){
        if(operationSchedule!=null) {
            if (operationSchedule.getIsNewRecord()) {
                String scheduleId = getScheduleId(operationSchedule.getPatientId(), operationSchedule.getVisitId());
                int sId=Integer.parseInt(scheduleId)+1;
                operationSchedule.setScheduleId(sId);
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
                            scheduledOperationName.setScheduleId(operationSchedule.getId());
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

                            scheduledOperationName.setScheduleId(operationSchedule.getId());
                            scheduledOperationNameDao.insert(scheduledOperationName);

                        } else {
                            scheduledOperationName.preUpdate();
                            scheduledOperationNameDao.update(scheduledOperationName);

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
     * 通过科室Code拿到医生所负责的病人
     * @param deptCode
     * @return
     */
    public List<PatsInHospital> getOperationin(String deptCode){
        return   patsInHospitalDao.getOperationin(deptCode);
    }

    /**
     * 找到病人本次住院最大的ScheduleId
     * @param patientId
     * @param visitId
     * @return
     */
    public String getScheduleId(String patientId,String visitId){
        String   scheduleId =operationScheduleDao.getScheduleId(patientId, visitId);
        if(scheduleId==null){
            scheduleId="0";
        }
        return scheduleId;
    }
}
