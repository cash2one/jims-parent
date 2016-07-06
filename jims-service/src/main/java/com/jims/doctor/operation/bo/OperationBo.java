package com.jims.doctor.operation.bo;

import com.jims.clinic.bo.CostOrdersUtilsService;
import com.jims.clinic.dao.PatsInHospitalDao;
import com.jims.clinic.entity.ClinicItemDict;
import com.jims.doctor.operation.dao.OperationScheduleDao;
import com.jims.doctor.operation.dao.ScheduledOperationNameDao;
import com.jims.operation.entity.OperationSchedule;
import com.jims.operation.entity.ScheduledOperationName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/23.
 * 门诊手术申请BO
 */
@Service
@Transactional(readOnly = false)
public class OperationBo {

    @Autowired
    private PatsInHospitalDao patsInHospitalDao;
    @Autowired
    private ScheduledOperationNameDao scheduledOperationNameDao;
    @Autowired
    private OperationScheduleDao operationScheduleDao;
    @Autowired
    private CostOrdersUtilsService costOrdersUtilsService;

    /**
     * 保存门诊
     * @param operationSchedule
     * @return
     */
    public String saveOperationOut(OperationSchedule operationSchedule){
        if(operationSchedule!=null) {
            int num;
            List<ClinicItemDict> clinicItemDictList=new ArrayList<ClinicItemDict>();
            if (operationSchedule.getIsNewRecord()) {
                String scheduleId = getScheduleId(operationSchedule.getPatientId(), operationSchedule.getVisitId());
                int sId=Integer.parseInt(scheduleId)+1;
                operationSchedule.setScheduleId(sId);
                operationSchedule.setAckIndicator(0);
                operationSchedule.preInsert();
//                operationScheduleDao.insert(operationSchedule);
                if (operationSchedule.getScheduledOperationNameList() != null) {
                    List<ScheduledOperationName> scheduledOperationNameList=operationSchedule.getScheduledOperationNameList();

                    for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                        ClinicItemDict clinicItemDict=new ClinicItemDict();
                        ScheduledOperationName scheduledOperationName = scheduledOperationNameList.get(i);
                        scheduledOperationName.setScheduleId(operationSchedule.getId());
                        clinicItemDict.setItemCode(scheduledOperationName.getOperationCode());
//                            clinicItemDict.setOrgId(scheduledOperationName.);
                        if (scheduledOperationName.getIsNewRecord()) {
                            scheduledOperationName.setOperationNo(i+1);
                            scheduledOperationName.preInsert();
                            scheduledOperationNameDao.insert(scheduledOperationName);
                        } else {
                            scheduledOperationName.preUpdate();
                            scheduledOperationNameDao.update(scheduledOperationName);
                        }
                        clinicItemDictList.add(clinicItemDict);
                    }
                }
                costOrdersUtilsService.save(operationSchedule.getClinicId(),clinicItemDictList,operationSchedule.getId());
                num = operationScheduleDao.insert(operationSchedule);
                return  num+"";

            } else {
                operationSchedule.preUpdate();
//                String scheduleId = getScheduleId(operationSchedule.getPatientId(), operationSchedule.getVisitId());
//                int sId=Integer.parseInt(scheduleId)+1;
//                operationSchedule.setScheduleId(sId);
//                operationScheduleDao.update(operationSchedule);
                operationSchedule.setAckIndicator(0);
                if (operationSchedule.getScheduledOperationNameList() != null) {
                    List<ScheduledOperationName> scheduledOperationNameList=operationSchedule.getScheduledOperationNameList();
                    for (int i = 0; i < scheduledOperationNameList.size(); i++) {
                        ClinicItemDict clinicItemDict = new ClinicItemDict();
                        ScheduledOperationName scheduledOperationName = scheduledOperationNameList.get(i);
                        clinicItemDict.setItemCode(scheduledOperationName.getOperationCode());
                        if (scheduledOperationName.getIsNewRecord()) {
                            scheduledOperationName.setOperationNo(i+1);
                            scheduledOperationName.preInsert();
                            scheduledOperationName.setScheduleId(operationSchedule.getId());
                            scheduledOperationNameDao.insert(scheduledOperationName);
                        } else {
                            scheduledOperationName.preUpdate();
                            scheduledOperationNameDao.update(scheduledOperationName);
                        }
                        clinicItemDictList.add(clinicItemDict);
                    }
                }
            }

//            return "1";
            costOrdersUtilsService.save(operationSchedule.getClinicId(),clinicItemDictList,operationSchedule.getId());
            num = operationScheduleDao.update(operationSchedule);
            return  num+"";

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
    public String getScheduleId(String patientId,String visitId){
        String   scheduleId =operationScheduleDao.getScheduleId(patientId, visitId);
        if(scheduleId==null){
            scheduleId="0";
        }
        return scheduleId;
    }

    /**
     * 通过patientId、visitId拿到手术安排
     * @param patientId
     * @param visitId
     * @return
     */
    public OperationSchedule getSchedule(String patientId,String visitId,String clinicId){
        OperationSchedule operationSchedule=operationScheduleDao.getSchedule(patientId,visitId,clinicId);
        return operationSchedule;
    }

    /**
     * 查询手术名称
     * @param patientId
     * @param visitId
     * @return
     */
    public List<ScheduledOperationName> getOperationName(String patientId,String visitId,String clinicId,String scheduleId){
        OperationSchedule operationSchedule=operationScheduleDao.getSchedule(patientId,visitId,clinicId);
        List<ScheduledOperationName>  operationNameList= scheduledOperationNameDao.getOperationName(patientId,visitId,clinicId,scheduleId);
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
